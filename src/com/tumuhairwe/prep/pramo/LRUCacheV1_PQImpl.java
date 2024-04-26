package com.tumuhairwe.prep.pramo;

import java.util.*;

public class LRUCacheV1_PQImpl {
    private final int initialCapacity;
    private PriorityQueue<EntryKey> pqCache;
    private Map<String, EntryKey> datastore;

    public LRUCacheV1_PQImpl(int initialCapacity){
        this.initialCapacity = initialCapacity;
        this.pqCache = new PriorityQueue<>();
        this.datastore = new HashMap<>();
    }

    public void set(String key, Object val){
        if(this.pqCache.size() >= initialCapacity){
            this.evictExcess();
        }
        EntryKey ek = new EntryKey(key, val);
        this.datastore.put(key, ek);
        this.pqCache.add(ek);
    }

    private void evictExcess() {
        // evict all excess
        //Set<EntryKey> toBeRemoved = new HashSet<>();
//        while (pqCache.size() > initialCapacity){
//            toBeRemoved.add(pqCache.remove());
//        }

        while (datastore.size() > initialCapacity){
            EntryKey removedEntry = pqCache.remove();
            this.datastore.remove(removedEntry);    // equals() only accounts for key and value
//            this.datastore.entrySet()
//                    .stream()
//                    .dropWhile(p -> toBeRemoved.contains(removedEntry));
        }
    }

    public Optional<Object> get(String key){
        if(!this.pqCache.contains(key)){
            Optional.of(this.datastore.get(key));
        }
        return Optional.empty();
    }
}
