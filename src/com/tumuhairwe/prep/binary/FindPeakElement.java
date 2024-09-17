package com.tumuhairwe.prep.binary;

/**
 * LeetCode 162 (medium)
 * A peak element is an element that is strictly greater than its neighbors
 *
 * Given a 0-indexed array nums, find a peak element and return its index. If nums contains multiple peaks
 * , return the index of any of the peaks.
 * You can assume that an element is considered strictly greater than a neighbor that is outside the array
 */
public class FindPeakElement {
    public static void main(String[] args) {

    }

    /**
     * Solution summary
     * - base case: if arr.length ==1, return 0
     * - base case: if edge element if peak, return its index
     * - do binary search
     *    - if peak lies on left, reduce search space by half
     *    - if peak lies on right, reduce search space by half
     *    - if we've found peak, return its index
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums){
        //0. base case: if there's only 1 element, return 0
        if(nums.length == 1){
            return 0;
        }

        //1. check if 0-the or n-1th element is the peak
        if(nums[0] > nums[1]){
            return 0;
        }
        if(nums[nums.length - 1] > nums[nums.length - 2]){
            return nums.length - 1;
        }

        //2. do binary search
        int left = 1;
        int right = nums.length - 2;
        while (left <= right){
            int mid = left + (right - left)/2;
            // if peak lies on the left, reduce search space by half
            if(nums[mid] < nums[mid + 1]){
                left = mid + 1;
            }

            // if peak lies on the right, reduce search space by half
            else if(nums[mid] < nums[mid - 1]){
                right = mid - 1;
            }

            else{
                //4. if we've found the peak ... return it
                if(nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]){
                    return mid;
                }
            }
        }

        return -1;
    }
}
