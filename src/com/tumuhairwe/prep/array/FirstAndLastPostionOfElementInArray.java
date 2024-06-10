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
//    static int[] doFindPositions(int[] data, int target){
//        int indexOfStartingPosition = doBinarySearch(data, target);
//
//        int[] subArray = Arrays.copyOfRange(data, indexOfStartingPosition+1, data.length);
//
//        int indexOfEndingPosition = doBinarySearch(subArray, target);
//        return new int[]{indexOfStartingPosition, indexOfEndingPosition};
//    }
    static int[] doBinarySearch(int[] nums, int target){
//        if(nums.length == 0){
//            return -1;
//        }

        int left = 0;
        int right = nums.length -1;
        int[] result = new int[]{-1, -1};

        while (left <= right){
            int midpoint = left + (right - left)/2;
            if(nums[midpoint] == target){
                findEdges(midpoint, nums, target);
                break;
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
        int[] result ={left, right};

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
