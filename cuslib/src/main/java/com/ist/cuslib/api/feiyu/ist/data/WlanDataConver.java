package com.ist.cuslib.api.feiyu.ist.data;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ist.cuslib.api.feiyu.ist.FeiyuComSubscribe;
import com.ist.cuslib.api.feiyu.ist.NetStatus;
import com.ist.cuslib.api.feiyu.ist.bean.BaseResponse;
import com.ist.cuslib.api.feiyu.ist.bean.wifi.WifiInfoData;
import com.ist.cuslib.api.feiyu.ist.bean.wifi.WifiStatusData;
import com.ist.cuslib.api.feiyu.ist.callback.IWlanCallback;

import io.reactivex.disposables.Disposable;

/**
 * Created by zhengshaorui
 * Time on 2018/10/23
 */

public class WlanDataConver extends NetBaseConvert {
    private static final String TAG = "WlanDataConver";
    private FeiyuDataConver mDataConver;
    private IWlanCallback mCallback;
    private Disposable mDisposable;


    private static class Holder {
        static final WlanDataConver INSTANCE = new WlanDataConver();
    }

    public static WlanDataConver getInstance() {
        return Holder.INSTANCE;
    }

    private WlanDataConver() {
        mDataConver = FeiyuDataConver.getInstance();

    }

    public WlanDataConver registerListener(IWlanCallback listener) {
        mCallback = listener;
        return this;
    }

    /**
     * 开启wifi,此时应有成功与否，成功则直接调用搜索的动作
     */
    public void enableWifi(final boolean isOpen) {
        addSubsribe(
                mDataConver.open24GWifi(isOpen)
                        .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                            @Override
                            public void onNext(String s) {
                                if (!TextUtils.isEmpty(s)) {
                                    try {
                                        BaseResponse bean = JSON.parseObject(s, BaseResponse.class);
                                        if (isOpen && "ok".equals(bean.stat)) {
                                            getWifiList();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        if (mCallback != null) {
                                            mCallback.errorMsg("enablewifi error "+e.toString());
                                        }
                                    }
                                }else{
                                    if (mCallback != null) {
                                        mCallback.errorMsg("enablewifi error "+ERROR_JSON);
                                    }
                                }
                            }
                        }), "enableWifi", true
        );
    }


    /**
     * 获取wifi list
     */
    public void getWifiList() {
        addSubsribe(
                mDataConver.getWifi2GInfo()
                        .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                            @Override
                            public void onNext(String s) {
                               // Log.d(TAG, "zsr --> json: "+s);
                                if (!TextUtils.isEmpty(s)) {
                                    try {

                                        JSONObject object = JSON.parseObject(s);
                                        JSONObject json = object.getJSONObject("cbk");

                                        //需要关闭混淆,或者加入okhttp的混淆规则
                                        WifiInfoData data = JSON.parseObject(json.toJSONString(),WifiInfoData.class);
                                        if (mCallback != null) {
                                            mCallback.scanWifiSuccess(data);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.d(TAG, "zsr --> getWifiStatus error: "+e.toString());
                                    }

                                }else {
                                    mCallback.errorMsg(ERROR_JSON);
                                }
                            }
                        }), "getWifiList"
        );

    }

    public void getWifiStatus() {
        addSubsribe(
                mDataConver.getWifiStatus()
                        .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                            @Override
                            public void onNext(String s) {
                                if (!TextUtils.isEmpty(s)) {
                                    try {
                                        //真的服，在mk 下编译就是不行
                                        BaseResponse<WifiStatusData> response = JSON.parseObject(s, new TypeReference<BaseResponse<WifiStatusData>>() {
                                        });

                                        if (mCallback != null) {
                                            mCallback.getWifiStatus(response);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.d(TAG, "zsr --> getWifiStatus error: "+e.toString());
                                    }

                                } else {
                                    if (mCallback != null) {
                                        mCallback.errorMsg(ERROR_JSON);
                                    }

                                }
                            }
                        }), "getWifiStatus"
        );
    }

    public void connectWifi(JSONObject info) {
        mCallback.connectStatus(NetStatus.START);
        addSubsribe(
                mDataConver.connectWifi(info)
                        .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                            @Override
                            public void onNext(String s) {
                                if (!TextUtils.isEmpty(s)) {
                                    try {
                                        JSONObject json = JSON.parseObject(s);
                                        String status = json.getString("stat");
                                        if ("ok".equals(status)) {
                                            mCallback.connectStatus(NetStatus.SUCCESS);
                                            getWifiList();
                                        } else {
                                            mCallback.connectStatus(NetStatus.FAIL);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        if (mCallback != null) {
                                            mCallback.errorMsg("connectWifi error: "+e.toString());
                                        }
                                    }
                                }else {
                                    if (mCallback != null) {
                                        mCallback.errorMsg("connectWifi error: "+ERROR_JSON);
                                    }
                                }
                            }
                        }), "connectWifi"
        );

    }


}
