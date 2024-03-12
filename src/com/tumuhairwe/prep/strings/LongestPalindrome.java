package com.tumuhairwe.prep.strings;

/**
 * Given a string s (which consists of lowercase or uppercase letters)
 * return the LENGTH of the longest palindrome that can be built with those letters
 *
 * Assume: Letters care case-sensitive e.g. "Aa" is NOT a palindrome but "aa" is
 * Assume: max_length <= 1010
 * ref: ref: https://leetcode.com/problems/longest-palindrome/description/
 */
public class LongestPalindrome {

    public static void main(String[] args) {
    }

    public static int longestPalindrome(String s){
        int[] char_counts  = new int[128];

        for (char c : s.toCharArray()){
            char_counts[c]++;   // counts the number of occurrences in each character
        }

        int result = 0;
        for (int charCount : char_counts){
            result += charCount / 2 * 2;
            // e.g. if charCount = 8 .. 8/2=4 ... 4 * 2 .. 8 (i.e. 8 chars we can put in palindrome)
            // e.g. if charCount = 9 .. 9/2=4 ... 4 * 2 .. 8 (i.e. 8 chars we can put in palindrome)
            boolean resultIEvenNumber = (result & 2) == 0;
            boolean hasAmiddleChar = charCount % 2 == 1;
            if(resultIEvenNumber && hasAmiddleChar){
                result += 1;
            }
        }

        return result;
    }
}
