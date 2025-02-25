package com.tumuhairwe.prep.twopointers;

import java.util.Arrays;

/**
 * LeetCode 1099
 * Given an array nums of integers, and an integer K, return the maximum sum such that there
 * exists i<j with nums[i] + nums[j] == sum and (sum <k).
 * If no i, j exists, return -1
 *
 * ref: https://leetcode.com/problems/two-sum-less-than-k/
 * ref: LeetCode 167 (medium) -- Two sum sorted array
 */
public class TwoSumLessThanK {
    /**
     * Solution summary
     * 1. sort the array (n log n)
     * 2. use pointers to traverse the array from opposite ends
     *      calculate the sum of p1 & p2
     * 3. if sum is less, result = max(sum, result);
     * 4. if sum is greater than or equal, decrement p2
     * 5. return result
     */
    public int twoSumLessThanK(int[] nums, int k) {
        //0. sort arrays
        Arrays.sort(nums);

        //2 p
        int p1 = 0;
        int p2 = nums.length - 1;
        int result = 0;
        while (p1 < p2){
            int sum = nums[p1] + nums[p2];
            if(sum < k){
                result = Math.max(result, sum);
                p1++;
            }
            else{
                p2--;
            }
        }

        return result;
    }
}
