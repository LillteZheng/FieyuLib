package com.ist.cuslib.api.feiyu.ist.bean.ethernet;

/**
 * Created by zhengshaorui
 * Time on 2018/11/22
 */

public class EthernetInfo {
    private WAN1 WAN1;

    public void setWAN1(WAN1 WAN1){
        this.WAN1 = WAN1;
    }
    public WAN1 getWAN1(){
        return this.WAN1;
    }


    @Override
    public String toString() {
        return "EthernetInfo{" +
                "WAN1=" + WAN1 +
                '}';
    }
}
