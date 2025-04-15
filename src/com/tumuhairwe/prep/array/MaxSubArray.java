package com.tumuhairwe.prep.array;

/**
 * LeetCode 53 (medium)
 * Given an integer  nums,
 * find the sub-array with the largest sum and it return its sum
 * aka Kadane's algorithm
 *
 * ref: https://leetcode.com/problems/maximum-subarray/
 * ref: https://www.youtube.com/shorts/lrH2sw-FmD4
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(arr));
    }

    //TC: O(n)
    //SC: O(1)
    public static int maxSubArray(int[] nums) {
        //0. case 1: only 1 element
        if (nums.length == 0) {
            return nums[0];
        }

        //1. declare vars
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;

        for (int i = 0; i < nums.length; i++) {

            //2. update both sums
            currSum += nums[i];
            maxSum = Math.max(currSum, maxSum);

            if(currSum < 0){
                currSum = 0;
            }
        }

        return maxSum;
    }
}
