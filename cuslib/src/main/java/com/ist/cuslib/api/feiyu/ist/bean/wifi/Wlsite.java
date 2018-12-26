package com.ist.cuslib.api.feiyu.ist.bean.wifi;

public class Wlsite {
    private String SSID;

    private String AUTHTYPE;

    private String SIGNAL;

    private String ENCRYPT;

    private String BSSID;

    private String MODE;


    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getSSID() {
        return this.SSID;
    }

    public void setAUTHTYPE(String AUTHTYPE) {
        this.AUTHTYPE = AUTHTYPE;
    }

    public String getAUTHTYPE() {
        return this.AUTHTYPE;
    }

    public void setSIGNAL(String SIGNAL) {
        this.SIGNAL = SIGNAL;
    }

    public String getSIGNAL() {
        return this.SIGNAL;
    }

    public void setENCRYPT(String ENCRYPT) {
        this.ENCRYPT = ENCRYPT;
    }

    public String getENCRYPT() {
        return this.ENCRYPT;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public String getBSSID() {
        return this.BSSID;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }

    public String getMODE() {
        return this.MODE;
    }


    @Override
    public String toString() {
        return "Wlsite{" +
                "SSID='" + SSID + '\'' +
                ", AUTHTYPE='" + AUTHTYPE + '\'' +
                ", SIGNAL='" + SIGNAL + '\'' +
                ", ENCRYPT='" + ENCRYPT + '\'' +
                ", BSSID='" + BSSID + '\'' +
                ", MODE='" + MODE + '\'' +
                '}';
    }
}