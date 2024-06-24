package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 229. Majority Element II (medium)
 *
 * Given an int[] of size n, find all elements that appear more than n/3 times
 *
 * ref: https://leetcode.com/problems/majority-element-ii/description/
 */
public class MajorityElementII {

    public static void main(String[] args) {
        int[] arr = {3,2,3};
        System.out.println(majorityElement(arr));
    }
    /**
     * Solution summary
     * - create frequency map of nums
     * - iterate thru the map, find any entry with frequency (map.value() > nums.length / 3
     * - add that entry's key to the List
     * - Return list
     */
    public static List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int existingCount = freqMap.getOrDefault(nums[i], 0);
            freqMap.put(nums[i], existingCount + 1);
        }

        List<Integer> results = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()){
            if(entry.getValue() > nums.length/3){
                results.add(entry.getKey());
            }
        }

        return results;
    }
}
