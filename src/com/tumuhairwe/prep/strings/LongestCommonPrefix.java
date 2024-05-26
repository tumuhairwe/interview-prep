package com.tumuhairwe.prep.strings;

import java.util.Arrays;

/**
 * LeetCode 14 (easy)
 * Write a functoin to find the longext common prefix among an array of string
 * ref: https://leetcode.com/problems/longest-common-prefix/description/
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] arr = new String[]{"flower","flow","flight"};
        String res = longestCommonPrefix_faster(arr);
        System.out.println("Should be fl:" + res);
    }
    // TC: 0(n^2)
    public static String longestCommonPrefix(String[] strings){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings[0].toCharArray().length; i++) {
            for(String s : strings){
                if(i == s.length() || s.charAt(i) != strings[0].charAt(i)){
                    return sb.toString();
                }
            }
        }
        return sb.toString();
    }

    //TC: O(n)
    public static String longestCommonPrefix_faster(String[] strs) {
        //0. sort
        Arrays.sort(strs);

        // 1. create vars
        String s1 = strs[0];
        String s2 = strs[strs.length -1];
        int index = 0;

        // 2. create sb
        StringBuilder sb = new StringBuilder();

        while (index < Math.min(s1.length(), s2.length())) {
            if(s1.charAt(index) != s2.charAt(index)){
                break;
            }
            else{
                sb.append(s1.charAt(index));
            }
            index++;
        }

        return sb.substring(0, index);
    }
}
