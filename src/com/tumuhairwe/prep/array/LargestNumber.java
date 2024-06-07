package com.tumuhairwe.prep.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 *
 * Since the result may be very large, so you need to return a string instead of an integer.
 *
 * ref: https://www.youtube.com/watch?v=WDx6Y4i4xJ8
 * ref: https://leetcode.com/problems/largest-number/description/
 */
public class LargestNumber {

    /**
     * Solution Summary
     * - Convert int[] to string[]
     * - sort string[] (such that strings with bigger prefixes have more weight e.g. 9 > 13)
     * - convert sorted string[] to String
     * - return string
     */
    public String largestNumber(int[] nums) {
        //0 convert int[] to string[]
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        //1. sort string[]
        Comparator<String> comp = (String s1, String s2) -> {
            String n1 = s1 + s2;
            String n2 = s2 + s1;
            return n2.compareTo(n1);
        };
        Arrays.sort(arr, comp);

        //1.5 account for edge case of just zeors
        if(Integer.parseInt(arr[0]) == 0){
            return "0";
        }

        //2. convert in[] back to string and return
        StringBuilder sb = new StringBuilder();
        for (String s : arr){
            sb.append(s);
        }

        return sb.toString();
    }
}
