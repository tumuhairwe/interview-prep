package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 90 II (medium)
 *
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets
 *  (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * ref: https://leetcode.com/problems/subsets-ii/description/
 */
public class SubsetsII {

    public static void main(String[] args) {
        int[] arr = {1,2,2};
        System.out.println(uniqueSubsets(arr));
    }
    public static List<List<Integer>> uniqueSubsets(int[] nums){
        //0. sort
        Arrays.sort(nums);

        List<List<Integer>> superset = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), superset);
        return superset;
    }

    static void backtrack(int[] nums, int startIndex, List<Integer> subset, List<List<Integer>> superset){
        superset.add(new ArrayList<>(subset));

        for (int i = startIndex; i < nums.length; i++) {
            if(i > 0 && nums[i] == nums[i -1]){
                continue;
            }
            // add nums to subset
            subset.add(nums[i]);
            backtrack(nums, i + 1, subset, superset);
            subset.remove(subset.size() - 1);
        }
    }
}
