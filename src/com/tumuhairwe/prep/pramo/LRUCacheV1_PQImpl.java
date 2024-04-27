package com.tumuhairwe.prep.pramo;

import java.util.*;

/**
 * LeetCode 146 (medium)
 *
 * Implement LRU Cache that's
 * - initialized with a positive size (initialCapacity)
 * - get(int key) - should return the value of the key if it exists, otherwise return -1
 *
 * ref: https://leetcode.com/problems/lru-cache/description/
 */
public class LRUCacheV1_PQImpl {
    private final int initialCapacity;
    private PriorityQueue<EntryKey> pqCache;
    private Map<String, EntryKey> datastore;

    public LRUCacheV1_PQImpl(int initialCapacity){
        this.initialCapacity = initialCapacity;
        Comparator<EntryKey> comp = (EntryKey e1, EntryKey e2) -> e1.getLastAccessedTime();
        this.pqCache = new PriorityQueue<>(comp);
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
            this.datastore.remove(removedEntry.getKey());    // equals() only accounts for key and value
        }
    }

    public Integer get(String key){
        if(!this.pqCache.contains(key)){
            int now = Long.valueOf(System.currentTimeMillis()).intValue();
            this.datastore.get(key).setLastAccessedTime(now);
            String value = this.datastore.get(key).getValue().toString();
            return Integer.parseInt(value);
        }
        return -1;
    }
}
