package com.tumuhairwe.prep.strings;

/**
 * Given a string s (which consists of lowercase or uppercase letters)
 * return the LENGTH of the longest palindrome that can be built with those letters
 *
 * LeetCode 409 (Easy)
 * Assume: Letters care case-sensitive e.g. "Aa" is NOT a palindrome but "aa" is
 * Assume: max_length <= 1010
 *
 * ref: https://www.youtube.com/watch?v=a_3XDW9P47E
 * ref: ref: https://leetcode.com/problems/longest-palindrome/description/
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String s = "abccccdd";
        int result = longestPalindrome(s);
        System.out.println("The length of the longest palindromic substring is " + result);
    }

    public static int longestPalindrome(String s){
        // char_counts = array to keep track of the number of occurrences of a character
        int[] char_counts  = new int[128];

        for (char c : s.toCharArray()){
            // c = converted to ASCII index & used as the index of hte array
            char_counts[c]++;   // counts the number of occurrences in each character
        }

        int result = 0;
        for (int charCount : char_counts){
            result += charCount / 2 * 2;
            // e.g. if charCount = 8 .. 8/2=4 ... 4 * 2 .. 8 (i.e. 8 chars we can put in palindrome)
            // e.g. if charCount = 9 .. 9/2=4 ... 4 * 2 .. 8 (i.e. 8 chars we can put in palindrome)
            boolean resultIsEvenNumber = (result & 2) == 0;
            boolean hasMiddleChar = charCount % 2 == 1;
            if(resultIsEvenNumber && hasMiddleChar){
                result += 1;
            }
        }

        return result;
    }
}
