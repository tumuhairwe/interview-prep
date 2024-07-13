package com.tumuhairwe.prep.strings;

/**
 * LeetCode 28 (easy)
 *
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * ref: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string
 */
public class FindFirstOccurrenceOfString {
    public static void main(String[] args) {
        System.out.println(strStr("mississippi", "issip"));
    }

    /**
     * Solution summary
     * - account for base cases (haystack is too short or needle is blank)
     * - iterate thru the array (set endIndex every time)
     * - if haystack contains the substring (startINdex = i, endIndex = i + needle.length()), return true
     * - if loop ends without matching above, return fasle
     */
    public static  int strStr(String haystack, String needle) {
        // 0. base case
        if(needle.length() > haystack.length()){
            return -1;
        }
        if(needle.equals(haystack)){
            return 0;
        }

        // this solution does not use a sliding window and relies on the substring
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
//            if(haystack.charAt(i) != needle.charAt(0)){
//                continue;
//            }

            int endIndex = i + needle.length(); // define end index
            if(haystack.substring(i, endIndex).equals(needle)){
                return i;
            }
        }

        return -1;
    }

    public static  int strStr_builtIn(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
