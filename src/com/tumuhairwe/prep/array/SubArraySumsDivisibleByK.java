package com.tumuhairwe.prep.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 974 (medium)
 * Given an integer array nums[] and integer K, return the number of non-empty subarrays that have a sum divisible by k
 *
 * ref: https://leetcode.com/problems/subarray-sums-divisible-by-k/description/
 * ref: https://www.youtube.com/watch?v=bcXy-T4Sc3E
 */
public class SubArraySumsDivisibleByK {

    public int subarraysDivByK(int[] nums, int k){
        int returnValue = 0;
        //0. create freqMap of remainders
        Map<Integer, Integer> remainder_freqMap = new HashMap<>();
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            int remainder = ((prefixSum % k) + k) % k;
            if(remainder_freqMap.containsKey(remainder)){
                returnValue += remainder_freqMap.get(remainder);
            }
            remainder_freqMap.put(remainder, remainder_freqMap.getOrDefault(remainder, 0) + 1);
        }
        return returnValue;
    }
}
