package com.tumuhairwe.prep.map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 981 (medium)
 * Design a time-based key value data structure that can store multiple values for the same key at different
 * timestamps and retrieve the key's value at a certain timestamp
 *
 * Implement the TimeMap class:
 *
 * - TimeMap() Initializes the object of the data structure.
 * - void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * - String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the value associated with the largest timestamp_prev.
 * If there are no values, it returns "".
 */
public class TimeBasedKeyValueStore {
    private Map<String, TreeMap<Integer, String>> map;

    /**
     * Solution summary
     * - initialize backing map to have String as key-type and a TreeMap as the value (TreeMap sorts keys in thte map by default)
     */
    public TimeBasedKeyValueStore(){
        this.map = new HashMap<>();
    }

    /**
     * Solution summary
     * - if key exists, simple add key-value pair as entry in the TreeMap that is the value of that key
     */
    public void set(String key, String value, int timestamp){
        if(map.containsKey(key)){
            map.get(key).put(timestamp, value);
        }
        else {
            map.put(key, new TreeMap<>());
            map.get(key).put(timestamp, value);
        }
    }

    /**
     * Solution summary
     * - if key doesn't exist in backing-map return ""
     * - if key exists, get closest-key value to timestamp (treeMap.floorKey(timestamp_as_key)
     * - if closest-key doesn't exist, return ""
     * - if closest-key key exists, get its value
     */
    public String get(String key, int timestamp){
        if(!map.containsKey(key)){
            return "";
        }

        TreeMap<Integer, String> values = map.get(key);
        if(values.containsKey(timestamp)){
            return values.get(timestamp);
        }
        else {
            Integer closest = values.floorKey(timestamp);
            if(closest == null){
                return null;
            }
            else {
                return values.get(closest);
            }
        }
    }
}
