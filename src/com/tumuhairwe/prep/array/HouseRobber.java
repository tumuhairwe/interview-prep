package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * Given a list of non-negative integers, representing the amount of money in each
 * house, determine the maximum amount of money you can rob to night
 * without alerting the police.
 * Constraint:
 * - Each house has exactly the amount of money stashed
 * - Only obstacle is that adjacent houses have security cameras connected that
 * that will alert the police
 *
 *  input: [1,2,3,1] -> output = 4 (can rob 1 + 3 ... but not 2 + 1)
 *
 *  Hint:
 *  - Use memoization to store calculated values in an array
 *  - Useful to cache repeatitive calculations
 * LeetCode 198
 * ref: https://leetcode.com/problems/house-robber/description/
 */
public class HouseRobber {

    public static void main(String[] args) {

    }

    public int rob(int[] nums){
        // 0. check base case (array.length == 0)
        if(nums.length == 0){
            return 0;
        }

        //int mid = nums.length /2;
        //int[] arr = Arrays.copyOfRange(nums, mid, nums.length-1);
        // 1. create cache/array called DP or meo
        int dp[] = new int[nums.length];

        // 2. set initial state/values (i.e. maximum value we can rob for each count of houses)
        dp[0] = 0;  // 1st value == "if we rob 0 houses" we get 0 money
        dp[1] = nums[0];;   // minimum = 0 once non-negative integers

        // 3. loop over array starting from 1
        for (int i = 1; i < nums.length; i++) {

            // get (old/previous-house's max dp[i-1] + THIS value) ..
            // plus the new Max ...
            // whichever is greater si the new value of
            dp[i+1] = Math.max(dp[i], dp[i -1] + nums[i]);
        }
        return 0;
    }
}
