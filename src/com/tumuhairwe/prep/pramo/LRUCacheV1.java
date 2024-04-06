package com.tumuhairwe.prep.pramo;

import java.util.*;

public class LRUCacheV1 {
    private final int initialCapacity;
    private PriorityQueue<EntryKey> pqCache;
    //private Map<EntryKey, TimePlanner.Interval> cache;

    public LRUCacheV1(int initialCapacity){
        this.initialCapacity = initialCapacity;
        this.pqCache = new PriorityQueue<>();
    }

    public void set(String key, TimePlanner.Interval val){
        if(this.pqCache.size() >= initialCapacity){
            this.evictExcess();
        }
        this.pqCache.add(new EntryKey(key));
    }

    private void evictExcess() {
        LinkedList<Integer> linkedList = new LinkedList<>();
//        linkedList.
    }

    public Optional<TimePlanner.Interval> get(String key){
        if(!this.pqCache.contains(key)){
            return  Optional.empty();
        }
        return Optional.empty();
        //return Optional.of(this.pqCache.);
    }
}
