package com.tumuhairwe.prep.stack;

/**
 * Given an input, determine if its a valida palindrome
 * Palindrome == string that reads the same from left-to-right and right-to-left
 *
 * e.g. 121 = true
 * e.g. -121= false
 * e.g. 10 = false
 * ref: https://leetcode.com/problems/palindrome-number/description/
 *
 * Summary: Split in the middle ... and compare (as you iterate ...
 *  if a character has a corresponding/opposite character
 *
 */
public class ValidPalindrome {

    public static void main(String[] args) {
        System.out.println("121 == "+ isValid("121"));
        System.out.println("-121 == " + isValid("-121"));
        System.out.println("aabb == " + isValid("aabb"));
        System.out.println("barab == " + isValid("barab"));
        System.out.println("RACECAR == " + isValid("RACECAR"));
    }

    public static boolean isValid(String input){
        int index = 0;
        int mid = input.length() /2;

        while (index < mid){
            int length = input.length();
            Character theChar = input.charAt(index);
            Character correspondingChar = input.charAt( length-index-1);
            if(theChar != correspondingChar){
                return false;
            }

            index++;
        }
        return true;    // there was only 1 character left in the middle e.g. caRab == R
    }
}
