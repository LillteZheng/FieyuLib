package com.ist.cuslib.api.feiyu.ist.bean.hotpot;

public class Ssid {
    private String macaddr;

    private String ssidCrypto;

    private String ssidEn;

    private String ssidHide;

    private String ssidName;

    private String ssidNum;

    private String ssidPw;

    public void setMacaddr(String macaddr){
        this.macaddr = macaddr;
    }
    public String getMacaddr(){
        return this.macaddr;
    }
    public void setSsidCrypto(String ssidCrypto){
        this.ssidCrypto = ssidCrypto;
    }
    public String getSsidCrypto(){
        return this.ssidCrypto;
    }
    public void setSsidEn(String ssidEn){
        this.ssidEn = ssidEn;
    }
    public String getSsidEn(){
        return this.ssidEn;
    }
    public void setSsidHide(String ssidHide){
        this.ssidHide = ssidHide;
    }
    public String getSsidHide(){
        return this.ssidHide;
    }
    public void setSsidName(String ssidName){
        this.ssidName = ssidName;
    }
    public String getSsidName(){
        return this.ssidName;
    }
    public void setSsidNum(String ssidNum){
        this.ssidNum = ssidNum;
    }
    public String getSsidNum(){
        return this.ssidNum;
    }
    public void setSsidPw(String ssidPw){
        this.ssidPw = ssidPw;
    }
    public String getSsidPw(){
        return this.ssidPw;
    }

    @Override
    public String toString() {
        return "Ssid{" +
                "macaddr='" + macaddr + '\'' +
                ", ssidCrypto='" + ssidCrypto + '\'' +
                ", ssidEn='" + ssidEn + '\'' +
                ", ssidHide='" + ssidHide + '\'' +
                ", ssidName='" + ssidName + '\'' +
                ", ssidNum='" + ssidNum + '\'' +
                ", ssidPw='" + ssidPw + '\'' +
                '}';
    }
}