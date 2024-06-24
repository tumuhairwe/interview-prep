package com.tumuhairwe.prep.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * LeetCode 2404. (easy)
 *
 * Given an integer array nums, return the most frequent even element.
 *
 * If there is a tie, return the smallest one. If there is no such element, return -1.
 * ref: https://leetcode.com/problems/most-frequent-even-element/description/
 */
public class MostFrequentEvenElement {

    public static void main(String[] args) {
        int[] arr = {8154,9139,8194,3346,5450,9190,133,8239,4606,8671,8412,6290};
        System.out.println("Smallest among same frequency should be 3346: " + mostFrequentEvent(arr));
    }
    /**
     * Solution summary
     * - create frequency map of all even numbers
     * - find entry with the maximum frequency
     * - stream().filter() to find multiple entries with that frequency
     * - map those entries to the entry.key
     * - sort those keys
     * - return the smallest one (if absent, return -1)
     */
    public static int mostFrequentEvent(int[] nums){
        //0. collect all even elements into map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(nums[i] %2 != 0){
                continue;
            }

            int existingCount = freqMap.getOrDefault(nums[i], 0);
            freqMap.put(nums[i], existingCount + 1);
        }

        //1. find maxFrequency
        int maxFreq = Integer.MIN_VALUE;
        for(Map.Entry<Integer, Integer> entry : freqMap.entrySet()){
            //int key = entry.getKey();
            int val = entry.getValue();

            if(maxFreq < val){
                maxFreq = val;
                //ans = key;
            }
        }

        // "if there's a tire, return the smallest one"
        final int maxFrequency = maxFreq;
        Optional<Integer> smallestKeyWithHighestFrequency = freqMap.entrySet().stream()
                .filter(e -> e.getValue() == maxFrequency)
                .map(e -> e.getKey())
                .sorted()
                .findFirst();

        return (smallestKeyWithHighestFrequency.isEmpty()) ? -1 : smallestKeyWithHighestFrequency.get();
    }
}
