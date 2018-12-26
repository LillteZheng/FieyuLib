package com.ist.cuslib.api.feiyu.ist.data;

import com.alibaba.fastjson.JSONObject;
import com.ist.cuslib.api.feiyu.feiyu.UdpApiClient;
import com.ist.cuslib.api.feiyu.ist.FeiyuAPI;
import com.ist.cuslib.api.feiyu.ist.bean.ethernet.WAN1;
import com.ist.cuslib.api.feiyu.ist.bean.others.AutoRebootInfo;
import com.ist.cuslib.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by zhengshaorui
 * Time on 2018/10/23
 */

public class FeiyuDataConver  {
    private static final String TAG = "FeiyuDataConver";
    /**
     * static
     */
    public static final char REQUEST = 0x51;
    public static final char RESPONSE = 0x61;
    private UdpApiClient mUdpApiClient;

    private static class Holder {
        static final FeiyuDataConver INSTANCE = new FeiyuDataConver();
    }

    public static FeiyuDataConver getInstance() {
        return Holder.INSTANCE;
    }

    private FeiyuDataConver() {
        mUdpApiClient = UdpApiClient.getInstance();
    }


    //====================================================
    //  wifi 部分
    //====================================================

    public Observable<String> getWifi2GInfo() {
        //不能用just 在于 sendquest 转换需要时间，如果直接用 just，数据为null
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(mUdpApiClient.sendRequest(FeiyuAPI.WIFI_INFO,REQUEST,"getWifi2GInfo"));
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());

    }

    public Observable<String> open24GWifi(final boolean isOpen){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(mUdpApiClient.sendRequest(FeiyuAPI.open24GWIFI(isOpen),REQUEST,"open24GWifi"));
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }


    public Observable<String> getWifiStatus(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String msg = mUdpApiClient.sendRequest(FeiyuAPI.WIFI_STAUTS,REQUEST,"getWifiStatus");
                emitter.onNext(msg);
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }


    /**
     * 请求结束
     */
    public void stopReqeust(){
        mUdpApiClient.stop();
    }


    public Observable<String> connectWifi(final JSONObject info){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String msg = mUdpApiClient.sendRequest(FeiyuAPI.set2Gwifi(info),REQUEST,"connectWifi");
                emitter.onNext(msg);
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }


    //====================================================
    //  热点部分
    //====================================================

    public Observable<String> get5GHotpotInfo(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String msg = mUdpApiClient.sendRequest(FeiyuAPI.HOTPOT_STATUS,REQUEST,"get5GHotpotInfo");
                emitter.onNext(msg);
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }

    public Observable<String> set5GHotpot(final String json,final String ssidChannel){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                String msg = mUdpApiClient.sendRequest(FeiyuAPI.set5GHotPot(json,ssidChannel),REQUEST,"set5GHotpot");
                emitter.onNext(msg);
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }

    //====================================================
    //  有线部分
    //====================================================

    public Observable<String> getEhternetStatus(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String msg = mUdpApiClient.sendRequest(FeiyuAPI.ETHERNET_STATUS,REQUEST,"getEhternetStatus");
                emitter.onNext(msg);
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }

    public Observable<String> setEthernet(final WAN1 data){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                JSONObject jsonObject = new JSONObject();
                JSONObject jsonObject2 = new JSONObject();
                jsonObject.put("fun", "setWanInfo");
                jsonObject.put("args", jsonObject2);
                jsonObject2.put("WAN1", data);
                jsonObject.put("stat", "ok");
                String msg = mUdpApiClient.sendRequest(jsonObject.toJSONString(),REQUEST,"setEthernet");
                emitter.onNext(msg);
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }

    //====================================================
    //  其他设置部分
    //====================================================

    public Observable<String> setNetRest(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String msg = mUdpApiClient.sendRequest(FeiyuAPI.NET_RESET,REQUEST,"setNetRest");
                emitter.onNext(msg);
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }

    public Observable<String> setNetReboot(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String msg = mUdpApiClient.sendRequest(FeiyuAPI.NET_REBOOT,REQUEST,"setNetReboot");
                emitter.onNext(msg);
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }

    public Observable<String> getNetUpdateInfo(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String msg = mUdpApiClient.sendRequest(FeiyuAPI.UPDATE_STATUS,REQUEST,"getNetUpdateInfo");
                emitter.onNext(msg);
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }

    public Observable<String> doUpdate(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String msg = mUdpApiClient.sendRequest(FeiyuAPI.DO_UPDATE,REQUEST,"doUpdate");
                emitter.onNext(msg);
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }

    public Observable<String> getAutoRebootStatus(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String msg = mUdpApiClient.sendRequest(FeiyuAPI.GET_AUTO_REBOOT_STATUS,REQUEST,"getAutoRebootStatus");
                emitter.onNext(msg);
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }

    public Observable<String> setAutoReboot(final AutoRebootInfo info){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String msg = mUdpApiClient.sendRequest(FeiyuAPI.setAutoReboot(info),REQUEST,"setAutoReboot");
                emitter.onNext(msg);
                emitter.onComplete();
            }
        }).compose(RxUtils.<String>rxScheduers());
    }

    public Observable<String> getBaseInfo(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String msg = mUdpApiClient.sendRequest(FeiyuAPI.GET_BASE_INFO,REQUEST,"getBaseInfo");
                emitter.onNext(msg);
                emitter.onComplete();

            }
        }).compose(RxUtils.<String>rxScheduers());
    }
}
