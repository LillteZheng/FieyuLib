package com.ist.cuslib.api.feiyu.ist.bean.wifi;

import java.util.List;

/**
 * Created by zhengshaorui
 * Time on 2018/10/23
 */

public class WifiInfoData {
    private String macaddr;

    private List<Wlsite> wlsite ;

    public void setMacaddr(String macaddr){
        this.macaddr = macaddr;
    }
    public String getMacaddr(){
        return this.macaddr;
    }
    public void setWlsite(List<Wlsite> wlsite){
        this.wlsite = wlsite;
    }
    public List<Wlsite> getWlsite(){
        return this.wlsite;
    }

    @Override
    public String toString() {
        return "WifiInfoData{" +
                "macaddr='" + macaddr + '\'' +
                ", wlsite=" + wlsite +
                '}';
    }
}
