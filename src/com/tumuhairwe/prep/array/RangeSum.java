package com.tumuhairwe.prep.array;

/**
 * LeetCode 303
 *
 * Given an integer array nums, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 */
public class RangeSum {

    private int[] prefixSums;
    public RangeSum(int[] nums){
        int current = 0;
        prefixSums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            current += nums[i];
            prefixSums[i] = current;
        }
    }
    /**
     * Solution Summary
     * - pre-compute the sums at initialization/construction so the reading/getting is O(1)
     * - get the sum of prefix[right]
     * - get the prefix[left] (make left 0 if out of bounds)
     * - return rightSum - leftSum
     */
    public int sumRange(int left, int right){
        int rightSum = prefixSums[right];
        int leftSum = left > 0 ? prefixSums[left - 1] : 0;
        return rightSum - leftSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2,0,3,-5,2,-1};
        RangeSum r = new RangeSum(nums);
        System.out.println("1"+ r.sumRange(0, 2));
        System.out.println("-1" + r.sumRange(2, 5));
        System.out.println("-3"+ r.sumRange(0, 5));
    }
}
