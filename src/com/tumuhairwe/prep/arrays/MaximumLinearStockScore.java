package com.tumuhairwe.prep.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2898 (medium)
 *
 * Given a 1-index int[] prices, where prices[i] == price of a particular stock on the i-th day.
 * 
 * your task is to select some of the elements of prices such that your selection is linear.
 * A selection indexes, where indexes is a 1-indexed integer array of length k which is a subsequence of the array [1, 2, ..., n], is linear if:
 * For every 1 < j <= k, prices[indexes[j]] - prices[indexes[j - 1]] == indexes[j] - indexes[j - 1].
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 * The score of a selection indexes, is equal to the sum of the following array: [prices[indexes[1]], prices[indexes[2]], ..., prices[indexes[k]].
 * 
 * Return the maxScore that a linear selection can have
 */
public class MaximumLinearStockScore {
    //TC: O(n)
    public long maxScore(int[] prices) {
        //0. declare vars
        Map<Integer, Long> diffFreqMap = new HashMap<>();
        long res = 0;

        //1. iterate over []
        for (int i = 0; i < prices.length; i++) {
            //2 calculate diff based on formula
            int diff = prices[i] - i;

            //3. update map with key=diff && val=diffFreq
            diffFreqMap.put(diff, diffFreqMap.getOrDefault(diff, 0L) + prices[i]);

            //4 calculate max of 2
            res = Math.max(res, diffFreqMap.get(diff));
        }

        return res;
    }
}
