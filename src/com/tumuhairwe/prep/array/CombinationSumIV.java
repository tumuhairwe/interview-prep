package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 377 (medium)
 *
 * Given an array of distinct integers nums and a target, return the number of possible
 * combinations that add up to target
 *
 * ref: https://www.youtube.com/watch?v=dw2nMCxG0ik
 * ref: https://leetcode.com/problems/combination-sum-iv/description/
 */
public class CombinationSumIV {
    public static void main(String[] args) {

    }

    // where T = Target = N = nums.length
    // tc: O(T x N)
    // sc: O(T)
    static int combinationSum4(int[] nums, int target ){
        int[] dp_cache = new int[target + 1];
        dp_cache[0] = 1;

        for (int currentSum = 0; currentSum < dp_cache.length; currentSum++) {
            for (int num : nums){
                if(currentSum - num >= 0){
                    dp_cache[currentSum] += dp_cache[currentSum - num];
                }
            }
        }
        return dp_cache[target];
    }
    public static int combinationSum(int[] nums, int target){
        Map<Integer, Integer> memo = new HashMap<>();
        return helper(nums, target, memo);
    }

    private static int helper(int[] nums, int target, Map<Integer, Integer> memo) {
        // base case for dp
        if(target == 0){
            return 1;
        }
        else if(target < 0){
            return 0;
        }
        // use memo/cache
        else if (memo.containsKey(target)){
            return memo.get(target);
        }

        int numberOfWays = 0;
        for (int num : nums){
            numberOfWays += helper(nums, target - num, memo);
        }
        memo.put(target, numberOfWays);
        return numberOfWays;
    }
}
