package com.ist.cuslib.api.feiyu.ist.bean;

/**
 * Created by zhengshaorui
 * Time on 2018/10/23
 */

public class BaseResponse<T> {
    public String fun;
    public String stat;
    public T cbk;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "fun='" + fun + '\'' +
                ", stat='" + stat + '\'' +
                ", cbk=" + cbk +
                '}';
    }
}
