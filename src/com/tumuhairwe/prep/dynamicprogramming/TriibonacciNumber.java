package com.tumuhairwe.prep.dynamicprogramming;

/**
 * LeetCode 1137 (easy)
 * Solution Summary
 * - Create dp[] and see with the 3 base cases (index_0 = 0, index_1 = 1, index_2 = 1)
 * - Account for base case (n < 3)
 * - Iteratively populate dp[i] as being the sum of the previous 3
 * - return dp[i]
 *
 * ref: https://www.youtube.com/watch?v=WrWFfzt3ayc
 * ref: https://leetcode.com/problems/n-th-tribonacci-number/description/
 */
public class TriibonacciNumber {
    public static void main(String[] args) {
        int result = tribonacci(4);
        System.out.println("4 should be 4 ->" + result );

        result = tribonacci(25);
        System.out.println("25 should be 1389537 ->" + result );
    }
    // TC: O(n) where n == N
    // SC: O(1) for the variables
    public static int tribonacci(int n) {
        int[] dp = new int[n + 3];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        if(n < 3){
            return dp[n];
        }

        for(int i=3; i<=n; i++){
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}
