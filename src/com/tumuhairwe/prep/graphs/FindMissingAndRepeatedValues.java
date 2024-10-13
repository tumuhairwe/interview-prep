package com.tumuhairwe.prep.graphs;

import java.util.HashSet;
import java.util.Set;

/**
 * Find missing and repeated values
 *
 * You are given a 0-indexed 2D integer matrix grid of size n * n with values in the range [1, n^2].
 * Each integer appears exactly once except a which appears twice and b which is missing.
 * The task is to find the repeating and missing numbers a and b.
 *
 * Return a 0-indexed integer array ans of size 2 where ans[0] equals to a and ans[1] equals to b.
 */
public class FindMissingAndRepeatedValues {

    public static void main(String[] args) {

    }
    /**
     * Solution summary
     * - init vars (set of nums) -- remember range =[1, n^2]
     * - traverse 2D grid and
     *   - find repeated num
     *   - populate set ...
     * - iterate from 1 thru n^2 -> find missing
     * - return array of missing and repeated
     *
     * TC: O(n^2)
     * SC: O(n) -- because of storage in set & contains() operation
     */
    public static int[] findMissingAndRepeatedValues(int[][] grid){
        int missing = -1;
        int repeated = -1;
        int len = grid.length;

        // a) this impl stores the numbers
        Set<Integer> set = new HashSet<>();

        // find repeated
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(set.contains(grid[i][j])){
                    repeated = grid[i][j];
                }
                set.add(grid[i][j]);
            }
        }

        // find missing
        for (int i = 1; i <= len * len; i++) {
            if(!set.contains(i)){
                missing = i;
            }
        }

        return new int[]{missing, repeated};
    }

    /**
     * Solution summary
     * - init count[] of length (n*n) + 1
     * - traverse 2D grid and
     *   - count frequency_of_num
     *   - if count[col] == 2, set repeated
     * - traverse freqArr for all of freqArrLength
     * - if a count[i] == 0, set missing
     * - return arr[repeated, missing]
     *
     * TC: O(n^2)
     * SC: O(n^2) -- because of storage of frequency in array
     * ref: https://leetcode.com/problems/find-missing-and-repeated-values/
     */
    public static int[] findMissingAndRepeatedValues_optimizedFreqCounter(int[][] grid){
        int missing = -1;
        int repeated = -1;
        int len = grid.length;

        // a) this impl uses a frequency counter
        int freqArrLength = (len * len) + 1;
        int[] count = new int[freqArrLength];
        // find repeated
        for(int[] row : grid){
            for(int col : row){
                count[col]++;
                if(count[col] == 2){
                    repeated = col;
                }
            }
        }

        // find missing
        for(int i=1; i<=freqArrLength; i++){
            if(count[i] == 0){
                missing = i;
                break;
            }
        }

        return new int[]{repeated, missing};
    }
}
