package com.tumuhairwe.prep.array;

/**
 * LeetCode 53 (medium)
 * Given an integer  nums,
 * find the sub-array with the largest sum and it return its sum
 *
 * ref: https://leetcode.com/problems/maximum-subarray/
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(arr));
    }
    public static int maxSubArray(int[] nums){
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1 ; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
