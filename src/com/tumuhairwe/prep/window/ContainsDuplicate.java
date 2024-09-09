package com.tumuhairwe.prep.window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 0219 (Easy)
 * Given an integer array nums & an integer K,
 * return true if
 *   - there are 2 DISTINCT INDICES i and j, in the array
 * such that  (i.e. indices must be different)
 *  - nums[i] == nums[j] and
 *  - abs(i - j) <= K --- i.e. a window of size K (at most)
 *
 *  Solution Summary
 *  - Create Set to track uniqueness of numbers in window of size K
 *  - Iterate over the array and
 *      - check if size has exceeded K (if so,
 *          - remove last added number (nums[leftPointer]
 *          - increment leftPointer
 *      - check if set contains nums[rightPointer], if so, return true (i.e. we're still in the window and just encountered a duplicate)
 *      - else add nums[rightPointer] to Set
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1};
        int k = 3;

        nums = new int[]{1,0,1,1};
        k = 1;

        nums = new int[]{1,2,3,1,2,3};
        k = 1;
    }

    static boolean containsNearbyDuplicate(int[] nums, int k){
        Set<Integer> windowOfSizeK = new HashSet<>();
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if(windowOfSizeK.size() > k){
                windowOfSizeK.remove(nums[left]);
                left++;
            }

            if(windowOfSizeK.contains(nums[right])){
                return true;
            }

            windowOfSizeK.add(nums[right]);
        }

        return false;
    }

    /**
     * Solution summary
     * - Loop thru nums, if nums[i] is not in map, add
     * - if nums[i] is in map, get index of previous occurrence
     * - get Math.abs(currentIndex - previousIndex) ... if <= k, return true
     * - else add to Map
     */
    static boolean containsDuplicate(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){   // is dupe
                int pastIdx = map.get(nums[i]);
                if(Math.abs(i - pastIdx) <= k){
                    return true;
                }
                else {
                    map.put(nums[i], i);
                }
            }
            else {
                map.put(nums[i], i);
            }
        }

        return false;
    }
}
