package com.ist.cuslib.api.feiyu.ist.callback;

import com.ist.cuslib.api.feiyu.ist.bean.BaseResponse;
import com.ist.cuslib.api.feiyu.ist.bean.hotpot.HotPotInfo;

/**
 * Created by zhengshaorui
 * Time on 2018/11/20
 */

public interface IHotCallback extends IBaseCallback {
    void getHotpotStatus(BaseResponse<HotPotInfo> response);

}
