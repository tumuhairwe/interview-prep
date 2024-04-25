package com.tumuhairwe.prep.window;

/**
 * LeetCode 209 (Medium) Minimum Size Sub array Sum
 *
 *  Given n positive integers and positive integer target, find the minimal length of a CONTIGUOUS sub-array
 *  for which the sum is <= target, if there isn't one, return -1;
 *  return sum
 *
 *  e.g. arr = [2,3,1,2,4,3] and target = 7
 * Solution:
 *  - Use two-pointers (both at the beginning) : left_boundary, right_boundary
 *  - Iteratively move window until window includes all of accumulatedSum/running_sum [rightIndex=0, leftIndex=NUM]
 *  - if you exceed the target, decrement running_sum by nums[leftBoundary]
 *  - Move leftBoundary++
 *  - Repeat iteration while calculating length of sub-array (result = Math.min( previousResult, rightBoundary+1 - leftBoundary)
 *
 * ref: https://leetcode.com/problems/minimum-size-subarray-sum/description/
 * ref: https://www.youtube.com/watch?v=jKF9AcyBZ6E
 */
public class MinimumSizeArraySum {
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,1,2,4,3};
        int target = 7;
        Integer result = minSubArray(target, arr);
        System.out.println("Given target=" + target + " " + result);
    }

    static int minSubArray(int target, int[] nums){
        int result = Integer.MAX_VALUE;

        int leftBoundary = 0;
        //int rightBoundary = 0;
        int running_sum = 0;

        for (int rightBoundary = 0; rightBoundary < nums.length; rightBoundary++) {
            running_sum += nums[rightBoundary];

            while (running_sum >= target){
                result = Math.min(result, rightBoundary+1 - leftBoundary);
                running_sum -= nums[leftBoundary];
                leftBoundary++;
            }
        }

        return (result != Integer.MAX_VALUE) ? result : 0;
    }
}
