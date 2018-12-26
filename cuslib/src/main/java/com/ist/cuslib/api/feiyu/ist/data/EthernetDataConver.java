package com.ist.cuslib.api.feiyu.ist.data;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ist.cuslib.api.feiyu.ist.FeiyuComSubscribe;
import com.ist.cuslib.api.feiyu.ist.NetStatus;
import com.ist.cuslib.api.feiyu.ist.bean.BaseResponse;
import com.ist.cuslib.api.feiyu.ist.bean.ethernet.EthernetInfo;
import com.ist.cuslib.api.feiyu.ist.bean.ethernet.WAN1;
import com.ist.cuslib.api.feiyu.ist.callback.IEthernetCallback;

/**
 * Created by zhengshaorui
 * Time on 2018/10/23
 */

public class EthernetDataConver extends NetBaseConvert {
    private static final String TAG = "EthernetDataConver";
    private FeiyuDataConver mDataConver;
    private IEthernetCallback mCallback;




    private static class Holder {
        static final EthernetDataConver INSTANCE = new EthernetDataConver();
    }

    public static EthernetDataConver getInstance() {
        return Holder.INSTANCE;
    }

    private EthernetDataConver() {
        mDataConver = FeiyuDataConver.getInstance();

    }

    public EthernetDataConver registerListener(IEthernetCallback listener) {
        mCallback = listener;
        return this;
    }

    public void getEthernetStatus() {
        addSubsribe(
            mDataConver.getEhternetStatus()
                .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                    @Override
                    public void onNext(String s) {
                        if (!TextUtils.isEmpty(s)){
                            try {
                                BaseResponse<EthernetInfo> response = JSON.parseObject(s, new TypeReference<BaseResponse<EthernetInfo>>() {});

                                if (mCallback != null) {
                                    mCallback.getEthernetStatus(response);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                if (mCallback != null) {
                                    mCallback.errorMsg("getEthernetStatus error: "+e.toString());
                                }
                            }

                        }else{
                            if (mCallback != null) {
                                mCallback.errorMsg("getEthernetStatus error: "+ERROR_JSON);
                            }
                        }
                    }
                }),"getEthernetStatus",true


        );
    }

    public void setEthernet(WAN1 data) {
        if (mCallback != null){
            mCallback.connectStatus(NetStatus.START);
        }
        addSubsribe(
            mDataConver.setEthernet(data)
                .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                    @Override
                    public void onNext(String s) {
                        if (!TextUtils.isEmpty(s)){
                            try {
                                BaseResponse response = JSON.parseObject(s,BaseResponse.class);
                                if ("ok".equals(response.stat)) {
                                    if (mCallback != null) {
                                        mCallback.connectStatus(NetStatus.SUCCESS);
                                    }
                                }else{
                                    if (mCallback != null){
                                        mCallback.connectStatus(NetStatus.FAIL);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                if (mCallback != null){
                                    mCallback.errorMsg("setEthernet error: "+ERROR_JSON);
                                }
                            }
                        }else{
                            if (mCallback != null){
                                mCallback.connectStatus(NetStatus.FAIL);
                            }
                        }
                    }
                }),"setEthernet"
        );
    }





}
