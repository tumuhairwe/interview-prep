package com.tumuhairwe.prep.array;

import java.util.TreeMap;

/**
 * LeetCode 1296 (medium)
 * Divide array into sets of k consecutive numbers
 *
 * ref: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/description/
 * ref: https://www.youtube.com/watch?v=6JF-fxiDjIc
 */
public class DivideArrayIntoKSetsOfConsecutiveNumbers {
    // Iterate through the array once to populate the TreeMap, and then iterate through the
    // TreeMap to check if each consecutive group of k elements can be formed.
    public boolean isPossibleDivide(int[] nums, int k) {
        //0. create freqMap (TreeMap impl)
        TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        //1. while !freqMap.isEmpty()
        // .. check for a broken range (iterate & decrement frequency ... if freq==1, remove
        /**
         * Solution summary
         *
         * 0. create a frequencyMap
         * 1. iterate the freqMap until its empty
         *   - from: lowestKey -> key + k
         *         - if key is not in Map, return false
         *        - otherwise, decrement by 1 & update key
         *        - if frequency is 1 (would be 0 if you decremented), remove key
         * - return false key is not in map
         * - return true if you exit when freqMap is empty
         *
         * TC: TreeMap sorts key => O(n log n)
         * iterating the map => O(n)
         *
         * SC: O(n) for storing frequencies
         */
        while (!freqMap.isEmpty()){
            int key = freqMap.firstKey();

            for (int j = key; j < key + k; j++) {
                if(!freqMap.containsKey(key)){
                    return false;   // range if broken
                }

                int frequency = freqMap.get(j);
                if(frequency == 1){
                    freqMap.remove(j);  // decrementing would lead to 0 occurrences
                }
                else{
                    freqMap.put(j, frequency - 1);
                }
            }
        }

        //2. if we exit the frequencyMap when empty, it means all nums & occurences were a contiguous range
        return true;
    }
}
