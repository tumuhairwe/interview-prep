package com.tumuhairwe.prep.array;

/**
 * LeetCode 55 (medium)
 * Given integer array nums. You are initially positioned at the array's first index, and each element in
 * the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * Solution Summary (uses greedy algo instead of dp)
 * - Set the target/goal to be 0
 * - Start from the end of the array (be greedy) and iterate backwards
 * - keep track of value in nums[i] ---- as jumpDistance
 * - for every iteration, if jumpDistance is >= goalIndex, set goalIndex = i
 *
 * ref: https://www.youtube.com/shorts/3pRtOeZzGJ4
 * ref: https://www.youtube.com/watch?v=Yan0cv2cLy8
 * ref: https://www.youtube.com/watch?v=Zb4eRjuPHbM
 */
public class JumpGame {

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println("Can jump? " + canJump(nums));

        nums = new int[]{3,2,1,0,4};
        System.out.println("Can jump? " + canJump(nums));
    }

    //TC: O(n)

    /**
     * Solution summary
     * - traverse array from the end (set nums[i] = jumpDistance)
     * - foreach jumpDistance: if jumpDistance+i >= jumpGoal (i.e. you'll overshoot the target)
     * - update the jumpGoal to be i
     * - At the end, i must be 0 (zero-th index) -- if you can go from beginning to end
     * - Else return false
     */
    public static boolean canJump(int[] nums){
        int jumpGoal = 0;  // aka lastGoodIndexPosition

        for (int i = nums.length - 1; i >0; i--) {
            int jumpDistance = nums[i];

            if(i + jumpDistance >= jumpGoal){
                jumpGoal = i;
            }
        }

        return jumpGoal == 0;
    }
}
