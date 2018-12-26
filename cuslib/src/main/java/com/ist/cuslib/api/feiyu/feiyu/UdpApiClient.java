//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ist.cuslib.api.feiyu.feiyu;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ist.cuslib.api.feiyu.feiyu.entity.vmanage_vrpchdr;
import com.ist.cuslib.api.feiyu.feiyu.entity.vmanage_vrpcmsg;

import java.lang.ref.SoftReference;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UdpApiClient {
    public static final String TAG = "SZH";
    private static UdpApiClient mInstance;
    private static int mTimeout = 12000;
    private static String mIp = "127.0.0.1";
    private static String mMac = "00:00:00:00:00:00";
    private static int mPort = 10081;
    private static Map<String, SoftReference<DatagramSocket>> socketList = new HashMap();

    public static void init(String ip, String mac) {
        init(ip, mac, mPort, mTimeout);
    }

    public static void init(String ip, String mac, int port, int timeout) {
        Log.e("SZH", "init: " + ip + " " + mac + " " + port + " " + timeout);
        setIp(ip);
        setMac(mac);
        setPort(port);
        setTimeout(timeout);
    }

    public static void setTimeout(int timeout) {
        mTimeout = timeout;
    }

    public static void setIp(String ip) {
        mIp = ip;
    }

    public static void setMac(String mac) {
        mMac = mac;
    }

    public static void setPort(int port) {
        mPort = port;
    }

    private UdpApiClient() {
    }

    public Observable<String> getwlsite2G() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(UdpApiClient.this.sendRequest("{\"fun\":\"getwlsite2G\",\"args\":{}}", 'Q', "getwlsite2G"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> getwwanStatus2G() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(UdpApiClient.this.sendRequest("{\"fun\":\"getwwanStatus2G\",\"args\":{}}", 'Q', "getwwanStatus2G"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> setwwan2G(final JSONObject Info) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(UdpApiClient.this.sendRequest("{\"fun\":\"setwwan2G\",\"args\":" + Info.toJSONString() + "}", 'Q', "setwwan2G"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> getWanInfo() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(UdpApiClient.this.sendRequest("{\"fun\":\"getWanInfo\",\"args\":{}}", 'Q', "getWanInfo"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> setWanInfo(final JSONObject Info) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                JSONObject jsonObject = new JSONObject();
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("WAN1", Info);
                jsonObject.put("fun", "setWanInfo");
                jsonObject.put("args", jsonObject2);
                jsonObject.put("stat", "ok");
                e.onNext(UdpApiClient.this.sendRequest(jsonObject.toJSONString(), 'Q', "setWanInfo"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> getWifiInfo5G() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(UdpApiClient.this.sendRequest("{\"fun\":\"getWifiInfo5G\",\"args\":{}}", 'Q', "getWifiInfo5G"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> setWifiInfo5G(final JSONArray mArray, final String ssidChannel) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                JSONObject ssidAd = new JSONObject();
                ssidAd.put("ssidChannel", ssidChannel);
                e.onNext(UdpApiClient.this.sendRequest("{\"fun\":\"setWifiInfo5G\",\"args\":{\"ssid\":" + mArray.toJSONString() + ",\"ssidAd\":" + ssidAd.toJSONString() + "}}", 'Q', "setWifiInfo5G"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> setFactory() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(UdpApiClient.this.sendRequest("{\"fun\":\"setFactory\",\"args\":{}}", 'Q', "setFactory"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> getUpdateInfo() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(UdpApiClient.this.sendRequest("{\"fun\":\"getUpdateInfo\",\"args\":{}}", 'Q', "getUpdateInfo"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> setDoupdate() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(UdpApiClient.this.sendRequest("{\"fun\":\"setDoupdate\",\"args\":{}}", 'Q', "setDoupdate"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> getAutoReboot() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(UdpApiClient.this.sendRequest("{\"fun\":\"getAutoReboot\",\"args\":{}}", 'Q', "getAutoReboot"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> setAutoReboot(final JSONObject Info) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(UdpApiClient.this.sendRequest("{\"fun\":\"setAutoReboot\",\"args\":" + Info.toJSONString() + "}", 'Q', "setAutoReboot"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> setReboot() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(UdpApiClient.this.sendRequest("{\"fun\":\"setReboot\",\"args\":{}}", 'Q', "setReboot"));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static synchronized UdpApiClient getInstance() {
        if(mInstance == null) {
            mInstance = new UdpApiClient();
        }

        return mInstance;
    }

    public String sendRequest(String requestJson, char operation, String key) {
        return this.sendRequest(requestJson, operation, 1, 3, key);
    }

    public String sendRequest(String requestJson, char operation, int count, int maxCount, String key) {
        try {
            LinkedList<byte[]> byte_list = new LinkedList();
            vmanage_vrpcmsg vrpcmsg = new vmanage_vrpcmsg();
            vmanage_vrpchdr vrpchdr = new vmanage_vrpchdr();
            vrpchdr.mac = mMac;
            vrpchdr.operation = operation;
            int msglen = requestJson.getBytes().length + vrpcmsg.vrpchdrlen;
            int maxlen = 1000;
            LinkedList mList;
            if(msglen > maxlen) {
                mList = this.segmentation(requestJson, maxlen - vrpcmsg.vrpchdrlen);
            } else {
                mList = new LinkedList();
                mList.addLast(requestJson);
            }

            int len = mList.size();
            if(len <= 1) {
                vrpchdr.pktmore = 1;
            } else {
                vrpchdr.pktmore = 2;
            }

            for(int i = 0; i < len; ++i) {
                vrpchdr.pktcnt = (byte)len;
                vrpchdr.pktindex = (byte)(i + 1);
                vrpcmsg.vrpcmsg = (String)mList.get(i);
                vrpcmsg.vrpchdr = vrpchdr;
                byte_list.addLast(vrpcmsg.toByte());
            }
           // Log.d(TAG, "zsr --> sendRequest: "+requestJson);
            return this.sendRequest(byte_list, count, maxCount, key);
        } catch (Exception var14) {
            var14.printStackTrace();
            Log.d(TAG, "zsr --> sendRequest error: "+var14.toString());
            return "";
        }
    }

    public LinkedList<String> segmentation(String str, int len) {
        int startlen = 0;
        int endlen = len;
        LinkedList mList = new LinkedList();

        while(startlen != str.length()) {
            mList.add(str.substring(startlen, endlen));
            startlen = endlen;
            endlen += len;
            if(endlen > str.length()) {
                endlen = str.length();
            }
        }

        return mList;
    }

    private String sendRequest(LinkedList<byte[]> byte_list, int count, int maxCount, String key) {
        boolean isSuccess = false;

        try {
            DatagramSocket mSocket = new DatagramSocket();
            mSocket.setSoTimeout(mTimeout);
            socketList.put(key, new SoftReference(mSocket));

            try {
                byte[] receive;
                DatagramPacket incomePacket;
                for(int i = 0; i < byte_list.size(); ++i) {
                    receive = (byte[])byte_list.get(i);
                    incomePacket = new DatagramPacket(receive, receive.length, InetAddress.getByName(mIp), mPort);
                    mSocket.send(incomePacket);
                }

                vmanage_vrpcmsg vrpcmsg = new vmanage_vrpcmsg();
                receive = new byte[2048];

                do {
                    incomePacket = new DatagramPacket(receive, receive.length);
                    mSocket.receive(incomePacket);
                    byte[] data = this.subBytes(incomePacket.getData(), 0, incomePacket.getLength());
                    vrpcmsg.toEntity(data);
                } while(!vrpcmsg.isComplete());

                isSuccess = true;
                String var19 = vrpcmsg.vrpcmsg;
                return var19;
            } catch (Exception var15) {
                var15.printStackTrace();
            }
        } catch (Exception var16) {
            var16.printStackTrace();
            Log.d(TAG, "zsr -->12 sendRequest error: "+var16.toString());
        } finally {
            if(socketList != null && socketList.size() > 0) {
                this.onStop(key);
                if(!isSuccess && count < maxCount) {
                    ++count;
                    return this.sendRequest(byte_list, count, maxCount, key);
                }
            }

        }

        return "";
    }

    private synchronized void onStop(String key) {
        try {
            if(socketList != null && socketList.size() > 0) {
                Iterator it = socketList.entrySet().iterator();

                while(true) {
                    Entry entry;
                    do {
                        if(!it.hasNext()) {
                            if(TextUtils.isEmpty(key)) {
                                socketList.clear();
                            }

                            return;
                        }

                        entry = (Entry)it.next();
                    } while(!TextUtils.isEmpty(key) && !((String)entry.getKey()).equalsIgnoreCase(key));

                    SoftReference<DatagramSocket> softReference = (SoftReference)entry.getValue();
                    if(softReference != null) {
                        DatagramSocket socket = (DatagramSocket)softReference.get();
                        if(socket != null) {
                            if(socket.isConnected()) {
                                socket.disconnect();
                            }

                            if(!socket.isClosed()) {
                                socket.close();
                            }

                            softReference.clear();
                        }

                        it.remove();
                    }
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public void stop(final String key) {
        (new Thread(new Runnable() {
            public void run() {
                UdpApiClient.this.onStop(key);
            }
        })).start();
    }

    public void stop() {
        (new Thread(new Runnable() {
            public void run() {
                UdpApiClient.this.onStop((String)null);
            }
        })).start();
    }

    public byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }
}
