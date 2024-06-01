package com.tumuhairwe.prep.array;

/**
 * LeetCode 55 (medium)
 * Given integer array nums. You are initially positioned at the array's first index, and each element in
 * the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * Solution Summary
 * - Start from the end of the array and iterate backwards
 * - keep track of value in nums[i] ---- as jumpDistance
 * - for every iteration, if jumpDistance is >= goalIndex, set goalIndex = i
 */
public class JumpGame {

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println("Can jump? " + canJump(nums));

        nums = new int[]{3,2,1,0,4};
        System.out.println("Can jump? " + canJump(nums));
    }
    public static boolean canJump(int[] nums){
        int goalIndex = 0;

        for (int i = nums.length - 1; i >0; i--) {
            int jumpDistance = nums[i];

            if(i + jumpDistance >= goalIndex){
                goalIndex = i;
            }
        }

        return goalIndex == 0;
    }
}
