package com.tumuhairwe.prep.arrays;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 646 (medium)
 * Given an array of N pair where pair[i] = [left, right] and left < right
 * A pair p2 = [c, d] follows a pair p1 =[a, b] if b < c.
 * A chain of pairs can be formed in this fashion
 *
 * Return the longest length chain which can be formed. You don't need to use up all the given intervals.
 * You can select pairs in any order
 *
 * ref: https://leetcode.com/problems/maximum-length-of-pair-chain/?envType=problem-list-v2&envId=mzw3cyy6
 */
public class MaxLengthOfPairChain {
    /**
     * Solution summary
     * 0. sort pairs by end (given [start, end] configuration
     * 1. seed maxEnd var with zero-th index's end. Init chainLength=1
     * 2. iterate the sorted chain starting with one-th index
     *      update maxEnd if when currEnd > maxEnd
     *      increment chainLength
     * 3. Return the chainLength when you exit the for loop
     */
    public int findLongestChain(int[][] pairs) {
        //0. sort pairs by end
        Comparator<int[]> orderByLeft = Comparator.comparingInt(a -> a[1]);
        Arrays.sort(pairs, orderByLeft);

        //1. seed result with 1st chain in link
        int maxEnd = pairs[0][1];
        int chainLength = 1;

        //2. increase length if start > length (calculate result && increase length)
        for (int i = 1; i < pairs.length; i++) {
            int currStart = pairs[i][0];
            int currEnd = pairs[i][1];

            if(currStart > maxEnd){
                maxEnd = currEnd;
                chainLength++;
            }
        }

        return chainLength;
    }
}
