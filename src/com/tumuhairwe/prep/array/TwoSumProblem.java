package com.tumuhairwe.prep.array;

import java.util.*;

/**
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
 */
public class TwoSumProblem {

    public static void main(String[] args) {
        int nums[] = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int [] result = twoSum(nums, 15);
        System.out.println(result[0] + " " + result[1]);
    }

    /**
     * time complexity = O(n) ... i.e. linear
     * space complexity = O(n) ... i.e. linear space (worst case, you'd have to store all the numbers -1)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        //linkedList.offerFirst();
        linkedList.peekFirst(); linkedList.peekLast();
        linkedList.getFirst(); linkedList.getLast();
        linkedList.pollFirst(); linkedList.pollLast();
        //int target = 10;
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(numMap.containsKey(complement)){
                return new int[]{ numMap.get(complement), i};
            }

            else numMap.put(nums[i], i);    // key = Integer, val = index
        }

        throw new IllegalArgumentException("No match found");
    }

    // Time Complexity  = N^2
    private static int[] bruteFoce(int[] nums, int target){
        // Brute force -> 2 nested for loops -> in inner for-loop, skip index i & check if arr[i] + arr[j= == target
        int numbs[] = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9};
        //int target = 15;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if(j == i) continue;;

                int complement = target - numbs[i];
                ///int sum = nums[i] + nums[j];
                if(nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }

        throw new IllegalArgumentException("No match found");
    }
}
