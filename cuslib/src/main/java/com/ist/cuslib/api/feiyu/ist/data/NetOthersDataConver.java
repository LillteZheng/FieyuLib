package com.ist.cuslib.api.feiyu.ist.data;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ist.cuslib.api.feiyu.ist.FeiyuApiManager;
import com.ist.cuslib.api.feiyu.ist.FeiyuComSubscribe;
import com.ist.cuslib.api.feiyu.ist.NetStatus;
import com.ist.cuslib.api.feiyu.ist.bean.BaseResponse;
import com.ist.cuslib.api.feiyu.ist.bean.others.AutoRebootInfo;
import com.ist.cuslib.api.feiyu.ist.bean.others.UpdateInfo;
import com.ist.cuslib.api.feiyu.ist.callback.INetOthersCallback;

/**
 * Created by zhengshaorui
 * Time on 2018/10/23
 */

public class NetOthersDataConver extends NetBaseConvert {
    private static final String TAG = "NetOthersDataConver";
    private FeiyuDataConver mDataConver;
    private INetOthersCallback mCallback;


    private static class Holder {
        static final NetOthersDataConver INSTANCE = new NetOthersDataConver();
    }

    public static NetOthersDataConver getInstance() {
        return Holder.INSTANCE;
    }

    private NetOthersDataConver() {
        mDataConver = FeiyuDataConver.getInstance();

    }

    public NetOthersDataConver registerListener(INetOthersCallback listener) {
        mCallback = listener;
        return this;
    }


