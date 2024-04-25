package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 39 (combination sum)
 *
 * Given
 * - an array of DISTINCT integers candidates,
 * - and a target integer target
 *
 * Return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * - You may return combinations in any order
 * - The same number may be chosen from candidates an unlimited number of times
 * - Two combinations are unique if the frequency of at least 1 chosen numbers is different
 *
 * ref: https://leetcode.com/problems/combination-sum/description/
 */
public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = new int[]{2,3,6,7};
        int target = 7;

        // [[2,2,3],[7]]
        System.out.println("Result " + combinationSum(candidates, target));
    }
    static public List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();

        backtrack(candidates, target, answer, curr, 0);
        return answer;
    }

    static private void backtrack(int[] candidates, int target, List<List<Integer>> answer, List<Integer> curr, int index) {
        if(target == 0){
            answer.add(new ArrayList<>(curr));
        }
        else if(target < 0 || index >= candidates.length){
            return;
        }
        else {
            curr.add(candidates[index]);
            backtrack(candidates, target - candidates[index], answer, curr, index);

            Integer lastObject = curr.size() - 1;
            curr.remove(lastObject);
            backtrack(candidates, target, answer, curr, index + 1);
        }
    }
}
