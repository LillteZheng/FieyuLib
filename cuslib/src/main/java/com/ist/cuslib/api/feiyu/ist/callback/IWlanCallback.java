package com.ist.cuslib.api.feiyu.ist.callback;

import com.ist.cuslib.api.feiyu.ist.bean.BaseResponse;
import com.ist.cuslib.api.feiyu.ist.bean.wifi.WifiInfoData;
import com.ist.cuslib.api.feiyu.ist.bean.wifi.WifiStatusData;

/**
 * Created by zhengshaorui
 * Time on 2018/10/23
 */

public interface IWlanCallback extends IBaseCallback {
    void getWifiStatus(BaseResponse<WifiStatusData> data);
    void scanWifiSuccess(WifiInfoData data);
}
