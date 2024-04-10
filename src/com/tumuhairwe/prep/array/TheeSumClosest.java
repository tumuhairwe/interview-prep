package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 16 (Medium) 3Sum Closest
 *
 * Given an integer array of length n, and an integer target,
 * find the 3 integers in nums, whose sum is closest to target
 *
 * ref: https://www.youtube.com/watch?v=qBr2hq4daWE
 * ref: https://leetcode.com/problems/3sum-closest/description/
 */
public class TheeSumClosest {
    public static void main(String[] args) {
        int[] nums = {-1,2,1,-4};
        int target = 1;
        int result = threeSumClosest(nums, target);

        System.out.println("Given nums: " + Arrays.toString(nums));
        System.out.println("The closest sum to target (" + target + ") is " + result);
    }

    // TC = O( N^2) -- because we need to iterate thru nums for each entry i
    public static int threeSumClosest(int[] nums, int target){
        //int[] xxx = new int[]{-1,2,1,-4};
        // 0. set closestSum to baseline
        int closestSum = nums[0] + nums[1] + nums[nums.length - 1];

        // 1. sort (so that iterating with 2 pointers will have numbers in order)
        Arrays.sort(nums);

        // 2. iterate thru the array using 2 pointers (so
        for (int i = 0; i < nums.length - 2; i++) {
            int a_pointer = i + 1;
            int b_pointer = nums.length - 1;

            // 3. do the pointer technique with every possible i
            while (a_pointer < b_pointer){
                // when sorted ... nums[a_pointer] == lowest element and num[b_pointer] == highest element
                int current_sum = nums[i] + nums[a_pointer] + nums[b_pointer];
                if(current_sum > target){
                    b_pointer--;                     // decrement b_pointer
                }
                else {
                    a_pointer++;    // increment a_pointer
                }

                if(Math.abs(current_sum - target) < Math.abs(closestSum - target)){
                    closestSum = current_sum;
                }
            }
        }
        return closestSum;
    }
}
