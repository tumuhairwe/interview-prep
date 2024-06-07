package com.tumuhairwe.prep.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * LeetCode 724 (easy)
 *
 * Given an array of integers nums, calculate the pivot index of this array.
 *
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
 *
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
 *
 * Return the leftmost pivot index. If no such index exists, return -1.
 */
public class PivotIndex {
    /**
     * Solution Summary
     * - Calculate sum of all ints in num[] -> rightSum
     * - calc leftSum, while decrementing right sum .. when they're equal, return
     * - if we never get to a scenario where rightSum == leftSum, return -1; (i.e. there's no pivot in array)
     */
    public int pivotIndex(int[] nums) {
        if(nums.length == 0){
            return -1;
        }

        //0. calc rightSum
        int rightSum = 0, leftSum = 0;
        rightSum = Arrays.stream(nums).sum();
        //rightSum= Arrays.asList(nums).stream().mapToInt(a -> a).sum();
//        for(int num : nums){
//            rightSum += num;
//        }

        //1. calc leftSum, while decrementing right sum .. when they're equal, return
        for(int i=0; i<nums.length; i++){
            rightSum -= nums[i];

            if(rightSum == leftSum){
                return i;
            }
            else{
                leftSum += nums[i];
            }
        }

        return -1;
    }
}
