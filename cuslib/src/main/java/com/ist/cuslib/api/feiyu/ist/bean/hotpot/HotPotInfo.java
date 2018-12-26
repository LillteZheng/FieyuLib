package com.ist.cuslib.api.feiyu.ist.bean.hotpot;

import java.util.List;

public class HotPotInfo {
    private List<Ssid> ssid;

    private SsidAd ssidAd;

    public void setSsid(List<Ssid> ssid) {
        this.ssid = ssid;
    }

    public List<Ssid> getSsid() {
        return this.ssid;
    }

    public void setSsidAd(SsidAd ssidAd) {
        this.ssidAd = ssidAd;
    }

    public SsidAd getSsidAd() {
        return this.ssidAd;
    }


    @Override
    public String toString() {
        return "HotPotInfo{" +
                "ssid=" + ssid +
                ", ssidAd=" + ssidAd +
                '}';
    }
}