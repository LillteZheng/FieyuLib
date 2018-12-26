package com.ist.cuslib.api.feiyu.ist.callback;

import com.ist.cuslib.api.feiyu.ist.bean.BaseResponse;
import com.ist.cuslib.api.feiyu.ist.bean.ethernet.EthernetInfo;

/**
 * Created by zhengshaorui
 * Time on 2018/11/20
 */

public interface IEthernetCallback extends IBaseCallback {
   void getEthernetStatus(BaseResponse<EthernetInfo> response);

}
