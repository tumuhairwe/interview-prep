package com.tumuhairwe.prep.pramo;

import java.util.Optional;

public class LRUCacheSolution {
    public static void main(String[] args) {
        LRUCacheV2_LinkedListImpl cache = new LRUCacheV2_LinkedListImpl(2);
        cache.set(1, 1);
        cache.set(2, 2);
        cache.set(3, 3);

        Integer result = cache.get(1);
        System.out.println("LRUCacheV1_PQImpl: first thing was " + result);

        result = cache.get(2);
        System.out.println("LRUCacheV1_PQImpl: second thing was " + result);

        result = cache.get(3);
        System.out.println("LRUCacheV1_PQImpl: third thing was " + result);

        LRUCacheV1_PQImpl cacheV1_pq = new LRUCacheV1_PQImpl(2);
        cacheV1_pq.set("first-thing", 1);
        cacheV1_pq.set("second-thing", 2);
        cacheV1_pq.set("third-thing", 3);

        Optional<Object> opt = cacheV1_pq.get("first-thing");
        System.out.println("LRUCacheV1_PQImpl: first thing was " + (opt.isEmpty() ? " found =" + opt.get() : " not found"));

        opt = cacheV1_pq.get("second-thing");
        System.out.println("LRUCacheV1_PQImpl: second thing was " + (opt.isEmpty() ? " found =" + opt.get() : " not found"));

        opt = cacheV1_pq.get("third-thing");
        System.out.println("LRUCacheV1_PQImpl: third thing was " + (opt.isEmpty() ? " found =" + opt.get() : " not found"));
    }
}
