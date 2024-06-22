package com.tumuhairwe.prep.strings;

import java.util.Arrays;

/**
 * LeetCode 14 (easy)
 * Write a functoin to find the longext common prefix among an array of string
 *
 * ref: https://leetcode.com/problems/longest-common-prefix/description/
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] arr = new String[]{"flower","flow","flight"};
        String res = longestCommonPrefix_faster(arr);
        System.out.println("Should be fl:" + res);
    }

    /**
     * Solution summary
     * - iterate the [] and find the minLength of all the strings
     * - while i < minLength,
     *      - for each string in array, check if string.charAt(i) == the char at strings[0].charAt(i)
     *      - if the character is not the same, exit and return the subtring from 0 to i
     * - if you exit the for loop, increment i
     * - if you exit the while loop, return the substring of the 1st string from 0 to i
     *
     * TC: O(n * m) == n is # of strings, m is the minLength of the shortest string
     * SC: (1)
     * ref: https://www.youtube.com/watch?v=8C6F8_nM0qs
     */
    public static String longestCommonPrefix(String[] strings){
        int minLength = Integer.MAX_VALUE;

        //0. find min_length of shortest
        for (String s : strings){
            if(s.length() < minLength){
                minLength = s.length();
            }
        }

        // better than for loop bcoz we need to return a substring
        // (i.e. we need access to I even after we don't find any shared prefix i.e. never enter the for-loop)
        int i =0;
        while (i < minLength){
            for(String s : strings){
                if(s.charAt(i) != strings[0].charAt(i)){
                    return s.substring(0, i);
                }
            }
            i++;
        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < strings[0].toCharArray().length; i++) {
//            for(String s : strings){
//                if(i == s.length() || s.charAt(i) != strings[0].charAt(i)){
//                    return sb.toString();
//                }
//            }
//        }
        return strings[0].substring(0, 1);
    }

    //TC: O(n)
    //SC: O(n)
    /**
     * Solution summary
     * - sort the strings and only try to find the shared prefix between string at index 0 and index arr.length - 1
     * - create a StringBuilder
     * - while the index is less than min( length_of_s1, length_of_s2)
     *      - if they have the same character that index -> add that character to stringBuilder & increment index
     *      - if they don't share the character index, exit for loop
     * - return stringBuilder.substring from index-0 to i;
     */
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
                index++;
            }
        }

        return sb.substring(0, index);
    }
}
