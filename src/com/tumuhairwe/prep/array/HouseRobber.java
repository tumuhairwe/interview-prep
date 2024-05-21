package com.tumuhairwe.prep.array;

/**
 * Given a list of non-negative integers, representing the amount of money in each
 * house, determine the maximum amount of money you can rob to night
 * without alerting the police.
 *
 * Constraint:
 * - Each house has exactly the amount of money stashed
 * - Only obstacle is that adjacent houses have security cameras connected that
 * that will alert the police
 *
 *  input: [1,2,3,1] -> output = 4 (can rob 1 + 3 ... but not 2 + 1)
 *
 *  Hint:
 *  - Use memoization to store calculated values in an array
 *  (memoization is useful for caching the results of repetitive calculations) so you can  use them for future calculations
 *  - Useful to cache repetitive calculations
 *
 *  Solution Summary
 *  - Use DP to calculate the max of (THIS house, THIS + 1 house)
 *
 * LeetCode 198 (Medium)
 * ref: https://leetcode.com/problems/house-robber/description/
 * ref: https://www.youtube.com/watch?v=ZwDDLAeeBM0&t=24s (Nick White)
 * ref: https://www.youtube.com/watch?v=73r3KWiEvyk (NeetCode)
 */
public class HouseRobber {

    public static void main(String[] args) {
        int[] x = new int[]{1,2,3,1};
        int r = rob(x);
        System.out.println(r);

        int[] y = new int[]{2,7,9,3,1};
        r = rob(y);
        System.out.println(r);
    }

    // dp memoization template
    public static int rob(int[] nums){
        // 0. check base case (array.length == 0)
        if(nums.length == 0){
            return 0;
        }

        // 1. create cache/array called DP or memoization
        int dp[] = new int[nums.length + 1];

        // 2. set initial state/values (i.e. maximum value we can rob for each count of houses)
        // set initial state of 1st 2 options (0 and 1)
        dp[0] = 0;          // 1st value == "if we rob 0 houses" we get 0 money
        dp[1] = nums[0];   // cost of robbing 1 house == nums[0] (first house) .. this assume=s once non-negative integers

        // 3. loop over array starting from 1
        for (int i = 1; i < nums.length; i++) {

            // get (old/previous-house's max dp[i-1] + THIS value) ..
            // plus the new Max ...
            // whichever is greater si the new value of
            int previousMax = dp[i];
            int oldMax = dp[i - 1]; // may not be an adjacent house
            int newHouseCost = nums[i];
            dp[i+1] = Math.max(previousMax, oldMax + newHouseCost);
            //dp[i+1] = Math.max(dp[i], dp[i - 1] + nums[i]);
        }

        // return the max we can rob for MAX number of houses (i.e. last element of dp array == max we can rob)
        return dp[nums.length];
    }
}
