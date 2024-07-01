package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LeetCode 46
 * Given an array nums of distinct integers, return all possible permutations (in ay order)
 *
 * ref: https://leetcode.com/problems/permutations/
 * ref: https://www.youtube.com/watch?v=FZe0UqISmUw
 */
public class Permutations {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println("Resutt = " + permute(nums));
    }

    // diff btwen this and permutation II is that perm-II requires NO duplicates (i.e. uses visited set)
    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permute(ans, nums, 0);
        return ans;
    }

    /**
     * Base case: if startIndex == nums.length -> put convert nums[] -> list
     *          - Add list to global/results list
     * - for each entry in nums
     *      - swap with startIndex (received
     *      - call permute() with altered arr[]
     *      - undo the swap
     * - Thinking is recursive call will
     *      (for each recursive call until startIndex == nums.length)
     *      (add the whole collection to the results list in various states of the arr[]
     * swap and unswap the numbers ... so each successive recusive call can depend on temporarily modified collection
     *
     * TC: O(2 ^ n)
     * SC: O(1) --- If you don't include the results collection
     */
    private static void permute(List<List<Integer>> results, int[] nums, int startIndex) {
        if(startIndex == nums.length){
            List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
            results.add(list);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            swap(nums, startIndex, i);
            permute(results, nums, startIndex + 1);
            swap(nums, startIndex, i);
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
