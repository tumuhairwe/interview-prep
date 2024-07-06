package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 2177 (medium)
 *
 * Given an integer num, return three consecutive integers (as a sorted array) that sum to num.
 * If num cannot be expressed as the sum of three consecutive integers, return an empty array.
 *
 * ref: https://leetcode.com/problems/find-three-consecutive-integers-that-sum-to-a-given-number/description/
 */
public class ThreeConsecutiveIntegsThatSumToTarget {

    /**
     * Solution summary
     * - if num % 3 != 0, return empty array
     * - else return {equalPart + 1, equalPart, equalPart - 1}
     */
    public long[] sumOfThree(long num){
        if(num %3 == 0){
            long d = num/3;
            long[] arr = new long[]{d+1, d, d-1};

            Arrays.sort(arr);
            return arr;
        }

        return new long[0];
    }
}
