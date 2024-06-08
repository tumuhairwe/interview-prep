package com.tumuhairwe.prep.array.Permutations;

import java.util.ArrayList;
import java.util.List;

class PermutationsMe {
    public List<List<Integer>> permute(int[] nums) {
        //0. create 2D list and sublist
        List<List<Integer>> result = new ArrayList<>();

        //2. pass function that will backtrack to find all permutations
        permutationsRecursive(result, nums, 0);
        return result;
    }

    public void permutationsRecursive(List<List<Integer>> result, int[] nums, int startIndex){
        if(startIndex == nums.length){
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                subList.add(nums[i]);
            }
            result.add(subList);
            return;
        }

        for(int i=startIndex; i< nums.length; i++){
            swap(nums, startIndex, i);
            permutationsRecursive(result, nums, startIndex + 1);
            swap(nums, startIndex, i);
        }
    }

    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}