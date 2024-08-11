package com.tumuhairwe.prep.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 454 (medium)
 *
 * Given 4 integers, nums1, nums2, nums3, num4 all of length n, return the number of tuples such that the sum of all
 * 4 is 0
 *
 * ref: https://leetcode.com/problems/4sum-ii/description/
 */
public class FourSumII {

    /**
     * Solution summary
     * - create frequency map of the sums of nums1[i] + nums2[j]
     * - check frequency map for sums of -(nums3[i] + nums4[j])
     *
     * TC: O(n^2) -- because of 2 quadratic loops (though parallel)
     * SC: O(n ^ 2) -- there could be n-squared keys in the map
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 0. create frequency map of the sums of nums1[i] + nums2[j]
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int target = nums1[i] + nums2[j];
                freqMap.put(target, freqMap.getOrDefault(target, 0) + 1);
            }
        }

        int count = 0;
        // 1. check frequency map where the sums of -(nums3[i] + nums4[j])
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int target = (nums3[i] + nums4[j]) * -1;

                //3. add that frequency to count
                count += freqMap.getOrDefault(target, 0);
            }
        }

        //4 return count
        return count;
    }
}
