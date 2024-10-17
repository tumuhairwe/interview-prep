package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 491 (medium)
 * Given an integer array nums, return all the difference possible
 * non-decreasing subsequences of the given array with at least 2 elements
 * You may return them in any order
 *
 * ref: https://leetcode.com/problems/non-decreasing-subsequences/description/
 */
public class NonDecreasingSubsequences {
    public static void main(String[] args) {

    }

    /**
     * Solution summary (backtrack impl
     * - init vars (returnable globalSet -- to dedupe sequences, localList)
     * - call backtrac(nums_array, index=0, )
     *   - if(index == nums.length) ... add sequence to globalResult if sequence.size >= 2
     *   - if sequence is increasing ...
     *      - add nums[index] to sequence
     *      - recursively call backtrack(nums, index + 1, sequence, result)
     *      - remove last-added element to sequence
     *   - recursively call backtrack()
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> sequence = new ArrayList<>();
        backtrack(nums, 0, sequence, result);

        return new ArrayList<>(result);
    }

    void backtrack(int[] nums, int index, List<Integer> sequence, Set<List<Integer>> result){
        if(index == nums.length){
            if(sequence.size() >= 2){
                result.add(sequence);
            }
        }

        // if it is an increasing subsequence
        //boolean isIncreasing = sequence.get(sequence.size() -1) <= nums[index];
        if(sequence.isEmpty() || sequence.get(sequence.size() - 1) <= nums[index]){
            sequence.add(nums[index]);                     // add from sequence
            backtrack(nums, index + 1, sequence, result);
            sequence.remove(sequence.size() - 1);   // remove from sequence
        }

        // if is NOT increasing ... backtrack ... there might be increasing sequence ahead
        backtrack(nums, index + 1, sequence, result);   // result of type Set will dedup duplicate sequences
    }
}
