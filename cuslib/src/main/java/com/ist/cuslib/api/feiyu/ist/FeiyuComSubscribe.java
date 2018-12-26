package com.ist.cuslib.api.feiyu.ist;


import android.util.Log;

import com.ist.cuslib.api.feiyu.ist.callback.IBaseCallback;
import com.ist.cuslib.api.feiyu.ist.data.FeiyuDataConver;

import io.reactivex.observers.ResourceObserver;

public abstract class FeiyuComSubscribe<T> extends ResourceObserver<T> {
    private static final String TAG = "FeiyuComSubscribe";
    private IBaseCallback mIBaseListener;


    public FeiyuComSubscribe(IBaseCallback iview) {
        mIBaseListener = iview;

    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "zsr --> onError: "+e.toString());
        if (mIBaseListener != null){
            mIBaseListener.errorMsg(e.toString());
        }

        FeiyuDataConver.getInstance().stopReqeust();

    }

    @Override
    public void onComplete() {

    }
}