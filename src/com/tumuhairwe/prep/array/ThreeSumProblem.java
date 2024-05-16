package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * LeetCode 15 (Medium) 3Sum triplets == zero
 *
 * Given an array of n integers
 * ... and 3 elements a, b, c such that a+ b + c = 0
 * Find all unique triplets in the array that give the sum of ZERO
 * i.e. find all UNIQUE Sets of 3 that add up to zero
 * Constraint:
 * - Can not have duplicates in solution
 *
 * ref: https://leetcode.com/problems/3sum/description/
 * ref: https://www.youtube.com/watch?v=jzZsG8n2R9A
 */
public class ThreeSumProblem {

    public static void main(String[] args) {
        int [] nums = new int[]{-1,0, 1,2,-1,-4};
        int target = 0;
        List<List<Integer>> results = threeSum_2_pointer(nums);

        System.out.println("Given nums: " + Arrays.toString(nums));
        System.out.println("Triplets with sum == (" + target + ") is " + results);
    }

    /**
     * LeetCode 15 (Medium) 3Sum triplets == zero
     *
     * Given an integer array nums, return all the triplets that total up to zero
     * [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     *
     * Solution Summary:
     * 0. Initialize Map<Integer, Integer> seen _ to hold seen/processed numbers + index of number
     * 0. Initialize Set<Integer> of duplicates to avoid double processing them
     * 0. Initialize Results List<List<Integer>> to be returned
     *
     *  1. iterate over nums while tracking duplicates in duplicates Set.
     *  2. Double for-loop (to calculate complement) ... if complement is not seen in a Map, add it (key= nums[i], value=index)
     *  3. If seen, compile list of triplets (nums[i], nums[j] ... from double for-loop, & complement (already seen)
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

    public static List<List<Integer>> threeSum_2_pointer(int[] nums) {
        //int target = 0;
        List<List<Integer>> results = new ArrayList<>();

        // 0. sort the array
        Arrays.sort(nums);

        // 1. iterate array using 2 pointers
        for(int i=0; i < nums.length - 2; i++){
            // skip numbers next to each other since the requirement is that they be distinct
            if(i != 0 && nums[i] == nums[i - 1]){
                continue;   // avoid duplicates
            }

            int a_pointer = i + 1;  // since we already have nums[i], start p1 from nums[i + 1]
            int b_pointer = nums.length - 1;    // inward moving p2
            int sum = -nums[i];  // same as 0 - nums[i]

            //2. traverse array with 2 pointers
            while(a_pointer < b_pointer){

                // 2a) if we found a match, add to result-set
                if(nums[a_pointer] + nums[b_pointer] == sum){
                    List<Integer> triplets = Arrays.asList(nums[i], nums[a_pointer], nums[b_pointer]);
                    results.add(triplets);

                    // 2a i) skip duplicates on the left
                    while (a_pointer < b_pointer && nums[a_pointer] == nums[a_pointer + 1]){
                        a_pointer++;
                    }
                    // 2b ii) skip duplicates on the right
                    while (a_pointer < b_pointer && nums[b_pointer] == nums[b_pointer - 1]){
                        b_pointer--;
                    }

                    a_pointer++;
                    b_pointer--;
                }

                // 2b) else if its too big ... decrement b_pointer
                else if(nums[a_pointer] + nums[b_pointer] > sum){
                    b_pointer--;
                }

                // 2c) else if its too small ... decrement b_pointer
                else{
                    a_pointer++;
                }
            }

        }
        return results;
    }
}
