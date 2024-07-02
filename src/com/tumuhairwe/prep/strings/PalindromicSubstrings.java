package com.tumuhairwe.prep.strings;

/**
 * LeetCode 516 (medium)
 *
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence
 * by deleting some or no elements without changing the order of the remaining elements.
 *
 * ref: https://leetcode.com/problems/longest-palindromic-subsequence/description/
 */
public class PalindromicSubstrings {
    public static void main(String[] args) {
        System.out.println(countSubstrings("abc"));
        System.out.println(countSubstrings("aaa"));
    }
    public static int countSubstrings(String s) {
        //0. base case
        if(s == null || s.trim().length() == 0){
            return 0;
        }

        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            // for odd lengths;
            int length = getMaxLength(i, i, s);
            maxLength = Math.max(length, maxLength);

            // even length
            length = getMaxLength(i, i + 1, s);
            maxLength = Math.max(length, maxLength);
        }

        return maxLength;
    }

    static int getMaxLength(int left, int right, String s){
        int maxLength = 0;
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            if(right - left + 1 > s.length()){
                maxLength = right - left +1;
            }
            left--;
            right++;
        }
        return maxLength;
    }

    public static boolean isPalindrome(String s){
        if(s.length() ==1){
            return true;
        }
        int left = 0;
        int mid = s.length()/2;
        for(int right =s.length()-1; right >= mid; right--){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
        }

        return true;
    }
}
