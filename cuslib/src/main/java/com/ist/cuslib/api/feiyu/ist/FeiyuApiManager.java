package com.ist.cuslib.api.feiyu.ist;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ist.cuslib.api.feiyu.feiyu.IpMacUtils;
import com.ist.cuslib.api.feiyu.feiyu.UdpApiClient;
import com.ist.cuslib.api.feiyu.ist.bean.NetBaseInfo;
import com.ist.cuslib.api.feiyu.ist.bean.ethernet.WAN1;
import com.ist.cuslib.api.feiyu.ist.bean.others.AutoRebootInfo;
import com.ist.cuslib.api.feiyu.ist.callback.IAllInfoCallback;
import com.ist.cuslib.api.feiyu.ist.callback.IBaseCallback;
import com.ist.cuslib.api.feiyu.ist.callback.IEthernetCallback;
import com.ist.cuslib.api.feiyu.ist.callback.IHotCallback;
import com.ist.cuslib.api.feiyu.ist.callback.INetOthersCallback;
import com.ist.cuslib.api.feiyu.ist.callback.IWlanCallback;
import com.ist.cuslib.api.feiyu.ist.data.EthernetDataConver;
import com.ist.cuslib.api.feiyu.ist.data.FeiyuDataConver;
import com.ist.cuslib.api.feiyu.ist.data.HotDataConver;
import com.ist.cuslib.api.feiyu.ist.data.NetBaseConvert;
import com.ist.cuslib.api.feiyu.ist.data.NetOthersDataConver;
import com.ist.cuslib.api.feiyu.ist.data.WlanDataConver;


/**
 * Created by zhengshaorui
 * Time on 2018/10/20
 */

public class FeiyuApiManager extends NetBaseConvert {
    private static final String TAG = "FeiyuApiManager";
    public static final String SCAN_WLAN_KEY = "getWifiList";
    private FeiyuDataConver mDataConver;
    private boolean isDeviceExsits = false;
    private IAllInfoCallback mCallback;
    private Context mContext;


    private static class Holder {
        static final FeiyuApiManager INSTANCE = new FeiyuApiManager();
    }

    public static FeiyuApiManager getInstance() {
        return Holder.INSTANCE;
    }

    private FeiyuApiManager() {
        mDataConver = FeiyuDataConver.getInstance();
    }


    public void setFeiyuDeviceExsits(boolean isExsits) {
        this.isDeviceExsits = isExsits;
    }

    public boolean isFeiyuDeviceExsits() {
        return isDeviceExsits;
    }


    public void stopReuest() {
        UdpApiClient.getInstance().stop();
    }


    public Context getContext(){
        return mContext;
    }

    public void registerListener(IBaseCallback callback) {

        if (callback instanceof IAllInfoCallback) {
            mCallback = (IAllInfoCallback) callback;
        }

        if (callback instanceof IWlanCallback) {
            IWlanCallback listener = (IWlanCallback) callback;
            WlanDataConver.getInstance().registerListener(listener);
        }
        if (callback instanceof IHotCallback) {
            IHotCallback listener = (IHotCallback) callback;
            HotDataConver.getInstance().registerListener(listener);
        }
        if (callback instanceof IEthernetCallback) {
            IEthernetCallback listener = (IEthernetCallback) callback;
            EthernetDataConver.getInstance().registerListener(listener);
        }
        if (callback instanceof INetOthersCallback) {
            INetOthersCallback listener = (INetOthersCallback) callback;
            NetOthersDataConver.getInstance().registerListener(listener);
        }
    }

    public void initFeiyuSocket(Context context) {
        mContext = context;
        UdpApiClient.init(IpMacUtils.getGateway(context), IpMacUtils.getMacAddress(context), 10081, 20000);
    }


    // ============================================================
    //  WIFI  方法
    //=============================================================

    /**
     * 是否开启wifi
     *
     * @param isOpen
     */
    public void enableWifi(final boolean isOpen) {
        WlanDataConver.getInstance().enableWifi(isOpen);
    }

    /**
     * 获取wifi列表
     */
    public void getWifiStatus() {
        WlanDataConver.getInstance().getWifiStatus();
    }

    public void getWifiList(){
        WlanDataConver.getInstance().getWifiList();
    }


    /**
     * 添加一个wifi
     *
     * @param info
     */
    public void addWifi(JSONObject info) {
        WlanDataConver.getInstance().connectWifi(info);
    }


    // ============================================================
    //  热点  方法
    //=============================================================

