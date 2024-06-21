package com.tumuhairwe.prep.strings;

/**
 * LeetCode 5 (medium) (409 -- easy asks for LENGTH). This asks for SUBSTRING
 * Longest palindromic substring
 *
 * ref: https://leetcode.com/problems/longest-palindromic-substring/description/
 */
public class LongestPalindromicSubstring {
    /**
     * Solution summary
     * - iterate thru the entire string
     * - for each char, expand from center (treating char as center)
     * - when length exceeds maxLength (update maxLength and the substring itself)
     * // Time Complexity: O(n^2)
     * // Extra Space Complexity: O(1)
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0 ){
            return "";
        }

        int maxLength = 0;
        String sub = "";
        for (int i = 0; i < s.length(); i++) {

            // for even numbered strings
            int left = i;
            int right = i;
            while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if(right - left + 1 > maxLength){
                    maxLength = right - left +1;
                    sub = s.substring(left, right + 1);
                }
                left--;
                right++;
            }

            // for odd numbered strings
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if(right - left + 1 > maxLength){
                    maxLength = right - left +1;
                    sub = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
        }

        return sub;
    }
}
