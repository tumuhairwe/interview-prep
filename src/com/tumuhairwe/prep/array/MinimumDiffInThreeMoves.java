package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 1509 (medium)
 * 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
 *
 * You are given an integer array nums.
 * In one move, you can choose one element of nums and change it to any value.
 * Return the minimum difference between the largest and smallest value of nums after performing at most three moves.
 */
public class MinimumDiffInThreeMoves {
    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 4};
        System.out.println("Should be 0: " + minDifference(arr));
    }
    public static int minDifference(int[] nums){
        //0. base case: if length <= 4, we can always DELETE each num i.e. min and max will be 0
        if (nums.length <= 4){
            return 0;
        }

        //1. sort
        Arrays.sort(nums);

        //2 iteratee thru the entire array and find min diff between a window of 4
        int result = nums[nums.length - 1] - nums[0];
        for (int i = 0; i < Math.min(nums.length, 4); i++) {
            int smallestInWindow = nums[nums.length - (3-i) -1] - nums[i];
            result = Math.min(result, smallestInWindow);
        }

        //alternate impl
        int left = 0;
        int right = nums.length - 4;
        result = nums[right] - nums[left];
        while (right < nums.length){
            result = Math.min(result, nums[right] - nums[left]);
            left++;
            right++;
        }
        return result;
    }
}
