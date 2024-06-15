package com.tumuhairwe.prep.array;

/**
 * LeetCode 945
 *
 * You are given an integer array nums. In one move, you can pick an index i where 0 <= i < nums.length and increment nums[i] by 1.
 *
 * Return the minimum number of moves to make every value in nums unique.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * ref: https://leetcode.com/problems/minimum-increment-to-make-array-unique/description/
 */
public class MinimumIncrementToMakeArrayUnique {

    public static void main(String[] args) {
        int[] arr = {1,2,2};
        System.out.println("Should be 1: " + minIncrementForUnique(arr));

        arr = new int[]{3,2,1,2,1,7};
        System.out.println("Should be 6: " + minIncrementForUnique(arr));
    }
    /**
     * Solution summary
     * - Find max number in nums
     * - create frequencyCount [] of size nums.length + max
     * - loop over frequencyCount and find duplicates (dupes = frequency - 1
     * - propagate the duplicates to the i+1 index
     * - reset the i-th index to 1
     * - add duplicates to the minNumberOFMoves
     * - return minNumberOFMoves
     */
    public static int minIncrementForUnique(int[] nums){
        //0. base case
        if (nums.length == 0){
            return 0;
        }

        //1. find max
        int max = 0;
        for (int n : nums){
            max = Math.max(n, max);
        }

        //2. create frequency map
        int[] frequencyCount = new int[max + nums.length];

        //3. populate freq map
        for (int n : nums){
            frequencyCount[n]++;
        }

        //4. loop over frequencyCount & find duplicates
        int minNumberOfMoves = 0;
        for (int i = 0; i < frequencyCount.length; i++) {
            if(frequencyCount[i] <= 1){
                continue;
            }

            //find duplicates
            int duplicates = frequencyCount[i] - 1;
            frequencyCount[i + 1] = duplicates + frequencyCount[i + 1];
            frequencyCount[i] = 1;
            minNumberOfMoves += duplicates;
        }

        return minNumberOfMoves;
    }
}
