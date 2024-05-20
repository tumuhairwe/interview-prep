package com.tumuhairwe.prep.strings;

/**
 * LeetCode 14 (easy)
 * Write a functoin to find the longext common prefix among an array of string
 * ref: https://leetcode.com/problems/longest-common-prefix/description/
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] arr = new String[]{"flower","flow","flight"};
        String res = longestCommonPrefix(arr);
        System.out.println("Should be fl:" + res);
    }
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
}
