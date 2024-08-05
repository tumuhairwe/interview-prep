package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2053 (easy)
 * Kth Distinct String in an array
 *
 * A distinct string is a string that is present only once in an array.
 * Given an array of strings arr, and an integer k, return the kth distinct string present in arr. If there are fewer than k distinct strings, return an empty string "".
 * Note that the strings are considered in the order in which they appear in the array.
 *
 * ref: https://leetcode.com/problems/kth-distinct-string-in-an-array/description/
 * ref: https://www.youtube.com/watch?v=1KOnvGPv9Mo
 */
public class KthDistinctStringInArray {
    /**
     * Solution summary
     * - Create frequency map of strings
     * - iterate thru array and test if str.frequency = 1, decrement k
     * - when k == 0, return string
     * - if you exist loop (i.e. too few strings) ... return ""
     */
    public String kthDistinct(String[] arr, int k) {
        //0. create freqMap
        Map<String, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }

        //1. find string with freq = 1 -> decrement k. When k=0, return string
        for (String str : arr){
            if(freqMap.get(str) == 1){
                k--;
            }

            if(k == 0){
                return str;
            }
        }

        return "";
    }
}
