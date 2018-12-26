package com.ist.cuslib.api.feiyu.ist.callback;

import com.ist.cuslib.api.feiyu.ist.bean.BaseResponse;
import com.ist.cuslib.api.feiyu.ist.bean.others.AutoRebootInfo;
import com.ist.cuslib.api.feiyu.ist.bean.others.UpdateInfo;

/**
 * Created by zhengshaorui
 * Time on 2018/11/20
 */

public interface INetOthersCallback extends IBaseCallback {
    void getUpdateInfo(BaseResponse<UpdateInfo> response);
    void getAutoRebootInfo(BaseResponse<AutoRebootInfo> response);
}
