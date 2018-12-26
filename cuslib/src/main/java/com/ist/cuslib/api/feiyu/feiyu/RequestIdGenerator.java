//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ist.cuslib.api.feiyu.feiyu;

import java.util.concurrent.atomic.AtomicLong;

public class RequestIdGenerator {
    protected static final AtomicLong offset = new AtomicLong(524288L);
    protected static final int BITS = 20;
    protected static final long MAX_COUNT_PER_MILLIS = 1048576L;

    public RequestIdGenerator() {
    }

    public static long getRequestId() {
        long currentTime = System.currentTimeMillis();

        long count;
        for(count = offset.incrementAndGet(); count >= 1048576L; count = offset.incrementAndGet()) {
            Class var4 = RequestIdGenerator.class;
            synchronized(RequestIdGenerator.class) {
                if(offset.get() >= 1048576L) {
                    offset.set(0L);
                }
            }
        }

        return (currentTime << 20) + count;
    }
}
