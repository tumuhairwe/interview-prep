package com.tumuhairwe.prep.strings;

/**
 * LeetCode 9 (easy)
 * Given an input, determine if it's a valid palindrome
 * Palindrome == string that reads the same from left-to-right and right-to-left
 *
 * e.g. 121 = true
 * e.g. -121= false
 * e.g. 10 = false
 *
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
        System.out.println("rotator == " + isValid("rotator"));
    }

    public static boolean isValid(String input){
        int p1 = 0;
        int p2 = input.length() -1;

        while (p1 < p2){
            Character theChar = input.charAt(p1);
            Character correspondingChar = input.charAt( p2);
            if(theChar != correspondingChar){
                return false;
            }

            p1++;
            p2--;
        }
        return true;    // there was only 1 character left in the middle e.g. caRab == R
    }
}
