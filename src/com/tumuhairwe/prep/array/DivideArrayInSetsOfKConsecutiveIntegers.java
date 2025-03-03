package com.tumuhairwe.prep.array;

import java.util.TreeMap;

/**
 * LeetCode 1296 (medium)
 * Given an array of integers nums and positve integer k, check whether its is possible to divide this
 * array into sets of k consecutive numbers
 *
 * Return TRUE if it is possible, FALSE otherwise
 * ref: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 */
public class DivideArrayInSetsOfKConsecutiveIntegers {

    //TC: O(n + k) where n == nums.length
    //SC: O(n)
    public boolean isPossibleDivide(int[] nums, int k) {
        //0. create freqMap
        TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        for (int i=0; i< nums.length; i++){
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        //1. loop thru, decrement frequency of each ... return false if there's a break
        while(!freqMap.isEmpty()){

            // for each current num,
            int first = freqMap.firstKey();
            //int first = Collections.min(freqMap.keySet());    // TLE
            for (int num = first; num < first + k; num++) {
                if(!freqMap.containsKey(num)){  // return false if num between here and k doesn't exist
                    return false;
                }

                // if frequency of num == 1, remove, else decrement
                int freq = freqMap.get(num);
                if(freq == 1){
                    freqMap.remove(num);
                }
                else {
                    freqMap.put(num, freq - 1);
                }
            }
        }

        //if you exit freqMap iteration, ... return true
        return true;
    }
}
