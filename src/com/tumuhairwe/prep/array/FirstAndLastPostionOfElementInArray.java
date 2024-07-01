package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 34 (medium)
 *
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

        int[] positions = doBinarySearch(data, target);
        System.out.println(Arrays.toString(positions));
    }

    /**
     * Solution summary: Binary search for a range
     * - do binary search to look for target
     * - when you find target, call special method to* 10 find range
     * - searchRange() basically expands around target as long as nums[left] == target or nums[right] == target
     */
    static int[] doBinarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length -1;
        int[] result = new int[]{-1, -1};

        while (left <= right){
            int midpoint = left + (right - left)/2;
            if(nums[midpoint] == target){
                return findEdges(midpoint, nums, target);
            }
            else if (nums[midpoint] > target) {
                right = midpoint - 1;
            }
            else {
                left = midpoint + 1;
            }
        }

        return result;
    }

    public static int[] findEdges(int mid, int[] nums, int target){
        int left = mid;
        int right = mid;
        int[] result = new int[]{left, right};

        //0. move left as long as nums[left] == target -> update result[0]
        while (left >=0 && nums[left] == target){
            result[0] = left;
            left--;
        }

        //0. move left as long as nums[right] == target -> update result[1]
        while (right <= nums.length -1 && nums[right] == target){
            result[1] = right;
            right++;
        }
        return result;
    }
}
