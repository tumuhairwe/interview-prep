package com.tumuhairwe.prep.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 677 (medium)
 * Design a map that allows you to do the following
 * - Map a string to a given value
 * - return the sum of the values
 * ref: https://leetcode.com/problems/map-sum-pairs/description/
 */
public class MapSumPairs_PrefixMap {
    /**
     * Solution summary
     * - pre-calculate the score and store it a map
     * - re-calculate the score every time a key is inserted so you update all children (that are suffixes)
     */
    Map<String, Integer> map;
    Map<String, Integer> score;
    public MapSumPairs_PrefixMap(){
        map = new HashMap<>();
        score = new HashMap<>();
    }

    /**
     * Time Complexity: Every insert operation is O(K^2)
     * where K is the length of the key, as K strings are made of an average length of K.
     */
    public void insert(String key, int val){
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);

        String prefix = "";
        for(char ch : key.toCharArray()){
            prefix += ch;
            score.put(prefix, score.getOrDefault(prefix, 0));
        }
    }

    /**
     * TC: Every sum operation is O(1).
     * SC: O(n)
     */
    public int sum(String prefix){
        return score.getOrDefault(prefix, 0);
    }
}
