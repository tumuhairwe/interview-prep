package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * LeetCode 1 (Easy)
 * Given an array of Integers length n.
 * Find the indices of 2 numbers a & b that add up to the target/Sum S
 *
 * Constraints:
 *  - You may not use the same element twice
 *  - You may assume that each input has exactly one solution
 *
 *  ref: https://leetcode.com/problems/two-sum/description/
 *
 *  Example:
 *  Input: nums = [7,2,13,11], target = 9
 *  Output: [0,1]
 *
 *  Solution summary: (uses constant extra space)
 *  - As you iterate thru the nums array
*   - find the complement (target - nums[i])
*   - if diff exists in a map_of_compliments -> return { complement, nums[i] }
*   - if diff does not exist, add it to map
 */
public class TwoSumProblem {

    public static void main(String[] args) {
        int nums[] = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 15;
        int [] result = twoSum(nums, 15);
        System.out.println(result[0] + " " + result[1]);

        System.out.println("The indices with whose sum is " + target + " are " + Arrays.toString(result));
    }

    /**
     * time complexity = O(n) ... i.e. linear
     * space complexity = O(n) ... i.e. linear space (worst case, you'd have to store all the numbers -1)
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            int diff = target - num;
            if(numMap.containsKey(diff)){
                return new int[]{ num, i};
            }

            else numMap.put(num, i);    // key = Integer, val = index
        }

        return new int[]{ -1, -1};
    }
}
