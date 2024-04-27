package com.tumuhairwe.prep.pramo;

import java.util.*;

/**
 * LeetCode 146 (medium)
 * Implement LRU Cache that's
 * - initialized with a positive size (initialCapacity)
 * - get(int key) - should return the value of the key if it exists, otherwise return -1
 * ref: https://leetcode.com/problems/lru-cache/description/
 */
public class LRUCacheV1_PQImpl {
    private final int initialCapacity;
    private PriorityQueue<EntryKey> pqCache;
    private Map<String, EntryKey> datastore;

    public LRUCacheV1_PQImpl(int initialCapacity){
        this.initialCapacity = initialCapacity;
        this.pqCache = new PriorityQueue<>();
        this.datastore = new HashMap<>();
    }

    public void put(String key, Integer val){
        if(this.pqCache.size() >= initialCapacity){
            this.evictExcess();
        }
        EntryKey ek = new EntryKey(key, val);
        this.datastore.put(key, ek);
        this.pqCache.add(ek);
    }

    private void evictExcess() {
        // evict all excess
        while (datastore.size() > initialCapacity){
            EntryKey removedEntry = pqCache.remove();
            this.datastore.remove(removedEntry);    // equals() only accounts for key and value
        }
    }

    public Integer get(String key){
        if(!this.pqCache.contains(key)){
            EntryKey ek = this.datastore.get(key);
            return Integer.parseInt(ek.getValue().toString());
        }
        return -1;
    }
}
