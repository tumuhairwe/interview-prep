package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Combination Sum II
 * LeetCode 40 (medium)
 *
 * Given a collection of candidate numbers (candidates, and a target number (target)
 * find all unique combinations of candidates where the candidate numbers sum to target
 *
 * ref: https://leetcode.com/problems/combination-sum-ii/description/
 * ref: https://www.youtube.com/watch?v=IER1ducXujU
 *
 * Solution Summary
 * 1. Simulate adding the number of the current list
 * 2. Make recursive call (decrement target -- until its zero)
 * 3. when the target == 0, add that number that collection results list i.e. every thing in the current-array has totaled to target
 */
public class CombinationSumII {

    // DFS with recursion
    public static void main(String[] args) {
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> result = combinationSum2(candidates, target);
        System.out.println("Results are: " + result);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // 0. sort array
        Arrays.sort(candidates);

        // 1. make recursive call
        findCombinations(candidates, 0, target, new ArrayList<>(),result);
        return result;
    }


    // TC = 2^n -> recursive calls
    // SC = O(N)
    private static void findCombinations(int[] candidates, int index, int target, List<Integer> current, List<List<Integer>> result) {
        // 0. base case
        if(target == 0){
            result.add(current);
            return;
        }

        // 1. if target is a non-negative number, return without adding number to result list
        if(target < 0){
            return; // all candidates are positive number so there's no way we'll get a number to add that will reach target
        }

        // DFS (loop thru candidates, check for non-dupes,
        for (int i = 0; i < candidates.length; i++) {
            boolean theNumbersAreDifferent = candidates[i] != candidates[i-1];
            boolean thereIsNoPreviousNumber = i == index;   // starting from this index going forward
            if(thereIsNoPreviousNumber || theNumbersAreDifferent){ // if the numbers
                current.add(candidates[i]);
                findCombinations(candidates, i + 1, candidates[i], current, result);
                current.remove(current.size() - 1);
            }
        }
    }

}
