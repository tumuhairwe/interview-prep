package com.tumuhairwe.prep.pramp;

import java.util.Objects;

public class EntryKey implements Comparable<EntryKey>{
    private int lastAccessedTime;
    private String key;
    private Object value;

    public EntryKey(String key, Object val){
        this.key = key;
        this.value = val;
        this.lastAccessedTime = (int) System.currentTimeMillis();
    }

    public Object getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public int getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(int lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryKey entryKey = (EntryKey) o;

        // equality only account for key and value
        return Objects.equals(key, entryKey.key)
                && Objects.equals(value, entryKey.value);
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
