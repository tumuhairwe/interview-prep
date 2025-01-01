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
public class MapSumPairs_Trie {
    class TrieNode{
        Map<Character, TrieNode> children;
        int score;
        public TrieNode(){
            children = new HashMap<>();
        }
    }

    /**
     * Since we are dealing with prefixes, a Trie (prefix tree) is a natural
     * data structure to approach this problem.
     * For every node we will store the desired answer/core at that node
     */
    private Map<String, Integer> map;
    private TrieNode root;
    public MapSumPairs_Trie(){
        this.map = new HashMap<>();
        this.root = new TrieNode();
    }
    public void insert(String key, int val){
        // add to map
        map.put(key, val);

        // calc diff in score and add to curr
        int delta = val - map.getOrDefault(key, 0);
        TrieNode curr = root;
        curr.score += delta;

        // propagate to all children that have this as prefix
        for(char ch : key.toCharArray()){
            curr.children.putIfAbsent(ch, new TrieNode());
            curr = curr.children.get(ch);
            curr.score += delta;
        }
    }

    public int sum(String prefix){
        TrieNode curr = root;
        for(char ch : prefix.toCharArray()){
            curr = curr.children.get(ch);
            if(curr == null){
                return 0;
            }
        }

        return curr.score;
    }
}
