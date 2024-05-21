package com.tumuhairwe.prep.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Leet 217
 *
 * Given an integer array nums, return true if any value appears
 * at least twice in the array, and return false if every element is distinct.
 *
 * ref: https://leetcode.com/problems/contains-duplicate/
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0217-contains-duplicate.java
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        System.out.println("Should be false " + containsDuplicate_map(arr));

        arr = new int[]{1,2,3,1};
        System.out.println("Should be true " + containsDuplicate_set(arr));
    }
    public static boolean containsDuplicate_map(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int existingCount = freqMap.getOrDefault(nums[i], 0 );

            freqMap.put(nums[i], existingCount + 1);
        }

        return freqMap.entrySet()
                .stream()
                .anyMatch(e -> e.getValue() > 1);
    }

    public static boolean containsDuplicate_set(int[] nums) {
        Set<Integer> uniques = new HashSet<>();
        for(int i=0; i<nums.length; i++){
            if(uniques.contains(nums[i])){
                return true;
            }

            uniques.add(nums[i]);
        }

        return false;
    }
}
