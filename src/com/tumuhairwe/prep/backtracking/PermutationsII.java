package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 47. Permutations II
 *
 * Given a collection of numbers nums, that might contain duplicates, return all possible unique permutations in any order
 *
 */
public class PermutationsII {

    public static void main(String[] args) {
        int[] arr = {3,3,0,3};
        System.out.println(permuteUnique(arr));
    }
    public static List<List<Integer>> permuteUnique(int[] nums){
        // 0. init vars
        List<List<Integer>> results = new ArrayList<>();

        //1. sort nums
        Arrays.sort(nums);

        //2. call recursive backtrack()
        backtrack(nums, results, new ArrayList<>(), new boolean[nums.length]);

        return results;
    }

    private static void backtrack(int[] nums, List<List<Integer>> results, List<Integer> paths, boolean[] visited) {
        if(paths.size() == nums.length){
            results.add(new ArrayList<>(paths));
        }

        // 3.backtrack
        for (int i = 0; i < nums.length; i++) {
            // 3.0 if already visited or its a duplicate and prev ha already been visited ... skip
            if(visited[i] || (i > 0 && nums[i - 1] == nums[i] && visited[i - 1])){
                continue;
            }

            // 3.1 mark as visited & add to path
            visited[i] = true;
            paths.add(nums[i]);

            // 3.2 backtrack
            backtrack(nums, results, paths, visited);

            // 3.3 unmark as visited and remove from path
            visited[i] = false;
            paths.remove(paths.size() - 1);
        }
    }
}
