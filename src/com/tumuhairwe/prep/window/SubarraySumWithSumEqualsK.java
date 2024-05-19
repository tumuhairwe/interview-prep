package com.tumuhairwe.prep.window;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 560 (Medium)
 *
 * Given an array of integers nums and an integer K,
 * return the total number of contiguous sum-arrays whose sum equals K
 *
 * ref: https://leetcode.com/problems/subarray-sum-equals-k/description/
 * ref: https://www.youtube.com/watch?v=fFVZt-6sgyo
 */
public class SubarraySumWithSumEqualsK {

    public static void main(String[] args) {

    }

    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int currentSum = 0;     // number of cont

        // key = prefixSum, val=count-of-numerOfTimes We've see that prefixSum
        Map<Integer, Integer> prefixSums = new HashMap<>();
        prefixSums.put(0, 1);   // there's at least 1 sub-array with sum = 0;

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            int diff = currentSum - k;
            if(prefixSums.containsKey(diff)){
                result += prefixSums.get(diff);
            }

            int existingCount = prefixSums.getOrDefault(currentSum, 0);
            prefixSums.put(currentSum, existingCount + 1);
        }

        return result;
    }
}
