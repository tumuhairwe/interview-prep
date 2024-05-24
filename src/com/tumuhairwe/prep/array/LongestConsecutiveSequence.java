package com.tumuhairwe.prep.array;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 128 (medium)
 * Given an unsorted array of integers, return the length of the longest consecutive sequence of elements
 *
 * Solution Summary
 * - Put all elements into a set
 * - Check if element is beginnning of se (i.e. if num - 1 does not exist in set)
 * - If so, set Length = 0;
 * - while set.contains(num + length) ... increment length & update longes to (max (longest, length)
 * - return longest
 * ref: https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] arr = new int[]{100,4,200,1,3,2};
        int longest = longestConsecutive(arr);
        System.out.println("Longest sequence is of length " + longest);
    }
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int longest = 0;

        // base case
        if(nums.length == 1){
            return 1;
        }

        // 0. put array into set
        for(int num : nums){
            set.add(num);
        }

        // 1. find if num is beginning of sequence
        for(int num : nums){
            if(!set.contains(num - 1)){
                int length = 0;

                while (set.contains(num + length)) {
                    length++;

                    // 2. update longest
                    longest = Math.max(longest, length);
                }
            }
        }

        return longest;
    }
}
