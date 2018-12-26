//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ist.cuslib.api.feiyu.feiyu.entity;

import android.text.TextUtils;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class vmanage_vrpcmsg {
    public vmanage_vrpchdr vrpchdr;
    public String vrpcmsg = "";
    public char vrpchdrlen = 56;
    private byte index = 0;
    Map<Byte, String> stringMap = new TreeMap(new Comparator<Byte>() {
        public int compare(Byte o1, Byte o2) {
            return o1.byteValue() - o2.byteValue();
        }
    });

    public vmanage_vrpcmsg() {
    }

    public byte[] toByte() {
        byte[] jsonBs = this.vrpcmsg.getBytes();
        char len = (char)(this.vrpchdrlen + jsonBs.length);
        this.vrpchdr.pktlen = len;
        ByteBuffer vrpcmsgBuffer = ByteBuffer.allocate(len);
        vrpcmsgBuffer.put(this.vrpchdr.toByte(this.vrpchdrlen));
        vrpcmsgBuffer.put(jsonBs);
        return vrpcmsgBuffer.array();
    }

    public void toEntity(byte[] res) {
        this.vrpchdr = new vmanage_vrpchdr();
        this.vrpchdr.toEntity(this.subBytes(res, 0, this.vrpchdrlen));
        this.stringMap.put(Byte.valueOf(this.vrpchdr.pktindex), new String(this.subBytes(res, this.vrpchdrlen, res.length - this.vrpchdrlen)));
        ++this.index;
        if(this.isComplete()) {
            Set<Byte> set = this.stringMap.keySet();
            Iterator var3 = set.iterator();

            while(var3.hasNext()) {
                Byte key2 = (Byte)var3.next();
                if(TextUtils.isEmpty(this.vrpcmsg)) {
                    this.vrpcmsg = (String)this.stringMap.get(key2);
                } else {
                    this.vrpcmsg = this.vrpcmsg + (String)this.stringMap.get(key2);
                }
            }
        }

    }

    public boolean isComplete() {
        return this.vrpchdr != null && this.vrpchdr.pktcnt <= this.index;
    }

    public byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

    public String toString() {
        return "vmanage_vrpcmsg{vrpchdr=" + this.vrpchdr.toString() + ", vrpcmsg='" + this.vrpcmsg + '\'' + ", vrpchdrlen=" + this.vrpchdrlen + '}';
    }
}
