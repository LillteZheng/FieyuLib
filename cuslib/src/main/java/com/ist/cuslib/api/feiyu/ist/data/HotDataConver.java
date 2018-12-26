package com.ist.cuslib.api.feiyu.ist.data;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ist.cuslib.api.feiyu.ist.FeiyuComSubscribe;
import com.ist.cuslib.api.feiyu.ist.NetStatus;
import com.ist.cuslib.api.feiyu.ist.bean.BaseResponse;
import com.ist.cuslib.api.feiyu.ist.bean.hotpot.HotPotInfo;
import com.ist.cuslib.api.feiyu.ist.callback.IHotCallback;
import com.ist.cuslib.utils.RxTimeUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by zhengshaorui
 * Time on 2018/10/23
 */

public class HotDataConver extends NetBaseConvert {
    private static final String TAG = "HotDataConver";
    private FeiyuDataConver mDataConver;
    private IHotCallback mCallback;
    private Disposable mDisposable;


    private static class Holder {
        static final HotDataConver INSTANCE = new HotDataConver();
    }

    public static HotDataConver getInstance() {
        return Holder.INSTANCE;
    }

    private HotDataConver() {
        mDataConver = FeiyuDataConver.getInstance();

    }

    public HotDataConver registerListener(IHotCallback listener) {
        mCallback = listener;
        return this;
    }

    public void get5GHotpotStatus(){
        addSubsribe(
             mDataConver.get5GHotpotInfo()
                .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                    @Override
                    public void onNext(String s) {
                        mDataConver.stopReqeust();
                        if (!TextUtils.isEmpty(s)) {

                            try {
                                BaseResponse<HotPotInfo> response =  JSON.parseObject(s, new TypeReference<BaseResponse<HotPotInfo>>(){});
                            /*BaseResponse response = JSON.parseObject(s,BaseResponse.class);
                            JSONObject json = JSON.parseObject(s).getJSONObject("cbk");

                            HotPotInfo data = new HotPotInfo();
                            SsidAd ssidAd = new SsidAd();
                            ssidAd.setSsidBand(json.getJSONObject("ssidAd").getString("ssidBand"));
                            ssidAd.setSsidChannel(json.getJSONObject("ssidAd").getString("ssidChannel"));
                            ssidAd.setSsidStanum(json.getJSONObject("ssidAd").getString("ssidStanum"));
                            ssidAd.setSsidTxpower(json.getJSONObject("ssidAd").getString("ssidTxpower"));
                            ssidAd.setSsidWidth(json.getJSONObject("ssidAd").getString("ssidWidth"));

                            data.setSsidAd(ssidAd);


                            List<Ssid> ssids = new ArrayList<>();
                            JSONArray jsonArray = json.getJSONArray("ssid");
                            for (int i = 0; i < jsonArray.size(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Ssid ssid = new Ssid();
                                ssid.setMacaddr(jsonObject.getString("macaddr"));
                                ssid.setSsidCrypto(jsonObject.getString("ssidCrypto"));
                                ssid.setSsidEn(jsonObject.getString("ssidEn"));
                                ssid.setSsidHide(jsonObject.getString("ssidHide"));
                                ssid.setSsidName(jsonObject.getString("ssidName"));
                                ssid.setSsidNum(jsonObject.getString("ssidNum"));
                                ssid.setSsidPw(jsonObject.getString("ssidPw"));
                                ssids.add(ssid);
                            }
                            data.setSsid(ssids);
                            response.cbk = data;*/

                                if (mCallback != null) {
                                    mCallback.getHotpotStatus(response);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                if (mCallback != null) {
                                    mCallback.errorMsg("get5GHotpotStatus error: "+e.toString());
                                }
                            }

                        } else {
                            if (mCallback != null) {
                                mCallback.errorMsg("get5GHotpotStatus error: "+ERROR_JSON);
                            }

                        }
                    }
                }),"get5GHotpotStatus",true
        );
    }

    public void set5Ghotpot(String json, String ssidChannel, final boolean isShowDialog){
        if (isShowDialog) {
            mCallback.connectStatus(NetStatus.START);
        }
        addSubsribe(
            mDataConver.set5GHotpot(json,ssidChannel)
                .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                    @Override
                    public void onNext(String s) {
                        mDataConver.stopReqeust();
                        if (!TextUtils.isEmpty(s)) {
                            try {
                                BaseResponse response = JSON.parseObject(s, BaseResponse.class);
                                if ("ok".equals(response.stat)) {
                                    mDataConver.stopReqeust();
                                    RxTimeUtils.timer(200, new RxTimeUtils.onRxTimeListener() {
                                        @Override
                                        public void onNext() {
                                            if (isShowDialog) {
                                                mCallback.connectStatus(NetStatus.SUCCESS);
                                            }
                                            get5GHotpotStatus();
                                        }
                                    });

                                } else {
                                    mCallback.connectStatus(NetStatus.FAIL);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                if (mCallback != null) {
                                    mCallback.errorMsg("set5Ghotpot error: "+e.toString());
                                }
                            }
                        }else{
                            mCallback.errorMsg("set5Ghotpot error: "+ERROR_JSON);
                        }

                    }
                }),"set5Ghotpot"

        );
    }


}
