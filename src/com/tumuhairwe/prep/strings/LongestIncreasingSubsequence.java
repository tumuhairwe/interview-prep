package com.tumuhairwe.prep.strings;

import java.util.Arrays;

/**
 * LeetCode 300 (medium)
 *
 * Given an array nums, return the length of the longest strictly increasing subsequence
 *
 * ref: https://leetcode.com/problems/longest-increasing-subsequence/description/
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};
        System.out.println("Should be 4 -> " + lengthOfLIS(arr));
    }
    /**
     * Solution summary (DP problem)
     * - declare cache of size nums.length, and seed it with 1s (length of every character in that index)
     * - since its a nested loop, loop backwards and forwards (forward staring from index i + 1)
     *
     * - its an increasing subsquenc (i.e. nums[i] < nums[j])
     *      - calculate the max (i.e. newMax = 1 + cache[j])
     *      - Update the cache[index] with the max(cache[i], newMax)
     *      - update the max variable
     * - return the max variable
     */
    public static int lengthOfLIS(int[] nums){
        //0. base case
        if(nums.length == 1){
            return 1;
        }

        //1. declare cache
        int[] cache = new int[nums.length];

        //2. seed cache with 1.
        Arrays.fill(cache, 1);

        //3 loop backwards
        int max = 1;
        for (int i = cache.length -1; i > 0; i--) {
            //4. loop forward ... starting from i + 1
            for (int j = i + 1; j < cache.length; j++) {
                // 5. set ith value to i-j only for i <j (i.e. if its increasing)
                if(nums[i] < nums[j]){
                    int newMax = 1 + cache[j];
                    cache[i] = Math.max(newMax, cache[i]);
                }

                // update max
                max = Math.max(max, cache[i]);
            }
        }

        return max;
    }
}
