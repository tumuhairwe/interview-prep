package com.tumuhairwe.prep;

/**
 * LeetCode 136 (easy)
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * ref: https://leetcode.com/problems/single-number/description/
 * ref: https://www.youtube.com/watch?v=qMPX1AOa83k
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] arr = {2,2,1};
        System.out.println("Should be 1: "+ singleNumber(arr));
    }
    /**
     * Solution summary (use XOR on all the entries)
     * - This is based on the idea that if you XOR a number with itself, you'll get 0
     * e.g. 9 XOR <<another_number>> = {{result}}
     *      9 XOR <<result>> = {{another-number}}
     *  - by XOR-ing all numbers in the input array ... we're guaranteed that all duplicates will cross/zero each other
     *  out we will be left with the one non-duplicate number
     *
     *  - initialize result = 0;
     *  - for each element on nums ..
     *  - $element ^ result
     *  - after the for-loop, result == non-duplicate number
     */
    public static int singleNumber(int[] nums){
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            result = nums[i] ^ result;
        }
        return result;
    }
}
