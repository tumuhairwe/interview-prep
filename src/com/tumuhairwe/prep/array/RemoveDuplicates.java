package com.tumuhairwe.prep.array;

/**
 * LeetCode 26 (easy)
 *
 * 26. Remove Duplicates from Sorted Array
 * ref: https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] arr ={0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(arr));
    }
    public static int removeDuplicates(int[] nums){
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i - 1]){
                nums[i] = nums[j++];
            }
        }

        return j;
    }
}
