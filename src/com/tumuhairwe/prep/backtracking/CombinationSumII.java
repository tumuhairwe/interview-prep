package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 40 (medium) -- Combination Sum II
 *
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used once in the combination.
 * Note: The solution set must not contain duplicate combinations.
 *
 * ref: https://leetcode.com/problems/combination-sum-ii/description/
 * ref: permutations, combinations and subsets template -> https://leetcode.com/problems/subsets/solutions/27281/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning/
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target){
        Arrays.sort(candidates);

        List<List<Integer>> results = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), results);
        return results;
    }

    /**
     * Solution summary
     * - if currentSum == 0 ... return
     * - if currentSum == 0 ... add subset to superset
     * - else iterate thru candidates[] (skipping dupes) and
     *      - add num to subset,
     *      - recursively call backtrack
     *      - remove num from subset
     */
    void backtrack(int[] candidates, int currentSum, int startIndex, List<Integer> subset, List<List<Integer>> superset){
        if(currentSum < 0){
            return;
        }
        else if(currentSum == 0){
            superset.add(new ArrayList<>(subset));
        }
        else{
            for (int i = startIndex; i < candidates.length; i++) {
                if(i > 0 && candidates[i] == candidates[i-1]){
                    continue;
                }

                subset.add(candidates[i]);
                backtrack(candidates, currentSum-candidates[i], i+1, subset, superset);
                subset.remove(subset.size() - 1);
            }
        }
    }
}
