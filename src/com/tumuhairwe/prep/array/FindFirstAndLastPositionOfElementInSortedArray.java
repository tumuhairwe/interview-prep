package com.tumuhairwe.prep.array;

import java.util.Arrays;

public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        int[] arr = new int[]{5,7,7,8,8,10};
        //System.out.println(Arrays.toString(searchRange(arr, 8)));

        arr = new int[]{1};
        System.out.println(Arrays.toString(searchRange(arr, 1)));
    }

    /**
     * Solution summary
     * - do binary search to search for the target
     * - when you find the target ..
     *      - implement a search for that target that simply expands the boundaries (left-- and right++)
     * - if target is not found, return [-1, -1
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};

        // 0. base case
        if(nums.length == 0){
            return  result;
        }

        //0 do binary search
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right -left)/2;

            if(nums[mid] == target){
                return searchRanges(nums, target, mid);
            }
            else if(target > nums[mid]){
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        // when target is found ... search for boundaries
        return result;
    }

    static int[] searchRanges(int[] nums, int target, int index){
        int left = index;
        int right = index;
        int[] result = new int[]{left, right};

        while (left >= 0 && nums[left] == target) {
            result[0] = left;
            left--;
        }
        while (right <= nums.length - 1 && nums[right] == target) {
            result[1] = right;
            right++;
        }

        return new int[]{left +1, right -1};
    }
}
