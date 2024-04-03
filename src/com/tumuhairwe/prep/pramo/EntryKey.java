package com.tumuhairwe.prep.pramo;

import java.time.LocalDateTime;

public class Entry implements Comparable<Entry>{
    private int lastAccessedTime;
    private Object value;

    public Entry(Object val){
        this.value = val;
        this.lastAccessedTime = (int) System.currentTimeMillis();
    }
    @Override
    public int compareTo(Entry o) {
        return this.lastAccessedTime - o.lastAccessedTime;
    }
}
