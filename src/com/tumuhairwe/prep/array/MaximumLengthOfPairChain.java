package com.tumuhairwe.prep.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 646 (medium)
 * ref: https://leetcode.com/problems/maximum-length-of-pair-chain/description/
 *
 * You are given an array of n pairs where pairs[i] = [left_i, right_i] and left_i < right_i.
 *
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
 *
 * Return the length longest chain which can be formed.
 *
 * You do not need to use up all the given intervals. You can select pairs in any order.
 */
public class MaximumLengthOfPairChain {

    public int maxLengthOfChain(int[][] pairs){
        //0. sort the pairs by end time
        Comparator<int[]> comp = Comparator.comparingInt(a -> a[1]);
        Arrays.sort(pairs, comp);

        //1. traverse array from index 1
        int chainLength = 1;
        int chainEnd = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            if(pairs[i][0] > chainEnd){
                chainLength++;
                chainEnd = pairs[i][1];
            }
        }

        return chainLength;
    }
}
