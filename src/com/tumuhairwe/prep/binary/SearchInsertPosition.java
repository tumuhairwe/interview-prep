package com.tumuhairwe.prep.binary;

/**
 * LeetCode 35. Search Insert Position (easy)
 *
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class SearchInsertPosition {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,6};
        System.out.println(searchInsert(arr, 2));
    }

    /**
     * Solution summary
     * - perform binary search looking for target
     * - if target is not found, return left or low
     */
    static int searchInsert(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] > target){
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }
}
