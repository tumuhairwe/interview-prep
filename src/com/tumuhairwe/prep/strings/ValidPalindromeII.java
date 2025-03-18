package com.tumuhairwe.prep.strings;

/**
 * LeetCode 680 (easy) -- Valid Palindrome II
 *
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 * ref: https://www.youtube.com/watch?v=JrxRYBwG6EI
 * ref: https://leetcode.com/problems/valid-palindrome-ii/description/
 */
public class ValidPalindromeII {

    /**
     * Solution Summary
     * - iterate string with 2 pointers
     * - if its not a palindrome so far (i.e. character_at_end != character_at_start -> test by changing
     * the boundary to start+1 and end-1
     *
     * TC: O(n)
     * SC: O(1)
     */
    public boolean isValidPalindrome(String s){
        int left = 0;
        int right = s.length() - 1;

        while (left < right){
            boolean isPalindromic_so_far = s.charAt(left) == s.charAt(right);
            if(!isPalindromic_so_far){
                // check if the string 1 character on each side of pointer is valid
                boolean legit = isPalindrome(s, left + 1, right) || isPalindrome(s, left, right -1);
                return legit;
            }
        }

        return true;
    }

    public boolean isPalindrome(String s, int left, int right){
        while (left < right && right < s.length()){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
