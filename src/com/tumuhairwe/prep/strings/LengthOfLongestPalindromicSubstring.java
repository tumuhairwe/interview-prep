package com.tumuhairwe.prep.strings;

/**
 * Method 1:
 * - For each word (space is separator)
 * - Find the middle_index ...
 * - Use that to compare if every character (going outwards to the left and the right) is equal
 * e.g. abccba => abc_MID_cba => string.charAt( MID + counter) == string.charAt( MID - counter)
 *
 * e.g. RACECAR == middle_character needs to be accounted for if length == odd number
 *
 * LeetCode 409 (Easy)
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest
 * palindrome
 *  that can be built with those letters.
 *
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome.
 *
 * Summary: Expand around the center
 */
public class LengthOfLongestPalindromicSubstring {

    public static void main(String[] args) {
        return;
    }

    /**
     * Solution summary
     * - iterate thru the string and for each character, expand from center
     * - get the length and compare with maxLength
     * - if longer, update maxLength
     */
    public static String longestPalindromeSusbstring(String input){
        if(input == null || input.length() == 0){
            return "";
        }

        int start =0;
        int end = 0;

        for (int i = 0; i < input.length(); i++) {
            int length1 = expandFromMiddle(input, i, i);    // if length == odd
            int length2 = expandFromMiddle(input, i, i+1);  // if length == even

            int len = Math.max(length1, length2);
            if(len > end -start){
                start = i - ((len-1)/2);
            }

        }

        return input.substring(start, end - 1);   // Time Complexity = n^2
    }
    public static int expandFromMiddle(String s, int left, int right){
        if(s == null || left > right){
            return 0;
        }

        while (left >=0 &&
                right < s.length() &&
                s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }

        return right - left + 1;
    }
}
