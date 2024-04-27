package com.tumuhairwe.prep.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 15 (Medium) 3Sum triplets == zero
 *
 * Given an array of n integers
 * ... and 3 elements a, b, c such that a+ b + c = 0
 * Find all unique triplets in the array that give the sum of ZERO
 * i.e. find all UNIQUE Sets of 3 that add up to zero
 *
 * ref: https://leetcode.com/problems/3sum/description/
 */
public class ThreeSumProblem {
    // --- optional 0. Dedupe ---

    public static void main(String[] args) {
        int[] nums = new int[]{-1, -2, 0, 4, 3, 7, 9};
    }

    private static int[] threeSum(int[] nums){
        // 1. Sort
        Arrays.sort(nums);  // n2

        // 2. declare response/output array
        List<List<Integer>> output = new LinkedList<>();
        for (int i = 1; i < nums.length - 2; i++) { // to avoid going out of bounds
            if(nums[i] == nums[i+1]){       // continue if they're not different
                continue;
            }

            // do stuff
            int lowerBound = i+1;
            int upperBound = nums.length -1;
            int sum = 0 - nums[i];

            while (lowerBound < upperBound){
                if(nums[lowerBound] - nums[upperBound] == sum) {
                    int thisNumber = nums[i];
                    int low = nums[lowerBound];
                    int high = nums[upperBound];
                    output.add(Arrays.asList(thisNumber, low, high));

                    // skip duplicates
                    while (lowerBound < upperBound && low == nums[lowerBound+1])    lowerBound++;
                    while (lowerBound < upperBound && high == nums[upperBound-1])    upperBound--;

                    lowerBound++;
                    upperBound++;
                }
                else if(nums[lowerBound] + nums[upperBound] > sum){
                    upperBound--;
                }
                else {
                    lowerBound++;
                }
            }
        }

            return nums;
    }
}
