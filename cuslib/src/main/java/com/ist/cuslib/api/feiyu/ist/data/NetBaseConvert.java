package com.ist.cuslib.api.feiyu.ist.data;

import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhengshaorui
 * Time on 2018/11/19
 */

public class NetBaseConvert {
    private static final String TAG = "NetBaseConvert";
    protected static final String ERROR_JSON = "get josn null";
    protected static final String LOW_VERSION = "low version or cannot get json";
    private CompositeDisposable mDisposable;
    private WeakHashMap<String,Disposable> mWeakHashMap = new WeakHashMap<>();


    /**
     * 通过一个 key 来判断上一次是否存在了 Disposable
     * 在网络的请求中，为了防止重复请求和及时取消，应该添加 dispose
     * @param disposable
     * @param key 一般用方法名即可
     * @return
     */
    protected Disposable addSubsribe(Disposable disposable,String key){
        if (mDisposable == null){
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(disposable);

        if (isExsits(key)){
            //把上一次的 dispose 掉
            mWeakHashMap.get(key).dispose();
            mWeakHashMap.remove(key);
            mWeakHashMap.put(key,disposable);
        }else{
            mWeakHashMap.put(key,disposable);
        }

        return disposable;
    }

    /**
     * 取消所有订阅，比如关闭某个开关，或者跳到另外的界面，以前界面的订阅就需要取消时
     * @param disposable
     * @param key
     * @param disposeAll
     * @return
     */
    protected Disposable addSubsribe(Disposable disposable,String key,boolean disposeAll){
        if (mDisposable == null){
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(disposable);

        if (disposeAll){
            //取消订阅所有
            Set<Map.Entry<String, Disposable>> entries = mWeakHashMap.entrySet();
            for (Map.Entry<String, Disposable> entry : entries) {
                entry.getValue().dispose();
            }
        }
        mWeakHashMap.put(key,disposable);

        return disposable;
    }


    /**
     * 订阅，不需要取消
     * @param disposable
     * @return
     */
    protected Disposable addSubsribe(Disposable disposable){
        if (mDisposable == null){
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(disposable);

        return disposable;
    }

    private boolean isExsits(String key){
        Set<Map.Entry<String, Disposable>> entries = mWeakHashMap.entrySet();
        for (Map.Entry<String, Disposable> entry : entries) {
            if (entry.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    /**
     * 关闭所有
     */
    protected void clearSubscribe(){
        if (mDisposable != null){
            mDisposable.dispose();
            mDisposable.clear();
        }
        if (mWeakHashMap != null){
            mWeakHashMap.clear();
        }

    }
    /**
     * 如果存在则取消
     * @param key
     */
    public void disposable(String key){
        if (isExsits(key)) {
            mWeakHashMap.get(key).dispose();
        }
    }
}
