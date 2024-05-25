package com.tumuhairwe.prep;

/**
 * LeetCode 704 (easy)
 * Given an array of integers nums which is sorted ina ascending order and an
 * integer target, implement a search that runs in O(log n) for target
 *
 * if target doesn't exist, return -1
 *
 * Solution Summary
 * - Base case: if array if empty, return -1. If array length is 1, check if index == target. If not return 1;
 * - Initialize 2 pointers left and right (0 and nums.length)
 * - while left <= right,
 *      - split array in half
 *      - if nums[mid] == target ... return mid
 *      - if nums[mid] > target ... shrink search space by setting right = mid -1;
 *      - if nums[mid] > target ... shrink search space by setting left = mid + 1;
 *  - if still not found ... i.e. target doesn't exist, return -1
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,3,5,9,12};
        int target = 9;
        int result = binarySearch(nums, target);

        System.out.println("Result: should be 4 : " + result);

        nums = new int[]{-1,0,3,5,9,12};
        target = 2;
        result = binarySearch(nums, target);

        System.out.println("Result: should be -1 : " + result);
    }
    static int binarySearch(int[] nums, int target){
        if(nums.length == 0){
            return -1;
        }
        else if(nums.length == 1){
            return (nums[0] == target) ? 0 : -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            //int mid = right + left / 2;

            if (nums[mid] == target){
                return mid;
            }
            else if(nums[mid] > target){
                right = mid -1;
            }
            else {
                left = mid + 1;
            }
        }

        return  -1;
    }
}
