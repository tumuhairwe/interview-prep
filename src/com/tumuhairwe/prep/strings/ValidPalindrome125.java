package com.tumuhairwe.prep.strings;

/**
 * leetCode 125 Easy
 *
 * Description: A phrase is a palindrome if, after converting all uppercase letters into
 * lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 *
 * Summary:
 *  - Convert string to all lowercase + alphanumberic
 *  - Remove non alphanumeric chars
 *  - use 2 pointers and traverse from both directions
 *  - Compare chars at index of each pointer (left_pointer and right_pointer) chars should be same
 *  .. otherwise false;
 *
 * ref: https://leetcode.com/problems/valid-palindrome/description/
 */
public class ValidPalindrome125 {

    public static void main(String[] args) {
        String string = "ROTATOR";
        System.out.println(string + " is palindrome? " + isPalindrome(string));
    }

    static  boolean isPalindrome(String s){

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()){
            if(Character.isDigit(c) || Character.isLetter(c)){
                sb.append(c);
            }
        }

        String fixed_string = sb.toString().toLowerCase();

        int a_pointer = 0;
        int b_pointer = fixed_string.length() -1;

        while (a_pointer <= b_pointer){
            if (fixed_string.charAt(a_pointer) != fixed_string.charAt(b_pointer)){
                return  false;
            }

            a_pointer++;
            b_pointer--;
        }

        return true;
    }
}
