package com.tumuhairwe.prep;

/**
 * LeetCode 137 (Single Number II) medium
 * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 */
public class SingleNumberII {

    // ref: https://leetcode.com/problems/single-number-ii/solutions/3527570/mastering-bitwise-operations-a-guide-to-boost-your-interview-preparation/
    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;
        for(int num : nums){
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }

        return ones;
    }
}
