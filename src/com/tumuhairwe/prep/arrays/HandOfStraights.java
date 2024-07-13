package com.tumuhairwe.prep.arrays;

import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 846 Hand of Straights
 * Alice has a number of Cards that need to be arranged into groups so that each group is of size groupSize ...
 * and consists of groupSize consecutive cards
 *
 * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize,
 * return true if she can rearrange the cards, or false otherwise.
 *
 * ref: https://leetcode.com/problems/hand-of-straights/description/?envType=problem-list-v2&envId=plakya4j
 */
public class HandOfStraights {

    /**
     * Solution summary
     * - if hand is not exactly divisible by groupSize, return false
     * - Create freqMap using TreeMap (sorts keys by value)
     * - while freqMap is not empty,
     *      - get next key from freqMap
     *      - for (0 to groupSize) --- set key = currentHand + i
     *           - if !freqMap.contains(key) ... return false
     *           - decrement frequency in freqMao
     *           - if frequency is now 0, remove key
     *  - if you get to the end .. (i.e. all cards of groupSize existed in hand) ... return true
     */
    // tc: O(n log n + n * k)
    // sorting == n log n (populating TreeMap)
    // outer loop processes the map until its empty (worst case: could run N times)
    // checking for the presence of k consecutive cards = K time
    public boolean isNStraightHand(int[] hand, int groupSize){
        //0. base case: can't split hand into groupSize cards
        if (hand.length % groupSize != 0){
            return false;
        }

        //1. create treeFreqMap
        Map<Integer, Integer> treeFreqMap = new TreeMap<>();
        for (int i = 0; i < hand.length; i++) {
            treeFreqMap.put(hand[i], treeFreqMap.getOrDefault(hand[i], 0) + 1);
        }

        while (!treeFreqMap.isEmpty()){
            int currentHand = treeFreqMap.keySet().iterator().next();

            for (int i = 0; i < groupSize; i++) {
                int key = currentHand + i;

                //int key = currentCard + i;
                //3a. if a card is missing or has exhausted its occurrences .. return false
                if(!treeFreqMap.containsKey(key)){
                    return false;
                }

                //3b. decrement freq by 1
                if(treeFreqMap.containsKey(key)){
                    treeFreqMap.put(key, treeFreqMap.get(key) - 1);
                }

                //4. if occurrences exhausted, remove from Map
                if(treeFreqMap.get(key) == 0){
                    treeFreqMap.remove(key);
                }
            }
        }

        return true;
    }
}
