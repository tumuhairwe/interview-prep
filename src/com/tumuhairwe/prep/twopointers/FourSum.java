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
 * ref: https://leetcode.com/problems/4sum/solutions/5464256/brute-force-better-solutions-and-optimal-approach-for-interview-preparation/
 * ref: https://www.youtube.com/watch?v=EYeR-_1NRlQ
 */
public class FourSum {

//    public List<List<Integer>> fourSum_impl2(int[] nums, int target){
//        //0. create freqMap
//        Map<Integer, Integer> freqMap = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
//        }
//
//        List<List<Integer>> result = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i+1; j < nums.length; j++) {
//                for (int k = j+1; k < nums.length; k++) {
//                    int desired = target - nums[i] - nums[j] - nums[k];
//                    if(freqMap.containsKey(desired) && isDistinct(nums[i], nums[j], nums[k], desired)){
//                        result.add(List.of(desired, nums[i], nums[j], nums[k]));
//                    }
//                }
//            }
//        }
//
//        return result;
//    }
//    static boolean isDistinct(int a, int b, int c, int d){
//        return (a != b) && (a != c) && (a != d)
//                && (b != c) && (b != d)
//                && (c != d);
//    }

    public static void main(String[] args) {
        int[]arr = {1,0,-1,0,-2,2};
        System.out.println(fourSum(arr, 0));

        int[] arr2 = {2,2,2,2,2};
        System.out.println(fourSum(arr2, 8));
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
    public static List<List<Integer>> fourSum(int[] nums, int target){
        if(nums == null || nums.length < 4){
            return new ArrayList<>();
        }

        //1. sort
        Arrays.sort(nums);

        //0. declare vars
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {

                // int low = j+1;
                // int high = nums.length - 1;
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    //int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right];
                    if(sum == target){
                        //result.add(List.of(nums[i], nums[j], nums[low], nums[high]));
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // move pointers
                        left++;
                        right--;
                    }
                    else if(sum < target){
                        left++;
                    }
                    else{
                        right--;
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }
}
