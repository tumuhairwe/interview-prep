package com.tumuhairwe.prep.dynamicprogramming;

/**
 * LeetCode 70 (Easy)
 *
 * Given a staircase of n steps to reach to the top
 * Each time, you can either climb 1 or 2 steps, how many distinct ways can you climb to the top
 *
 * ref: https://leetcode.com/problems/climbing-stairs/description/
 * ref: https://www.youtube.com/watch?v=Y0lT9Fck7qI
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        System.out.println("Given n = " + 2 + " there are " + dynamicProgrammingSolution(2));
    }
    /**
     * Solution Summary
     * - Base case: if n == 1, return 1
     * - create DP array and seed the 1st 2 values to exact number of steps i.e 1 and 2
     * - This is based on the observation that solution to N = (solution to n -1) + (solution to n -2)
     * - Iterate up to index n and populate all the values
     * - Return dp[N] as the (solution to N)
     */
     static int dynamicProgrammingSolution(int n){
         // 0. handle base case
        if(n == 1){
            return 1;
        }

        // 1. seed 1st 2
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 2;

        // set value of dp[i] = previous + 2nd_previous
         for (int i = 3; i <= n; i++) {
             dp[i] = dp[i - 1] + dp[i - 2];
         }

         return dp[n];
    }
}
