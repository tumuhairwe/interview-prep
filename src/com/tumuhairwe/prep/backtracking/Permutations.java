package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 46
 * Given an array nums of distinct integers, return all possible permutations (in ay order)
 *
 *
 */
public class Permutations {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println("Resutt = " + permute(nums));
    }

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        function(ans, nums, 0);
        return ans;
    }

    private static void function(List<List<Integer>> ans, int[] nums, int startIndex) {
        if(startIndex == nums.length){
            List<Integer> list = new ArrayList<>();
            for (int i=0; i< nums.length; i++){
                list.add(nums[i]);
            }

            ans.add(list);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            swap(nums, startIndex, i);
            function(ans, nums, startIndex + 1);
            swap(nums, startIndex, i);
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
