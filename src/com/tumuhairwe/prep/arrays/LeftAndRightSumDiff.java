package com.tumuhairwe.prep.arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * LeetCode 2574 (easy)
 * Given a 0-indexed int[] nums, find a 0-indexed in[] ans where
 * ans.length == nums.length;
 * ans[i] = | leftSum[i] - rightSum[i]
 *
 * Where:
 *
 * leftSum[i] is the sum of elements to the left of the index i in the array nums. If there is no such element, leftSum[i] = 0.
 * rightSum[i] is the sum of elements to the right of the index i in the array nums. If there is no such element, rightSum[i] = 0.
 * Return the array answer.
 *
 * ref: https://leetcode.com/problems/left-and-right-sum-differences/description/
 * ref: https://leetcode.com/problems/left-and-right-sum-differences/solutions/3308546/easy-java-solution-intuition-lbeginner-friendly/
 */
public class LeftAndRightSumDiff {

    public static void main(String[] args) {
        int[] arr = {10, 4, 8, 3};
        System.out.println(Arrays.toString(leftRightDifference_bigO_of_n(arr)));
        System.out.println(Arrays.toString(leftRightDifference_bigO_of_n(new int[]{1})));
    }
    /**
     * Solution summary
     * - init vars (leftSum=0, rightSum=0, ans[])
     * - forward loop thru the arr & calc rightSum;
     * - forward loop thru the arr & increment leftSum while decrementing rightSum
     * - populate ans[i] = abs( leftSum - nums[i] -
     * ref: https://leetcode.com/problems/left-and-right-sum-differences/solutions/3233427/java-actual-constant-space-solution/
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] leftRightDifference_bigO_of_n(int[] nums) {
        int[] answer = new int[nums.length];
        int leftSum = 0;
        int rightSum = IntStream.of(nums).sum();
//        for (int r : nums){   // same
//            rightSum += r;
//        }

        for (int i = 0; i < nums.length; i++) {
            leftSum += nums[i];
            rightSum -= nums[i];
            int leftSumWithoutSelf = leftSum - nums[i];
            answer[i] = Math.abs(leftSumWithoutSelf - rightSum);
            //answer[i] = Math.abs((leftSum - nums[i]) - rightSum); // same
        }

        return answer;
    }
    /**
     * Solution summary
     * - init vars (answer[], right[], left[]), leftSum=0, rightSum=0;
     * - left-to-right: calc right[i] * accumulate rightSum
     * - right-to-left: calc left[i] * accumulate leftSum
     * - populate answer[]
     * - return answer[]
     *
     * TC: O(n)
     * SC: O(n)
     */
    public int[] leftRightDifference(int[] nums) {
        // calc right sum
        int rightSum = 0;
        int[] right = new int[nums.length];
        for (int i = nums.length - 1; i>=0; i--) {
            right[i] = rightSum;
            rightSum += nums[i];
        }

        // calc left sum
        int leftSum = 0;
        int[] left = new int[nums.length];
        for (int i = 0; i <nums.length-1; i++) {
            left[i] = leftSum;
            leftSum += nums[i];
        }

        // calc answer
        int[] answer = new int[nums.length];
        //for (int i = 0; i < nums.length; i++) {   // same
        for(int i = nums.length-1; i>=0; i--){
            answer[i] = Math.abs(leftSum - rightSum);
        }

        IntStream.of(nums).sum();

        return answer;
    }
}
