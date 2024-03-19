package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * Given an array of sorted integers nums (sorted in non-decreasing order)
 * Find the starting and ending position of a given target value
 *
 * Time must be O(log_n)
 *
 * If target value is not found, return [-1, -1]
 * ref: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class FirstAndLastPostionOfElementInArray {

    public static void main(String[] args) {
        int[] data = new int[]{5,7,7,8,8,10};
        int target = 8;

        int[] positions = doFindPositions(data, target);
        System.out.println(Arrays.toString(positions));
    }
    static int[] doFindPositions(int[] data, int target){
        int indexOfStartingPosition = doBinarySearch(data, target);

        int[] subArray = Arrays.copyOfRange(data, indexOfStartingPosition+1, data.length);

        int indexOfEndingPosition = doBinarySearch(subArray, target);
        return new int[]{indexOfStartingPosition, indexOfEndingPosition};
    }
    static int doBinarySearch(int[] nums, int target){
        if(nums.length == 0){
            return -1;
        }

        int left = 0;
        int right = nums.length -1;

        while (left <= right){
            int midpoint = left + (right - left)/2;
            if(nums[midpoint] == target){
                return midpoint;
            }
            else if (nums[midpoint] > target) {
                right = midpoint - 1;
            }
            else {
                left = midpoint + 1;
            }
        }

        return -1;
    }
}
