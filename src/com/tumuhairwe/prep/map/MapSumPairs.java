package com.tumuhairwe.prep.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 677 (medium)
 * Design a map that allows you to do the following
 * - Map a string to a given value
 * - return the sum of the values
 * ref: https://leetcode.com/problems/map-sum-pairs/description/
 */
public class MapSumPairs {

    private Map<String, Set<Integer>> map;
    public MapSumPairs(){
        map = new HashMap<>();
    }

    public void add(String key, Integer val){
        if(map.containsKey(key)){
            map.remove(key);
        }

        map.putIfAbsent(key, new HashSet<>());
        map.get(key).add(val);
    }

    public int sum(String prefix){
        int total = 0;
        for(Map.Entry<String, Set<Integer>> entry : map.entrySet()){
            if(entry.getKey().startsWith(prefix)){
                total += entry.getValue().stream().mapToInt(a -> a).sum();
            }
        }
        return total;
    }
}
