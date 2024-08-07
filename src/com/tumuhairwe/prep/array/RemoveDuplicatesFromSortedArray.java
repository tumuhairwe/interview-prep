package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 80 (medium)
 *
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra
 *
 * ref: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,2,2,3};
        System.out.println(removeDuplicates2(arr) + "-> " + Arrays.toString(arr));
    }
    public static int removeDuplicates2(int[] nums){
        if (nums.length <= 2){
            return nums.length;
        }

        int uniqueCount = 2;
        for (int p2 = 2; p2 < nums.length; p2++) {
            if(nums[p2] != nums[uniqueCount - 2]){
                nums[uniqueCount] = nums[p2];
                uniqueCount++;
            }
        }
        return uniqueCount;
    }
    static int removeDuplicates(int[] nums){
        if (nums.length <= 2){
            return nums.length;
        }

        int count = 0;
        for (int n :  nums){
            if(count < 2 || nums[count - 2] != n){
                nums[count++] = n;
            }
        }

        return count;
    }
}
