package com.tumuhairwe.prep.array;

/**
 * LeetCode 121 (easy)
 *
 * Given an array prices where prices[i] == price of given stock on the i-th day.
 * Goal: To maximize profit by choosing a single day to buy 1 stock and choosing a different day in the future to sell it
 *
 * Return the maximum profit you can achieve from this transaction (ie a single day to buy stock and a different day to sell that stock)
 *
 * If max_profit can not be achieved, return 0; else return max_profit
 *
 * ref: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * ref: https://www.youtube.com/watch?v=3RHCb8LY-X4
 */
public class BestTimeToSellStock {

    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println("Should be 5: Max Profit = " + maxProfit(arr));

        arr = new int[]{7,6,4,3,1};
        System.out.println("Should be 0. Max Profit = " + maxProfit(arr));
    }

    /**
     * Solution summary
     * - Track lowestPrice and maxProfit by
     * - For each day, if today's price is lowest,
     * - Else update calculate that day's profit and compare with existing maxProfit
     * - If larger, update maxProfit
     * - Return max_profit
     */
    public static int maxProfit(int[] prices){
        // 0. account for base case
        if(prices.length <= 1){
            return 0;
        }

        // track max-profit and min-value and scan the array while updatting them
        int maxProfit = 0;
        int lowestPrice = Integer.MAX_VALUE;

        for (int day = 0; day < prices.length; day++) {
            if(prices[day] < lowestPrice){
                lowestPrice = prices[day];
            }
            else {
                maxProfit = Math.max(maxProfit, prices[day] - lowestPrice);
            }
        }

        return maxProfit;
    }
}
