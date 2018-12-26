package com.ist.cuslib.api.feiyu.ist.bean.wifi;

/**
 * Created by zhengshaorui
 * Time on 2018/10/24
 */

public class WifiStatusData {
    public String wispState;

    public String macaddr;

    public String enable;

    public String wispBssid;

    public String wispPswd;

    public String wispIpType;

    public String wispIpaddr;

    public String wispNetmask;

    public String wispGateway;

    public String wispDns1;

    public String wispDns2;


    @Override
    public String toString() {
        return "WifiStatusData{" +
                "wispState='" + wispState + '\'' +
                ", macaddr='" + macaddr + '\'' +
                ", enable='" + enable + '\'' +
                ", wispBssid='" + wispBssid + '\'' +
                ", wispPswd='" + wispPswd + '\'' +
                ", wispIpType='" + wispIpType + '\'' +
                ", wispIpaddr='" + wispIpaddr + '\'' +
                ", wispNetmask='" + wispNetmask + '\'' +
                ", wispGateway='" + wispGateway + '\'' +
                ", wispDns1='" + wispDns1 + '\'' +
                ", wispDns2='" + wispDns2 + '\'' +
                '}';
    }
}
