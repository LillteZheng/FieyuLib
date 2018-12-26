package com.ist.cuslib.api.feiyu.ist.callback;

import com.ist.cuslib.api.feiyu.ist.NetStatus;

/**
 * Created by zhengshaorui
 * Time on 2018/10/23
 */

public interface IBaseCallback {
    void connectStatus(NetStatus status);
    void errorMsg(String errorMsg);
}
