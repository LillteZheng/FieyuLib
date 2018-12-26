package com.ist.cuslib.api.feiyu.ist;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ist.cuslib.api.feiyu.ist.bean.others.AutoRebootInfo;

/**
 * Created by zhengshaorui
 * Time on 2018/11/19
 */

public class FeiyuAPI {
    private static final String TAG = "FeiyuAPI";
    //========================================================
    // 获取 WIFI 的接口
    //========================================================
    /**
     *  获取设备基本信息
     */
    public static final String WIFI_STAUTS = "{\"fun\":\"getwwanStatus2G\",\"args\":{}}";
    /**
     * 获取2.4Gwifi信息
     */
    public static final String WIFI_INFO = "{\"fun\":\"getwlsite2G\",\"args\":{}}";

    /**
     * 设置 wifi 2.4G 开关
     * @param isopen
     * @return
     */
    public static String open24GWIFI(boolean isopen){
        JSONObject res = new JSONObject();
        res.put("enable", isopen ? "YES" : "NO");
        return "{\"fun\":\"setwwan2G\",\"args\":" + res.toJSONString() + "}";
    }

    public static String set2Gwifi(JSONObject info){
        return "{\"fun\":\"setwwan2G\",\"args\":" + info.toJSONString() + "}";
    }


    //=============================================
    //           热点 API
    //=============================================


    public static String HOTPOT_STATUS = "{\"fun\":\"getWifiInfo5G \",\"args\":{}}";

    public static String set5GHotPot(String json,String ssidChannel){
        JSONObject ssidAd = new JSONObject();
        ssidAd.put("ssidChannel", ssidChannel);
        //传递的是一个 json 数组
        json = "["+json+"]";
       // Log.d(TAG, "zsr --> set5GHotPot: "+"{\"fun\":\"setWifiInfo5G\",\"args\":{\"ssid\":" + json + ",\"ssidAd\":" + ssidAd.toJSONString() + "}}");
        return "{\"fun\":\"setWifiInfo5G\",\"args\":{\"ssid\":" + json + ",\"ssidAd\":" + ssidAd.toJSONString() + "}}";
    }

    //=============================================
    //          有线 API
    //=============================================

    public static String ETHERNET_STATUS = "{\"fun\":\"getWanInfo\",\"args\":{}}";

    //=============================================
    //          其他设置 API
    //=============================================

    public static String NET_RESET = "{\"fun\":\"setFactory\",\"args\":{}}";
    public static String NET_REBOOT = "{\"fun\":\"setReboot\",\"args\":{}}";
    public static String UPDATE_STATUS = "{\"fun\":\"getUpdateInfo\",\"args\":{}}";
    public static String DO_UPDATE = "{\"fun\":\"setDoupdate\",\"args\":{}}";
    public static String GET_AUTO_REBOOT_STATUS = "{\"fun\":\"getAutoReboot\",\"args\":{}}";

    public static String setAutoReboot(AutoRebootInfo info){
        String json = JSON.toJSONString(info);
        String msg = "{\"fun\":\"setAutoReboot\",\"args\":" + json + "}";
        return msg;
    }


    public static String GET_BASE_INFO = "{\"fun\":\"getBaseInfo \",\"args\":{}}";

}
