package com.tumuhairwe.prep.tree;

/**
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 * ref: https://leetcode.com/problems/valid-palindrome-ii/description/
 */
public class ValidatePalindromeII {

    public static void main(String[] args) {
        System.out.println("Should be true ->" + validPalindrome("aba"));
        System.out.println("Should be true ->" + validPalindrome("abca"));
        System.out.println("Should be false ->" + validPalindrome("abc"));
    }
    public static boolean validPalindrome(String s){
        int left = 0;
        int right = s.length() - 1;

        while (left < right){
            if(s.charAt(left) != s.charAt(right)){
                boolean legit = checkPal(s, left + 1, right) || checkPal(s, left, right -1);
                return legit;
            }

            left++;
            right--;
        }

        return true;
    }

    public static boolean  checkPal(String s, int left, int right){
        while(left < right && right < s.length()){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
