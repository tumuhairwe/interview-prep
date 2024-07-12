package com.tumuhairwe.prep.arrays;

/**
 * LeetCode 189 (medium)
 *
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * ref: https://www.youtube.com/watch?v=BHr381Guz3Y
 */
public class RotateArray {

    /**
     * Solution summary
     * - reverse entire array
     * - reverse 0 to k
     * - reverse k to nums.length;
     */
    public void rotate(int[] nums, int k){
        k = k % nums.length;    // in case K > nums.length;

        //0. reverse entire array
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;

            left++;
            right--;
        }

        //1. reverse 0 to k-1
        left = 0;
        right = k-1;
        while (left < right){
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;

            left++;
            right--;
        }

        //3 reverse k to arr.length
        left = k;
        right = nums.length;
        while (left < right){
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;

            left++;
            right--;
        }
    }
}
