package com.tumuhairwe.prep.twopointers;

/**
 * LeetCode 45. Jump Game II (medium)
 *
 * You are given a 0-indexed array of integers nums of length n.
 * You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1].
 * The test cases are generated such that you can reach nums[n - 1].
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
     * Solution summary
     * - Main idea is to be greedy. Say the range of the current jump is [curBegin, curEnd], and curFarthest s the farthest
     * all points i [curBegin, curEnd] can reach,
     * - iterate over all the points and find the max/farthest,
     * - once the current point reached curEnd, trigger another jump and set the new curEnd to curFarthest
     * - keep iterating until you reach the nums.length-1
     * - return the number of jumps
     *
     * @param nums
     * ref: https://leetcode.com/problems/jump-game-ii/solutions/3758457/java-0ms-100-easy-solution/
     */
    public static int jump2(int[] nums){
        int minJumps = 0;
        int curEnd = 0;

        //the furthest point that all points in [curBegin, curEnd] can reach
        int curFarthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            // update the farthest reachable index of this jump
            curFarthest = Math.max(curFarthest, i + nums[i]);

            // if we finish the starting-range of this jump. move on to the starting range of the next jump
            if(i == curEnd){
                minJumps++;
                curEnd = curFarthest;
            }
        }

        return minJumps;
    }

    /**
     * Solution Summary
     * - initialize 3 vars (numSteps, p1, and p2)
     * - Traverse the array using 2 pointers (fast & slow)
     * - calculate the FARTHEST each iteration can go (farthest = max(farthest, nums[i) )
     * - update both pointers( l = r+1, and r = farthest), and numSteps++
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
