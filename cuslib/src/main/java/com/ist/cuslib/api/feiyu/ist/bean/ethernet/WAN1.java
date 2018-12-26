package com.ist.cuslib.api.feiyu.ist.bean.ethernet;

/**
 * Created by zhengshaorui
 * Time on 2018/11/22
 */

public class WAN1 {
    public String macaddr;

    public String enable;

    public String link;

    public String network_ok;

    public String wanType;

    public String wanIp;

    public String wanNetmask;

    public String wanGateway;

    public String wanDns1;

    public String wanDns2;

    public String dhcpType;

    public String dhcpDns1;

    public String dhcpDns2;



    @Override
    public String toString() {
        return "WAN1{" +
                "macaddr='" + macaddr + '\'' +
                ", enable='" + enable + '\'' +
                ", link='" + link + '\'' +
                ", network_ok='" + network_ok + '\'' +
                ", wanType='" + wanType + '\'' +
                ", wanIp='" + wanIp + '\'' +
                ", wanNetmask='" + wanNetmask + '\'' +
                ", wanGateway='" + wanGateway + '\'' +
                ", wanDns1='" + wanDns1 + '\'' +
                ", wanDns2='" + wanDns2 + '\'' +
                ", dhcpType='" + dhcpType + '\'' +
                ", dhcpDns1='" + dhcpDns1 + '\'' +
                ", dhcpDns2='" + dhcpDns2 + '\'' +
                '}';
    }
}
