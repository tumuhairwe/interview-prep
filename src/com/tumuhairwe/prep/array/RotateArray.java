package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * Summary, given an array, rotate the array by K steps to the right
 *
 * Definition:
 * - A rotation is popping one number off the end of the array
 * - putting it to the front
 * - and moving everything else to the right
 *
 *  Example:
 *  Given nums = [1,2,3,4,5,6,7], k = 3
 *  output =>5,6,7,1,2,3,4]
 *
 *  Solution:
 *  i) Reverse all the numbers e,g. [1, 3, 4, 5, 6, 7] -> [7, 6, 5, 4, 3, 2, 1]
 *  ii) Reverse the first K numbers .e. [5, 6, 7,    4, 3, 2, 1]
 *  iii) Reverse the last n-k numbers e.g. the last 4 -> [5, 6, 7, 4, 3, 2, 1]
 *
 * ref: https://www.youtube.com/watch?v=gmu0RA5_zxs
 * ref: https://leetcode.com/problems/rotate-array/description/
 */
public class RotateArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        int k = 3;

        System.out.println("Initial: " + Arrays.toString(arr));
        rotate(arr, 3);
        System.out.println("Reverse (" + k + "): " + Arrays.toString(arr));
    }

    static void rotate(int[] nums, int k){
        k = k % nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }
}
