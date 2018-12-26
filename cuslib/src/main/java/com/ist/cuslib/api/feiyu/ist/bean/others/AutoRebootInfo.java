package com.ist.cuslib.api.feiyu.ist.bean.others;

/**
 * Created by zhengshaorui
 * Time on 2018/11/26
 */

public class AutoRebootInfo {
    private String enable;

    private String time;

    private String days;

    private String timezone;

    private String time_srv;

    private String systime;

    public void setEnable(String enable){
        this.enable = enable;
    }
    public String getEnable(){
        return this.enable;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getTime(){
        return this.time;
    }
    public void setDays(String days){
        this.days = days;
    }
    public String getDays(){
        return this.days;
    }
    public void setTimezone(String timezone){
        this.timezone = timezone;
    }
    public String getTimezone(){
        return this.timezone;
    }
    public void setTime_srv(String time_srv){
        this.time_srv = time_srv;
    }
    public String getTime_srv(){
        return this.time_srv;
    }
    public void setSystime(String systime){
        this.systime = systime;
    }
    public String getSystime(){
        return this.systime;
    }

    @Override
    public String toString() {
        return "AutoRebootInfo{" +
                "enable='" + enable + '\'' +
                ", time='" + time + '\'' +
                ", days='" + days + '\'' +
                ", timezone='" + timezone + '\'' +
                ", time_srv='" + time_srv + '\'' +
                ", systime='" + systime + '\'' +
                '}';
    }
}
