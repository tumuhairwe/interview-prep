package com.tumuhairwe.prep.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 560 (Medium)
 *
 * Given an array of integers nums and an integer K,
 * return the total number of contiguous sum-arrays whose sum equals K
 *
 * Solution Summary
 * - Create Map<Integer, Integer> where [key=total_sum_of_sub_array, value = count]
 * - iterate thru array and populate Map and seed it with [0, 1] -- bcoz there at least 1 sub-array with sum = 0
 * - calculate cumulative sum (as you traverse array)
 * - calculate diff (cumulativeSum - k)
 * - if diff exists in, map -> set as result (to be returned)
 * - add cummulativeSum to map (with existingCount updated + 1)
 * ref: https://leetcode.com/problems/subarray-sum-equals-k/description/
 * ref: https://www.youtube.com/watch?v=fFVZt-6sgyo
 */
public class SubArraySumWithSumEqualsK {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        int k = 3;
        System.out.println(subarraySum(nums, k));
    }

    /**
     * Solution summary
     * - Create prefixSum frequency map and seed with 0
     * - iterate over array and accumulate currentSum
     * - calculate duff (currentSum -k)  and check if exists in prefixSum map -> get frequency to result
     * - update prefixSum freqMap with currentSum
     *
     * TC: O(n) - we iterate over the array and no other data structure is used
     * SC: O(n) -- because we store the currentSum in a map
     */
    public static int subarraySum(int[] nums, int k) {
        int result = 0;
        int currentSum = 0;     // number of cont

        // 0. Declare vars (prefixSums map) and seed it
        // key = prefixSum, val=count-of-numberOfTimes We've seen that prefixSum
        Map<Integer, Integer> prefixSums = new HashMap<>();
        prefixSums.put(0, 1);   // there's at least 1 sub-array with sum = 0; (i.e. empty sub-array)

        for (int i = 0; i < nums.length; i++) {
            // 2. calculate current sum
            currentSum += nums[i];

            // 3. update result (if diff between currentSum and K exists in prefixSum)
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
