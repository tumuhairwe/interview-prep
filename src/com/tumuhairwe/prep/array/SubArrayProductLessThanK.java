package com.tumuhairwe.prep.array;

/**
 * LeetCode 2461 medium
 *
 * Given an integer sub-array numbs and an integer K, find the maximum
 * sub-array sum of all the sub-arrays of numbers that meet the following conditions
 *
 * - The length of the sub-array is k
 * - All the elements of the sub-array are DISTINCT
 *
 * Return the maximum sub-array sum of all the sub-arrays that meet the conditions
 * If no sub-array meets the conditions, return 0;
 *
 * Solution:
 * - Use sliding window approach (set left =0, and right = 0)
 * - Iterate (while right has not bumped against nums.length
 * - set product = product * nums[rghtBoundary]
 * - do a nested while loop to make sure product has not exceeded K
 * - if so, undo the multiplication above and slide window to the left (leftBoundary++)
 *
 * ref: https://www.youtube.com/watch?v=SxtxCSfSGlo
 * ref: https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/description/
 */
public class SubArrayProductLessThanK {
    public static void main(String[] args) {
        int k = 3;
        int[] arr = new int[]{1,5,4,2,9,9,9};
        int result = numSubArrayProductLessThanK(arr, k);
        System.out.println("The sum of distinct subarrays with length " + k + " is " + result);
    }

    public static int numSubArrayProductLessThanK(int[] nums, int k){
        int product = 1;
        int result = 0; // number of sub-arrays

        // base case
        if(k <= 1){
            return 0;
        }

        int leftBoundary = 0;
        int rightBoundary = 0;  // nums.length

        while (rightBoundary < nums.length){
            product *= nums[rightBoundary];

            while (product >= k){
                product /= nums[leftBoundary];
                leftBoundary++;
            }

            result += (rightBoundary - leftBoundary) + 1;
            rightBoundary++;
        }

        return result;
    }
}
