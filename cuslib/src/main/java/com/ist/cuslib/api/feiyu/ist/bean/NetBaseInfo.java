package com.ist.cuslib.api.feiyu.ist.bean;

/**
 * Created by zhengshaorui
 * Time on 2018/11/29
 */

public class NetBaseInfo {
    public String state;
    public String info_upTime;
    public String info_version;
    public String sysTime;
    public String platform;
    public String ethernet_enable;
    public String ethernet_link;
    public String ethernet_network_ok;
    public String wlan_ssid;
    public String wlan_enable;
    public String wlan_network_ok;
    public String ap_enable;
    public String ap_ssid;
    public String ota_state;
    public String ota_ready;
    public String timeReboot;

    @Override
    public String toString() {
        return "NetBaseInfo{" +
                "state='" + state + '\'' +
                ", info_upTime='" + info_upTime + '\'' +
                ", info_version='" + info_version + '\'' +
                ", sysTime='" + sysTime + '\'' +
                ", platform='" + platform + '\'' +
                ", ethernet_enable='" + ethernet_enable + '\'' +
                ", ethernet_link='" + ethernet_link + '\'' +
                ", ethernet_network_ok='" + ethernet_network_ok + '\'' +
                ", wlan_ssid='" + wlan_ssid + '\'' +
                ", wlan_enable='" + wlan_enable + '\'' +
                ", wlan_network_ok='" + wlan_network_ok + '\'' +
                ", ap_enable='" + ap_enable + '\'' +
                ", ap_ssid='" + ap_ssid + '\'' +
                ", ota_state='" + ota_state + '\'' +
                ", ota_ready='" + ota_ready + '\'' +
                ", timeReboot='" + timeReboot + '\'' +
                '}';
    }
}
