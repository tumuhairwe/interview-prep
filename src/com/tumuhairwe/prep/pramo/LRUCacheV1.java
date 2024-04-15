package com.tumuhairwe.prep.pramo;

import java.util.*;

public class LRUCacheV1 {
    private final int initialCapacity;
    private PriorityQueue<EntryKey> pqCache;
    private Map<EntryKey, Object> cache;

    public LRUCacheV1(int initialCapacity){
        this.initialCapacity = initialCapacity;
        this.pqCache = new PriorityQueue<>();
    }

    public void set(String key, Object val){
        if(this.pqCache.size() >= initialCapacity){
            this.evictExcess();
        }
        EntryKey ek = new EntryKey(key);
        this.pqCache.add(ek);
        this.cache.put(ek, val);
    }

    private void evictExcess() {
        LinkedList<Integer> linkedList = new LinkedList<>();
//        linkedList.
    }

    public Optional<Object> get(String key){
        if(!this.pqCache.contains(key)){
            return  Optional.empty();
        }
        return Optional.empty();
        //return Optional.of(this.pqCache.);
    }
}
