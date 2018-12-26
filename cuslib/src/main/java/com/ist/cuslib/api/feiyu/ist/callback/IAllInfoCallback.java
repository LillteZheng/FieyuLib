package com.ist.cuslib.api.feiyu.ist.callback;

import com.ist.cuslib.api.feiyu.ist.bean.NetBaseInfo;

/**
 * Created by zhengshaorui
 * Time on 2018/11/29
 */

public interface IAllInfoCallback extends IBaseCallback {
    void getAllInfo(NetBaseInfo info);
}
