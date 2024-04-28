package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * LeetCode 15 (Medium) 3Sum triplets == zero
 *
 * Given an array of n integers
 * ... and 3 elements a, b, c such that a+ b + c = 0
 * Find all unique triplets in the array that give the sum of ZERO
 * i.e. find all UNIQUE Sets of 3 that add up to zero
 *
 * ref: https://leetcode.com/problems/3sum/description/
 */
public class ThreeSumProblem {

    public static void main(String[] args) {
        //int[] nums = new int[]{-1, -2, 0, 4, 3, 7, 9};
        int [] nums = new int[]{-1,2,-1,-4};
        int target = 0;
        List<List<Integer>> results = threeSum(nums);

        System.out.println("Given nums: " + Arrays.toString(nums));
        System.out.println("Triplets with sum == (" + target + ") is " + results);
    }

    /**
     * LeetCode 15 (Medium) 3Sum triplets == zero
     *
     * Given an integer array nums, return all the triplets that total up to zero
     * [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     *
     * LeetCode 15 (Medium) 3Sum
     * ref: https://leetcode.com/problems/3sum/description/
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums){
        Set<List<Integer>> results = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();

        // 2. iterate thru the array using 2 pointers
        for (int i = 0; i < nums.length - 2; i++) {
            if(duplicates.add(nums[i])) {       // continue if they're not different UNIQUE
                for (int j = i + 1; j < nums.length; j++) {
                    int complement = -nums[i] - nums[j];

                    if(seen.containsKey(complement) && seen.get(complement) == i){
                        List<Integer> triplets = List.of(nums[i], nums[j], complement);
                        Collections.sort(triplets);

                        results.add(triplets);
                    }
                    seen.put(nums[i], i);
                }
            }
        }
        return new ArrayList<>(results);
    }
}
