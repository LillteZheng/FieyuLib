package com.ist.cuslib.api.feiyu.ist.bean.hotpot;

public class SsidAd {
    private String ssidBand;

    private String ssidChannel;

    private String ssidStanum;

    private String ssidTxpower;

    private String ssidWidth;

    public void setSsidBand(String ssidBand){
        this.ssidBand = ssidBand;
    }
    public String getSsidBand(){
        return this.ssidBand;
    }
    public void setSsidChannel(String ssidChannel){
        this.ssidChannel = ssidChannel;
    }
    public String getSsidChannel(){
        return this.ssidChannel;
    }
    public void setSsidStanum(String ssidStanum){
        this.ssidStanum = ssidStanum;
    }
    public String getSsidStanum(){
        return this.ssidStanum;
    }
    public void setSsidTxpower(String ssidTxpower){
        this.ssidTxpower = ssidTxpower;
    }
    public String getSsidTxpower(){
        return this.ssidTxpower;
    }
    public void setSsidWidth(String ssidWidth){
        this.ssidWidth = ssidWidth;
    }
    public String getSsidWidth(){
        return this.ssidWidth;
    }

    @Override
    public String toString() {
        return "SsidAd{" +
                "ssidBand='" + ssidBand + '\'' +
                ", ssidChannel='" + ssidChannel + '\'' +
                ", ssidStanum='" + ssidStanum + '\'' +
                ", ssidTxpower='" + ssidTxpower + '\'' +
                ", ssidWidth='" + ssidWidth + '\'' +
                '}';
    }
}