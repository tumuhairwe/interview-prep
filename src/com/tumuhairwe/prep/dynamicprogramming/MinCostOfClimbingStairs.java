package com.tumuhairwe.prep.dynamicprogramming;

/**
 * LeetCode 746 (easy)
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
 *
 * You can either start from the step with index 0, or the step with index 1.
 *
 * Return the minimum cost to reach the top of the floor.
 *
 * ref: https://leetcode.com/problems/min-cost-climbing-stairs/description/
 * ref: dp reference tutorial https://leetcode.com/problems/min-cost-climbing-stairs/solutions/476388/4-ways-step-by-step-from-recursion-top-down-dp-bottom-up-dp-fine-tuning/
 */
public class MinCostOfClimbingStairs {
    public int climbStairs(int n){
        if (n == 1){
            return 1;
        }

        int[] cache = new int[n];
        cache[0] = 1;
        cache[1] = 2;

        for (int i = 2; i < cache.length; i++) {
            cache[i] = cache[i-1] + cache[i -2];
        }

        return cache[n-1];
    }
}
