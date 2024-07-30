package com.tumuhairwe.prep.twopointers;

import java.util.*;

/**
 * 4Sum
 * Given an array nums of n integers, return an array of the unique quadruplets
 * [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 * - they lie between 0 and n i.e. 0 <= a, b, c, d < n
 * - they're distinct
 * - they sum up to target i.e. nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * ref: https://leetcode.com/problems/4sum/
 */
public class FourSum {

    public List<List<Integer>> fourSum_impl2(int[] nums, int target){
        //0. create freqMap
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    int desired = target - nums[i] - nums[j] - nums[k];
                    if(freqMap.containsKey(desired) && isDistinct(nums[i], nums[j], nums[k], desired)){
                        result.add(List.of(desired, nums[i], nums[j], nums[k]));
                    }
                }
            }
        }

        return result;
    }
    static boolean isDistinct(int a, int b, int c, int d){
        return (a != b) && (a != c) && (a != d)
                && (b != c) && (b != d)
                && (c != d);
    }

    /**
     * TC: O(n^3)
     * SC: O(n)
     *
     * Solution summary
     * - sort the array ... (easier to find duplicates if they're next to each other)
     * - formulate 2 nested for-loop (which skipping over duplicates)
     * - Create 2 pointers (p1=i, p2=j, p3=j+1, p4=nums.length-1)
     * - while moving p3 and p4 inward ...
     *      - calculate the sums of the numbers at those 4 pointers ....
     *      - if sum == target
     *          - add to resultList
     *          - increment p3 and p4
     *          - move p3 and p4 inward while skipping duplicates
     *      - if sum < target ... move p3 forward
     *      - if sum > target ... move p4 backward
     */
    public List<List<Integer>> fourSum(int[] nums, int target){
        // declare vars
        List<List<Integer>> result = new ArrayList<>();

        //0. sort array (so we don't have to keep track of seen/unseen
        Arrays.sort(nums);

        //1. iterate over nums & skip dupes
        for (int i = 0; i < nums.length; i++) {
            if(i > 1 && nums[i] == nums[i-1]){
                continue;
            }

            //2. iterate over nums & skip dupes
            for (int j = i+1; j < nums.length; j++) {
                if(j > i+1 && nums[j] == nums[j+1]){
                    continue;
                }

                //3. create 2 pointers low && high
                int low = j+1;
                int high = nums.length -1;

                //4. move pointers in-ward until you find a sum == target
                while (low < high){
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];

                    // 5if sum == target .. add to resultList ... and move the 2 pointers in ward
                    if(sum == target){
                        result.add(List.of(nums[i], nums[j], nums[low], nums[high]));

                        // move pointers inward
                        low++;
                        high--;

                        // move low pointer forward to avoid dupes
                        while (low < high && nums[low] == nums[low + 1]){
                            low++;
                        }

                        // move high pointer backward to avoid dupes
                        while (low < high && nums[high] == nums[high - 1]){
                            high--;
                        }
                    }
                    // if sum was too low ... move low ptr forward
                    if(sum < target){
                        low++;
                    }
                    else {
                        //if sum was too high ... move high ptr backward
                        high--;
                    }
                }
            }
        }
        return result;
    }
}
