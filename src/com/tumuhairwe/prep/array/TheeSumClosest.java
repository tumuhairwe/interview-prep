package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * LeetCode 16 (Medium) 3Sum (Closest to target)
 * LeetCode 15 (Medium) 3Sum triplets == zero
 */
public class TheeSumClosest {
    public static void main(String[] args) {
        int[] nums = {-1,2,1,-4};
        int target = 1;
        int result = threeSumClosest(nums, target);

        System.out.println("Given nums: " + Arrays.toString(nums));
        System.out.println("The closest sum to target (" + target + ") is " + result);

        nums = new int[]{-1,2,-1,-4};
        target = 0;
        List<List<Integer>> results = threeSum(nums);

        System.out.println("Given nums: " + Arrays.toString(nums));
        System.out.println("Triplets with sum == (" + target + ") is " + results);
    }


    /**
     * LeetCode 16 (Medium) 3Sum (Closest to target)
     *
     * Given an integer array of length n, and an integer target,
     * find the 3 integers in nums, whose sum is closest to target
     *
     * Solution Summary
     * - Set initial sum (e.g. sum = nums[0] + nums[1] + nums[nums.length]
     * - Sort the array
     *
     * - iterate thru array with 2 pointers (p1=i + 1, p2=nums.length -1)
     * - initialize currentSum = nums[i] + nums[p1] + nums[p2]
     * - while(p1 < p2) ... increment p1 if currentSum > target ... decrement p2 if currentSum < target
     * - diff between (currentSum - target) is less than diff between (globalClosestSum - target) ... update globalClosestSum= closestSum
     *
     * ref: https://www.youtube.com/watch?v=qBr2hq4daWE
     *
     * ref: https://leetcode.com/problems/3sum-closest/description/
     */
    public static int threeSumClosest(int[] nums, int target){
        //int[] xxx = new int[]{-1,2,1,-4};
        // 0. set closestSum to baseline
        int closestSum = nums[0] + nums[1] + nums[nums.length - 1];

        // 1. sort (so that iterating with 2 pointers will have numbers in order)
        Arrays.sort(nums);

        // 2. iterate thru the array using 2 pointers
        for (int i = 0; i < nums.length - 2; i++) {
            int a_pointer = i + 1;
            int b_pointer = nums.length - 1;

            // 3. do the pointer technique with every possible i
            while (a_pointer < b_pointer){
                // when sorted ... nums[a_pointer] == lowest element and num[b_pointer] == highest element
                int current_sum = nums[i] + nums[a_pointer] + nums[b_pointer];
                if(current_sum > target){
                    b_pointer--;                     // decrement b_pointer
                }
                else {
                    a_pointer++;    // increment a_pointer
                }

                if(Math.abs(current_sum - target) < Math.abs(closestSum - target)){
                    closestSum = current_sum;
                }
            }
        }
        return closestSum;
    }

    /**
     * LeetCode 15 (Medium) 3Sum triplets == zero
     *
     * Given an integer array nums, return all the triplets
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
