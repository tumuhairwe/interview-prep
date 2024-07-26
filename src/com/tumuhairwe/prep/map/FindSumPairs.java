package com.tumuhairwe.prep.map;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1865 (medium)
 *
 * You're given 2 integer arrays with nums1 and nums2. You are tasked to implement a data structure
 * that supports queries of 2 types
 *
 * 1. Add a positive integer to an element of a given index
 * 2. Count the number of pairs (i, j) such that nums1[i] + nums2[j] == a given value
 * assuming (0 <= i < nums1.length and 0 <= j < nums2.length)
 *
 *
 * Implement the FindSumPairs class:
 *
 * FindSumPairs(int[] nums1, int[] nums2) Initializes the FindSumPairs object with two integer arrays nums1 and nums2.
 * void add(int index, int val) Adds val to nums2[index], i.e., apply nums2[index] += val.
 * int count(int tot) Returns the number of pairs (i, j) such that nums1[i] + nums2[j] == tot.
 *
 * ref: https://www.youtube.com/watch?v=T4yZIHISIYY
 * ref: https://leetcode.com/problems/finding-pairs-with-a-certain-sum/description/
 */
public class FindSumPairs {

    //0. create freqMap of nums2
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> nums2_freqMap;

    /**
     * Solution summary
     * - save both vars
     * - create freqMap of nums2
     */
    public FindSumPairs(int[] nums1, int[] nums2){
        this.nums1 = nums1;
        this.nums2 = nums2;

        nums2_freqMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            nums2_freqMap.put(nums2[i], nums2_freqMap.getOrDefault(nums2[i], 0) + 1);
        }
    }

    /**
     * Solution summary
     * - decrement frequency of value @ num2[index] in the freqMap
     * - increment VALUE in num2[index] by value
     * - increment frequency of value @ num2[index] in the freqMap
     */
    public void add(int index, int value){
        // decrement freqMap
        nums2_freqMap.put(nums2[index], nums2_freqMap.getOrDefault(nums2[index], 0) - 1);

        //add value to num2
        nums2[index] += value;

        // increment freqMap
        nums2_freqMap.put(nums2[index], nums2_freqMap.getOrDefault(nums2[index], 0) +1);
    }

    /**
     * Solution summary
     * - loop over all entries in NUMS_1
     * - for each entry,
     *      - get the diff between entry-tatal
     *      - get the frequency of that diff in num2_freqMap
     *      - add that frequency to the result (numPairs)
     * - return numPairs
     */
    public int count(int total){
        int numPairs = 0;
        for (int n1 : nums1){
            int diff = n1 - total;
            numPairs += nums2_freqMap.getOrDefault(diff, 0);
        }

        return numPairs;
    }
}
