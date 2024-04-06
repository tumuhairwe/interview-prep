package com.tumuhairwe.prep.pramo;

public class EntryKey implements Comparable<EntryKey>{
    private int lastAccessedTime;
    private Object value;

    public EntryKey(Object val){
        this.value = val;
        this.lastAccessedTime = (int) System.currentTimeMillis();
    }
    @Override
    public int compareTo(EntryKey o) {
        return this.lastAccessedTime - o.lastAccessedTime;
    }
}