    /**
     * 获取热点信息
     */
    public void getHotPotStatus() {
        HotDataConver.getInstance().get5GHotpotStatus();
    }

    /**
     * 设置热点信息
     *
     * @param json
     * @param ssidChannel
     */
    public void set5GHotpot(String json, String ssidChannel) {
        HotDataConver.getInstance().set5Ghotpot(json, ssidChannel, true);
    }

    /**
     * 设置热点开关
     *
     * @param json
     * @param ssidChannel
     */
    public void enable5G(String json, String ssidChannel) {
        HotDataConver.getInstance().set5Ghotpot(json, ssidChannel, false);
    }


    // ============================================================
    //  有线  方法
    //=============================================================

    /**
     * 获取有线状态信息
     */
    public void getEthernetStatus() {
        EthernetDataConver.getInstance().getEthernetStatus();
    }

    /**
     * 设置有线
     * @param data
     */
    public void setEthernet(WAN1 data) {
        EthernetDataConver.getInstance().setEthernet(data);
    }


    // ============================================================
    //  其他设置  方法
    //=============================================================

    /**
     * 复位
     */
    public void setNetRest() {
        NetOthersDataConver.getInstance().setNetReset();
    }

    /**
     * 重启
     */
    public void setNetReboot() {
        NetOthersDataConver.getInstance().setNetReboot(false);
    }

    /**
     * 获取升级信息
     */
    public void getUpdateStatus() {
        NetOthersDataConver.getInstance().getNetUpdateInfo();
    }

    /**
     * 升级
     */
    public void doUpdte() {
        NetOthersDataConver.getInstance().doUpdate();
    }

    /**
     * 获取定时设置状态，名字没起好
     */
    public void getAutoRebootStatus() {
        NetOthersDataConver.getInstance().getAutoRebootStatus();
    }

    /**
     * 设置定时信息
     * @param info
     */
    public void setAutoReboot(AutoRebootInfo info) {
        NetOthersDataConver.getInstance().setAutoReboot(info);
    }

    /**
     * 获取所有信息
     */
    public void getBaseInfo() {
        addSubsribe(
                FeiyuDataConver.getInstance().getBaseInfo()
                        .subscribeWith(new FeiyuComSubscribe<String>(mCallback) {
                            @Override
                            public void onNext(String s) {
                                if (!TextUtils.isEmpty(s)) {
                                    try {
                                        JSONObject jsonObject = JSON.parseObject(s);
                                        NetBaseInfo info = new NetBaseInfo();
                                        info.state = jsonObject.getString("stat");
                                        JSONObject cbkObject = jsonObject.getJSONObject("cbk");

                                        JSONObject baseObject = cbkObject.getJSONObject("base");

                                        info.info_upTime = baseObject.getString("upTime");
                                        info.info_version = baseObject.getString("version");
                                        info.sysTime = baseObject.getString("sysTime");
                                        info.platform = baseObject.getString("platform");

                                        JSONObject ethernetObject = cbkObject.getJSONObject("wiredwan");
                                        info.ethernet_enable = ethernetObject.getString("enable");
                                        info.ethernet_link = ethernetObject.getString("link");
                                        info.ethernet_network_ok = ethernetObject.getString("network_ok");

                                        JSONObject wlanObject = cbkObject.getJSONObject("wisp");
                                        info.wlan_enable = wlanObject.getString("enable");
                                        info.wlan_ssid = wlanObject.getString("wispSsid");
                                        info.wlan_network_ok = wlanObject.getString("network_ok");

                                        JSONObject hotObject = cbkObject.getJSONObject("wifi");
                                        info.ap_enable = hotObject.getString("enable");
                                        info.ap_ssid = hotObject.getString("ssidName");

                                        JSONObject otaObject = cbkObject.getJSONObject("ota");
                                        info.ota_state = otaObject.getString("ota_state");
                                        info.ota_ready = otaObject.getString("ota_ready");
                                        info.timeReboot = cbkObject.getJSONObject("timeReboot").getString("enable");

                                        if (mCallback != null){
                                            mCallback.getAllInfo(info);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        if (mCallback != null) {
                                            mCallback.errorMsg("getBaseInfo" + e.toString());
                                        }
                                    }

                                } else {
                                    if (mCallback != null) {
                                        mCallback.errorMsg("getBaseInfo" + ERROR_JSON);
                                    }
                                }

                            }
                        })
        );

    }
    public void disposeableKey(String key){
        disposable(key);
    }
}
