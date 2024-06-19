package com.tumuhairwe.prep.twopointers;

/**
 * LeetCode 45. Jump Game II (medium)
 *
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 *
 * ref: https://leetcode.com/problems/jump-game-ii/description/
 * ref: https://www.youtube.com/watch?v=dJ7sWiOoK7g
 */
public class JumpGameII {

    public static void main(String[] args) {
        int[] arr= new int[]{2,3,1,1,4};
        System.out.println("Should be 2 " + jump(arr));
    }

    /**
     * Solution Summary
     * - initialize 3 vars (numSteps, p1, and p2)
     * - Traverse the array using 2 pointers
     * - calculate the FARTHEST each iteration can go (farthest = max(farthest, nums[i)
     * - update both pointers( l = r+1, and r = farthest, and numSteps++
     * - return totalNumSteps
     */
    public static int jump(int[] nums){
        //0. declare vars
        int totalNumberOfSteps =0;
        int leftPointer = 0;
        int rightPointer = 0;

        //1. find the farthest (sing 2 pointers)
        while(leftPointer < nums.length -1){
            int farthest = 0;
            for (int i = leftPointer; i < nums.length - 1; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }

            //2. update pointers
            leftPointer = rightPointer + 1;
            rightPointer = farthest;
            totalNumberOfSteps++;
        }

        //3. return total number of steps
        return totalNumberOfSteps;
    }
}
