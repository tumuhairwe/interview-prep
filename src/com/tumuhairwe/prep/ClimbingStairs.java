package com.tumuhairwe.prep;

/**
 * LeetCode 70 (Easy)
 *
 * Given a staircase of n steps to reach to the top
 * Each time, you can either climb 1 or 2 steps, how many distinct ways can you climb to the top
 *
 * ref: https://leetcode.com/problems/climbing-stairs/description/
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        System.out.println("Given n = " + 2 + " there are " + dynamicProgrammingSolution(2));
        System.out.println("Given n = " + 3+ " there are " + fibonnaciSolution(3));
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
        dp[1] = 1;
        dp[2] = 2;

        // set value of dp[i] = previous + 2nd_previous
         for (int i = 3; i <= n; i++) {
             dp[i] = dp[i - 1] + dp[i - 2];
         }

         return dp[n];
    }

    /**
     * Solution Summary
     * - Base case: if n == 1, return 1, if n== 2, return 2
     * - Build fibonnaci sequence until n by sliding current forward
     *      - current = previous_1 + previous_2
     *      - previous_2 = previous_1
     *      - previous_1 = current
     *  return current (since loop was upt to N)
     */
    static int fibonnaciSolution(int n){
         if(n == 1){
             return 1;
         }
         if(n == 2){
             return 2;
         }
         int current = 0;
         int previous_1 = 2;
         int previous_2 = 1;
         for (int i = 3; i < n; i++) {
             current = previous_1 + previous_2;
             previous_2 = previous_1;
             previous_1 = current;
        }

         return current;
    }
}
