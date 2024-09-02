package com.tumuhairwe.prep.twopointers;

/**
 * LeetCode 487 (medium)
 *
 * Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.
 * ref: https://leetcode.com/problems/max-consecutive-ones-ii/solutions/1508045/java-tc-o-n-sc-o-1-four-solutions-with-follow-up-handled/
 */
public class MaxConsecutiveOnesII {
    public static void main(String[] args) {
        int[] arr = {1,0,1,1,0};
        System.out.println("Should be 4 " + findMaxConsecutiveOnes(arr));

        int[] arr2 = {1,0,1,1,0,1};
        System.out.println("Should be 4 " + findMaxConsecutiveOnes(arr2));
    }

    /**
     * - init 2 vars (previousZeroIdx = -1; & maxNumZeros - 0;
     * - if (nums[i]) == 0, slide window forward (start = prevIdx + 1, end = 0) & update prevZeroIdx = end
     * - update maxNumZeros = with sizeOfWindow
     * int sizeOfWindow = end - start + 1;
     * - set maxNumZeros = Math.max(maxNumZeros, sizeOfWindow)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxNumZeros = 0;

        int start = 0;
        int prevZeroIdx = -1;
        for (int end = 0; end < nums.length; end++) {
            if(nums[end] == 0){
                start = prevZeroIdx + 1;
                prevZeroIdx = end;
            }

            maxNumZeros = Math.max(maxNumZeros, end - start + 1);
        }

        return maxNumZeros;
    }
}
