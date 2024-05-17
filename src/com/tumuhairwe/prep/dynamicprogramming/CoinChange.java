package com.tumuhairwe.prep.dynamicprogramming;

import java.util.Arrays;

/**
 * LeetCode 322 (esay)
 * Given an array coins, representing  coins of different denominations
 * and an integer amount representing the total amount of money
 *
 * Return the fewest number of coins you need to make up that amount.
 * If that amount of money can not bemade up by any combo of coins, return -1
 *
 * You may assume that you have an infinite number of each kind of coin
 *
 * ref: https://leetcode.com/problems/coin-change/
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = new int[]{1,2,5};
        int amount = 11;
        int result = coinChange(coins, amount);
        System.out.println("Should be 3: " + result);

        result = coinChange(new int[]{2}, 3);
        System.out.println("Should be -1: " + result);

        result = coinChange(new int[]{1}, 0);
        System.out.println("Should be 0: " + result);
    }
    static int coinChange(int[] coins, int amount){
        // 0. handle base case
        if(amount < 0 || coins.length == 0 || coins == null){
            return 0;
        }

        // 1. set up & see DP array
        int DEFAULT_VALUE = amount + 1;
        int[] dp = new int[amount + 1]; // default length = amt + 1
        Arrays.fill(dp, DEFAULT_VALUE);

        dp[0] = 0;

        // 2. for each amount, iterate over coins to find optimum number that doesn't exceed amount
        for (int amt = 1; amt <= amount; amt++) {
            for (int coin : coins){
                if(amt - coin >= 0){
                    dp[amt] = Math.min(dp[amt], 1 + dp[amt - coin]);
                }
            }
        }

        // 3. return default value if not reached
        return dp[amount] == DEFAULT_VALUE ? -1 : dp[amount];
    }
}
