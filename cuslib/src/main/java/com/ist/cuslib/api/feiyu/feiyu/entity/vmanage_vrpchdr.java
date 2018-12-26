//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ist.cuslib.api.feiyu.feiyu.entity;


import com.ist.cuslib.api.feiyu.feiyu.RequestIdGenerator;

import java.nio.ByteBuffer;

public class vmanage_vrpchdr {
    public String mac;
    public long sessionid = RequestIdGenerator.getRequestId();
    public char pktlen;
    public char operation;
    public byte pktmore = 1;
    public byte pktcnt = 1;
    public byte pktindex = 1;
    private char rev1 = 0;
    private char rev2 = 0;
    private byte tag1 = 97;
    private byte tag2 = 97;
    private byte encrypt = 0;
    private byte signflag = 0;
    private String md5 = "0000000000000000";

    public vmanage_vrpchdr() {
    }

    public byte[] toByte(int vrpchdrlen) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(vrpchdrlen);
        byteBuffer.put(this.tag1);
        byteBuffer.put(this.tag2);
        byteBuffer.putChar(this.pktlen);
        byteBuffer.putChar(this.operation);
        byteBuffer.put(this.mac.getBytes());
        byteBuffer.put(this.pktmore);
        byteBuffer.put(this.pktcnt);
        byteBuffer.put(this.pktindex);
        byteBuffer.put(this.encrypt);
        byteBuffer.put(this.signflag);
        byteBuffer.putLong(this.sessionid);
        byteBuffer.putChar(this.rev1);
        byteBuffer.putChar(this.rev2);
        byteBuffer.put(this.md5.getBytes());
        return byteBuffer.array();
    }

    public void toEntity(byte[] res) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(res.length);
        byteBuffer.put(res);
        byteBuffer.position(0);
        this.tag1 = byteBuffer.get();
        this.tag2 = byteBuffer.get();
        this.pktlen = byteBuffer.getChar();
        this.operation = byteBuffer.getChar();
        byte[] data = new byte[17];

        int i;
        for(i = 0; i < data.length; ++i) {
            data[i] = byteBuffer.get();
        }

        this.mac = new String(data);
        this.pktmore = byteBuffer.get();
        this.pktcnt = byteBuffer.get();
        this.pktindex = byteBuffer.get();
        this.encrypt = byteBuffer.get();
        this.signflag = byteBuffer.get();
        this.sessionid = byteBuffer.getLong();
        this.rev1 = byteBuffer.getChar();
        this.rev2 = byteBuffer.getChar();
        data = new byte[16];

        for(i = 0; i < data.length; ++i) {
            data[i] = byteBuffer.get();
        }

        this.md5 = new String(data);
    }

    public String toString() {
        return "vmanage_vrpchdr{mac='" + this.mac + '\'' + ", sessionid=" + this.sessionid + ", pktlen=" + this.pktlen + ", operation=" + this.operation + ", pktmore=" + this.pktmore + ", pktcnt=" + this.pktcnt + ", pktindex=" + this.pktindex + ", rev1=" + this.rev1 + ", rev2=" + this.rev2 + ", tag1=" + this.tag1 + ", tag2=" + this.tag2 + ", encrypt=" + this.encrypt + ", signflag=" + this.signflag + ", md5='" + this.md5 + '\'' + '}';
    }
}
