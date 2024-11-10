package com.tumuhairwe.prep.strings;

/**
 * LeetCode 2414. Length of the Longest Alphabetical Continuous Substring
 *
 * An alphabetical continuous string is a string consisting of consecutive letters in the alphabet. In other words, it is any substring of the string "abcdefghijklmnopqrstuvwxyz".
 *
 * For example, "abc" is an alphabetical continuous string, while "acb" and "za" are not.
 * Given a string s consisting of lowercase letters only, return the length of the longest alphabetical continuous substring.
 *
 * ref: https://leetcode.com/problems/length-of-the-longest-alphabetical-continuous-substring/description/
 */
public class LongestContinuousSubstring {
    /**
     * Solution summary
     * 1. traverse the whole string
     * 2. if diff between currentChar and prevChar == 1.  increment count
     * 3. if diff != 1, reset counter
     * 4. store the max count of each step
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int longestContinuousSubstring(String s) {
        int maxLength = 1;
        int currentLength= 1;
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i) - s.charAt(i-1) == 1){
                currentLength++;
                maxLength = Math.max(currentLength, maxLength);
            }
            else {
                currentLength = 1;
            }
        }
        return maxLength;
    }
}
