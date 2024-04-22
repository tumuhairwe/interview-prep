package com.tumuhairwe.prep.pramo;

import java.util.Objects;

public class EntryKey implements Comparable<EntryKey>{
    private int lastAccessedTime;
    private Object value;

    public EntryKey(Object val){
        this.value = val;
        this.lastAccessedTime = (int) System.currentTimeMillis();
    }

    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryKey entryKey = (EntryKey) o;
        return lastAccessedTime == entryKey.lastAccessedTime && Objects.equals(value, entryKey.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastAccessedTime, value);
    }

    @Override
    public int compareTo(EntryKey o) {
        return Integer.compare(o.lastAccessedTime, this.lastAccessedTime);
    }
}
