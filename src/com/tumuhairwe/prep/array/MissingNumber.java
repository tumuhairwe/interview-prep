package com.tumuhairwe.prep.array;

/**
 * LeetCode 268 (easy)
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 *
 *
 */
public class MissingNumber {

    public static void main(String[] args) {
        int[] nums = new int[]{3,0,1};
        System.out.println(calcMissingNumber(nums));

        nums = new int[]{0,1};
        System.out.println(calcMissingNumber(nums));

        nums = new int[]{9,6,4,2,3,5,7,0,1};
        System.out.println(calcMissingNumber(nums));
    }
    static int calcMissingNumber(int[] nums){
        int expectedSum =0;
        int total = nums.length * (nums.length + 1)/2;
        for (int i = 0; i < nums.length; i++) {
            expectedSum += nums[i];
        }

        return total - expectedSum;
    }
}
