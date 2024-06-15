package com.tumuhairwe.prep.array;

/**
 * LeetCode 303 (easy)
 *
 * Given an integer array nums, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 */
public class RangeSum_impl2 {

    private int[] nums;
    public RangeSum_impl2(int[] nums){
        this.nums = nums;
    }

    /**
     * Solution Summary
     * - iterate the array from <<left>> to <<right>>
     * - accumlate sum as sum = sum + nums[i]
     * - return sum
     */
    public int sumRange(int left, int right){
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }

        return sum;
    }
}
