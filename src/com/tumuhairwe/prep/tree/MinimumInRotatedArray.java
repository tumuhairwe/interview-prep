package com.tumuhairwe.prep.tree;

import java.util.Arrays;

/**
 * Given a sorted array ... its rotated ... find the minimum in it
 *
 * e.g. original arr = [1, 2, 3, 4, 5, 6]
 * rotated_arr = [4, 5, 6, 0, 1, 2, 3]
 *
 * Summary: Find the one entry where nums[i] is not less that num [i -1]
 *
 * LeetCode 153 Medium
 * ref: https://www.youtube.com/watch?v=IzHR_U8Ly6c
 * ref: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class MinimumInRotatedArray {

    public static void main(String[] args) {
        int[] arr = new int[]{3,4,5,1,2};
        int result = findMin(arr);
        System.out.println("Given " + Arrays.toString(arr) + " result=" + result);

        arr = new int[]{4,5,6,7,0,1,2};
        result = findMin(arr);
        System.out.println("Given " + Arrays.toString(arr) + " result=" + result);

        arr = new int[]{11,13,15,17};
        result = findMin(arr);
        System.out.println("Given " + Arrays.toString(arr) + " result=" + result);
    }

    public static int findMin(int[] nums) {
        if(nums.length == 0){
            return -1;
        }
        if (nums.length == 1){
            return nums[0];
        }

        int left = 0;
        int right = nums.length -1;

        while (left < right){
            int middle = left + (right - left)/2;
//            if(nums[middle] == target){
//                return middle
//            }
            if(middle > 0 && nums[middle] < nums[middle-1]){
                // its sorted
                return nums[middle];
            }
            else if(nums[left] <= nums[middle] && nums[middle] > nums[right]){
                left= middle + 1;
            }
            else {
                right = middle -1;
            }
        }
        return nums[left];
    }
}
