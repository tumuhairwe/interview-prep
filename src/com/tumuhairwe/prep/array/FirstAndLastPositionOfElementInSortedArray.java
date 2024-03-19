package com.tumuhairwe.prep.array;

/**
 * Given a sorted array of integers (ascending order), find the starting
 * and ending position of a given value <i>target</i>
 *
 * The algo's runtime complexity must be 0(log_n)
 *
 * if the target is not found ... return {-1, -1}
 * ref: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 *
 * e.g. given nums = [5, 7, 7, 8, 8, 10] and target = 8
 * output = [first_occurrence, last_occurrence] i.e. [3, 4]
 *
 * e.g. given nums = [5, 7, 7, 8, 8, 10] and target = 6
 * output = [-1, -1]
 */
public class FirstAndLastPositionOfElementInSortedArray {

    /**
     * Nothing is log_n except binary search ...
     * most of the time sorted_array input === binary search
     *
     * Usually, with binary search ... you have left-boundary and right-boundary ...
     * and you keep looping while splitting the search_space in half as you look for <i>target</i>
     */
    public static void main(String[] args) {

    }

    public int[] searchRange(int[] nums, int target){
        int[] result = new int[2];
        result[0] = findStartingIndex(nums, target);
        result[1] = findEndingIndex(nums, target);

        return result;
    }

    private int findStartingIndex(int[] nums, int target) {
        int index = -1;

        int start = 0;
        int end = nums.length;

        while (start <= end){
            int midpoint = start + (end-start)/2;
            if(nums[midpoint] >= target){
                end = midpoint -1;
            }
            else {
                start = midpoint + 1;
            }
            if(nums[midpoint] == target) index = midpoint;
        }
        return index;
    }

    // binary search = O(log-n)
    private int findEndingIndex(int[] nums, int target) {
        int index = -1;
        int start = 0;
        int end = nums.length;

        while (start <= end){
            int midpoint = start + (end-start)/2;

            if(nums[midpoint] <= target){
                start = midpoint +1;
            }
            else {
                end = midpoint - 1;
            }
            if(nums[midpoint] == target) index = midpoint;
        }
        return index;
    }
}
