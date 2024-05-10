package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * LeetCode 16 (Medium) 3Sum (Closest to target)
 * Given an integer array of length N and a target T, find the 3 numbers in nums that are closest to target
 *
 * 1. Sort the array
 * 2. Init closestSum = nums[0] + nums[1] + nums[nums.length - 1]
 * 3. inside a for-loop loop thru the array using 2 pointers (until i < length - 1)
 *  - p1 = from the front
 *  - p2 = from the end
 *  4. do an internal while-loop until p1 and p2 meet to calculate
 *      current_sum = nums[i] + num[p1] + nums[p2]
 *      if(current_sum > target) -> decrement p2;
 *      if(current_sum < target) -> increment p2;
 *  5. Update closest_sum, based on diff between target and current_sum
 * LeetCode 15 (Medium) 3Sum triplets == zero
 */
public class TheeSumClosest {
    public static void main(String[] args) {
        int[] nums = {-1,2,1,-4};
        int target = 1;
        int result = threeSumClosest(nums, target);

        System.out.println("Given nums: " + Arrays.toString(nums));
        System.out.println("The closest sum to target (" + target + ") is " + result);
    }


    /**
     * LeetCode 16 (Medium) 3Sum (Closest to target)
     *
     * Given an integer array of length n, and an integer target,
     * find the 3 integers in nums, whose sum is closest to target
     *
     * Solution Summary
     * - Set initial sum (e.g. sum = nums[0] + nums[1] + nums[nums.length]
     * - Sort the array
     *
     * - iterate thru array with 2 pointers (p1=i + 1, p2=nums.length -1)
     * - initialize currentSum = nums[i] + nums[p1] + nums[p2]
     * - while(p1 < p2) ... increment p1 if currentSum > target ... decrement p2 if currentSum < target
     * - diff between (currentSum - target) is less than diff between (globalClosestSum - target) ... update globalClosestSum= closestSum
     *
     * ref: https://www.youtube.com/watch?v=qBr2hq4daWE
     *
     * ref: https://leetcode.com/problems/3sum-closest/description/
     */
    public static int threeSumClosest(int[] nums, int target){
        // 1. sort (so that iterating with 2 pointers will have numbers in order)
        Arrays.sort(nums);

        // 1. set closestSum to baseline
        int closestSum = nums[0] + nums[1] + nums[nums.length - 1];

        // 2. iterate thru the array using 2 pointers
        for (int i = 0; i < nums.length - 2; i++) {
            int a_pointer = i + 1;
            int b_pointer = nums.length - 1;

            // 3. do the pointer technique with every possible i
            while (a_pointer < b_pointer && closestSum != target){
                // when sorted ... nums[a_pointer] == lowest element and num[b_pointer] == highest element
                int current_sum = nums[i] + nums[a_pointer] + nums[b_pointer];
                if(current_sum > target){
                    b_pointer--;                     // decrement b_pointer
                }
                else {
                    a_pointer++;    // increment a_pointer
                }

                if(Math.abs(current_sum - target) < Math.abs(closestSum - target)){
                    closestSum = current_sum;
                }
            }
        }
        return closestSum;
    }
}
