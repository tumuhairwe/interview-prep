package com.tumuhairwe.prep.arrays;

import java.util.Arrays;

/**
 * LeetCode 462 (medium)
 * Given an integer array nums of size n, return the min number of moves required to
 * make all array elements equal
 *
 * In one move, you can increment or decrement an element of the array by 1.
 *
 * ref: https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/description/
 * ref: https://www.youtube.com/watch?v=FGgL5QxZLno
 */
public class MinimumMovesToEqualArrayElementsII {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        System.out.println(minMoves2(arr));

        int[] arr2 = {1,10,2,9};
        System.out.println(minMoves2(arr2));
    }

    /**
     * Solution summary
     * - Sort the array
     * - find the median
     * - calculate the steps (find Math.abs distance from every number to the median)
     * - return the steps
     */
    public static int minMoves2_median(int[] nums) {
        //0. sort the array
        Arrays.sort(nums);

        //1. find the algorithm
        int median = nums[nums.length/2];

        //2 calc the steps
        int steps = 0;
        for(int num : nums){
            steps += Math.abs(median - num);
        }

        return steps;
    }

    public static int minMoves2(int[] nums) {
        //0. sort array
        Arrays.sort(nums);

        //1. use 2 pointers to traverse array
        int p1 = 0;
        int p2 = nums.length;

        //2. calculate the steps using 2 pointers
        int steps = 0;
        while (p1 < p2){
            steps += nums[p2] - nums[p1];
            p1++;
            p2--;
        }

        return steps;
    }
}