    public void setNetReset() {
        if (mCallback != null) {
            mCallback.connectStatus(NetStatus.RESET_START);
        }
        addSubsribe(
                mDataConver.setNetRest()
                        .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                            @Override
                            public void onNext(String s) {
                                Log.d(TAG, "zsr --> setNetReset: " + s);
                                mDataConver.stopReqeust();
                                if (!TextUtils.isEmpty(s)) {
                                    try {
                                        BaseResponse response = JSON.parseObject(s, BaseResponse.class);
                                        if (response != null) {
                                            if ("ok".equals(response.stat)) {
                                                if (mCallback != null) {
                                                    mCallback.connectStatus(NetStatus.RESET_SUCCESS);
                                                    setNetReboot(true);
                                                }
                                            } else {
                                                if (mCallback != null) {
                                                    mCallback.connectStatus(NetStatus.RESET_FAIL);
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        if (mCallback != null) {
                                            mCallback.errorMsg("setNetReset error: "+e.toString());
                                        }
                                    }
                                } else {
                                    if (mCallback != null) {
                                        mCallback.connectStatus(NetStatus.RESET_FAIL);
                                    }
                                }
                            }
                        }), "setNetReset"
        );
    }

    public void setNetReboot(final boolean isRest) {
        if (mCallback != null) {
            if (isRest) {
                mCallback.connectStatus(NetStatus.RESET_REBOOT_START);
            } else {
                mCallback.connectStatus(NetStatus.REBOOT_START);
            }
        }
        addSubsribe(
                mDataConver.setNetReboot()
                        .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                            @Override
                            public void onNext(String s) {
                                mDataConver.stopReqeust();
                                if (!TextUtils.isEmpty(s)) {
                                    try {
                                        BaseResponse response = JSON.parseObject(s, BaseResponse.class);
                                        if (response != null) {
                                            if ("ok".equals(response.stat)) {
                                                if (mCallback != null) {
                                                    if (isRest) {
                                                        mCallback.connectStatus(NetStatus.RESET_REBOOT_SUCCES);
                                                    } else {
                                                        mCallback.connectStatus(NetStatus.REBOOT_SUCCESS);
                                                    }
                                                }
                                            } else {
                                                if (mCallback != null) {
                                                    if (isRest) {
                                                        mCallback.connectStatus(NetStatus.RESET_REBOOT_FAIL);
                                                    } else {
                                                        mCallback.connectStatus(NetStatus.REBOOT_FAIL);
                                                    }
                                                }
                                            }
                                            //重启了，需要发送个广播，去告诉Android板子，重新获取IP
                                            Context context = FeiyuApiManager.getInstance().getContext();
                                            if (context != null){
                                                context.sendBroadcast(new Intent("action.feiyu.request.ip"));
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        if (mCallback != null) {
                                            mCallback.errorMsg("setNetReboot error: "+e.toString());
                                        }
                                    }
                                } else {
                                    if (mCallback != null) {
                                        if (isRest) {
                                            mCallback.connectStatus(NetStatus.RESET_REBOOT_FAIL);
                                        } else {
                                            mCallback.connectStatus(NetStatus.REBOOT_FAIL);
                                        }
                                    }
                                }
                            }
                        })
        );
    }

    public void getNetUpdateInfo() {
        addSubsribe(
                mDataConver.getNetUpdateInfo()
                        .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                            @Override
                            public void onNext(String s) {
                                if (!TextUtils.isEmpty(s)) {
                                    try {
                                        BaseResponse<UpdateInfo> response =
                                                JSON.parseObject(s, new TypeReference<BaseResponse<UpdateInfo>>() {
                                                });
                                        if (response != null) {
                                            if (mCallback != null) {
                                                mCallback.getUpdateInfo(response);
                                            }
                                        } else {
                                            if (mCallback != null) {
                                                mCallback.errorMsg("getNetUpdateInfo " + ERROR_JSON);
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        if (mCallback != null) {
                                            mCallback.errorMsg("getNetUpdateInfo error: "+e.toString());
                                        }
                                    }

                                } else {
                                    if (mCallback != null) {
                                        mCallback.errorMsg("getNetUpdateInfo " + ERROR_JSON);
                                    }
                                }
                            }
                        }), "getNetUpdateInfo"
        );
    }


    public void doUpdate() {
        addSubsribe(
                mDataConver.doUpdate()
                        .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                            @Override
                            public void onNext(String s) {
                                Log.d(TAG, "zsr --> doUpdate: " + s);
                            }
                        }), "doUpdate"
        );
    }

    public void getAutoRebootStatus() {
        addSubsribe(
                mDataConver.getAutoRebootStatus()
                        .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                            @Override
                            public void onNext(String s) {
                                if (!TextUtils.isEmpty(s)) {

                                    try {
                                        BaseResponse<AutoRebootInfo> response =
                                                JSON.parseObject(s, new TypeReference<BaseResponse<AutoRebootInfo>>() {
                                                });
                                   /* BaseResponse response = JSON.parseObject(s, BaseResponse.class);
                                    JSONObject jsonObject = JSON.parseObject(s).getJSONObject("cbk");
                                    AutoRebootInfo info = new AutoRebootInfo();
                                    info.setEnable(jsonObject.getString("enable"));
                                    info.setTime(jsonObject.getString("time"));
                                    info.setDays(jsonObject.getString("days"));
                                    info.setTimezone(jsonObject.getString("timezone"));
                                    info.setTime_srv(jsonObject.getString("time_srv"));
                                    info.setSystime(jsonObject.getString("systime"));
                                    response.cbk = info;
                                    Log.d(TAG, "zsr --> onNext: "+response);*/

                                        if (response != null) {
                                            if (mCallback != null) {
                                                mCallback.getAutoRebootInfo(response);
                                            }
                                        } else {
                                            if (mCallback != null) {
                                                mCallback.errorMsg("getAutoRebootStatus " + ERROR_JSON);
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        if (mCallback != null) {
                                            mCallback.errorMsg("getAutoRebootStatus error: " + e.toString());
                                        }
                                    }

                                } else {
                                    if (mCallback != null) {
                                        mCallback.errorMsg("getAutoRebootStatus  error: " + ERROR_JSON);
                                    }
                                }
                            }
                        }), "getAutoRebootStatus"
        );
    }


    public void setAutoReboot(final AutoRebootInfo info) {
        if (mCallback != null) {
            mCallback.connectStatus(NetStatus.TIME_START);
        }
        addSubsribe(
                mDataConver.setAutoReboot(info)
                        .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                            @Override
                            public void onNext(String s) {
                                if (!TextUtils.isEmpty(s)) {
                                    try {
                                        BaseResponse response = JSON.parseObject(s, BaseResponse.class);
                                        if (response != null && "ok".equals(response.stat)) {
                                            if (mCallback != null) {
                                                mCallback.connectStatus(NetStatus.TIME_SUCCESS);
                                            }
                                        } else {
                                            if (mCallback != null) {
                                                mCallback.connectStatus(NetStatus.TIME_FAIL);
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        if (mCallback != null) {
                                            mCallback.errorMsg("setAutoReboot error: " + e.toString());
                                        }
                                    }

                                } else {
                                    if (mCallback != null) {
                                        mCallback.connectStatus(NetStatus.TIME_FAIL);
                                    }
                                }
                            }
                        }), "setAutoReboot"
        );
    }

}
