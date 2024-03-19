package com.tumuhairwe.prep;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 9;

        System.out.println(binarySearch(array, target));
    }
    static int binarySearch(int[] nums, int target){
        if(nums.length == 0){
            return  -1;
        }

        int left = 0;
        int right = nums.length -1;
        while (left <= right){
            int midpoint = left + (right - left) /2;

            if(nums[midpoint] == target){
                return midpoint;
            }
            else if(nums[midpoint] > target){
                right = midpoint -1;
            }
            else {
                left = midpoint + 1;
            }
        }

        return  -1;
    }
}
