package com.ist.cuslib.api.feiyu.ist.bean.others;

/**
 * Created by zhengshaorui
 * Time on 2018/11/24
 */

public class UpdateInfo {
    private String cur_fw_ver;

    private String ota_state;

    private String ota_ready;

    private String ota_fw_des;

    private String ota_fw_ver;

    public void setCur_fw_ver(String cur_fw_ver){
        this.cur_fw_ver = cur_fw_ver;
    }
    public String getCur_fw_ver(){
        return this.cur_fw_ver;
    }
    public void setOta_state(String ota_state){
        this.ota_state = ota_state;
    }
    public String getOta_state(){
        return this.ota_state;
    }
    public void setOta_ready(String ota_ready){
        this.ota_ready = ota_ready;
    }
    public String getOta_ready(){
        return this.ota_ready;
    }
    public void setOta_fw_des(String ota_fw_des){
        this.ota_fw_des = ota_fw_des;
    }
    public String getOta_fw_des(){
        return this.ota_fw_des;
    }
    public void setOta_fw_ver(String ota_fw_ver){
        this.ota_fw_ver = ota_fw_ver;
    }
    public String getOta_fw_ver(){
        return this.ota_fw_ver;
    }


    @Override
    public String toString() {
        return "UpdateInfo{" +
                "cur_fw_ver='" + cur_fw_ver + '\'' +
                ", ota_state='" + ota_state + '\'' +
                ", ota_ready='" + ota_ready + '\'' +
                ", ota_fw_des='" + ota_fw_des + '\'' +
                ", ota_fw_ver='" + ota_fw_ver + '\'' +
                '}';
    }
}
