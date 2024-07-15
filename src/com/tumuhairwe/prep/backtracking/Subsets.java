package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 78
 *
 * Given an array nums of unique integers, return all possible subsets.
 * The solution set must not contain duplicate sets. Return the solution in any order
 *
 * ref: https://leetcode.com/problems/subsets/
 * ref: https://www.youtube.com/watch?v=REOH22Xwdkk
 *
 * ref for backtracking template: https://leetcode.com/problems/subsets/solutions/27281/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning/
 */
public class Subsets {

    /**
     * Solution summary
     * - sort arr[]
     * - declare superset
     * - use backtracking
     * - return results
     */
    public List<List<Integer>> subsets(int[] nums){
        //0. sort
        Arrays.sort(nums);

        //1 declare vars
        List<List<Integer>> results = new ArrayList<>();

        //2 backtrack
        backtrack(nums, 0, results, new ArrayList<>());

        return  results;
    }

    /**
     * Backtracking solution summary
     * - add subset to superset
     * - iterate array starting from starting index
     * - add nums[i] to array -> recursively call backtrack -> remove recently added num
     */
    public void backtrack(int[] nums, int startIndex, List<List<Integer>> superset, List<Integer> subset){
        //3. add subset to super set
        superset.add(new ArrayList<>(subset));

        //4.iterate from startIndex
        for (int i = startIndex; i < nums.length; i++) {
            //5 add to set
            subset.add(nums[i]);

            // 6. backtrack
            backtrack(nums, i+1, superset, subset);

            // 7. remove from set
            subset.remove(subset.size() - 1);
        }
    }
}
