package com.tumuhairwe.prep.strings;

/**
 * LeetCode 2390. Removing Stars From a String
 *
 * You are given a string s, which contains stars *.
 *
 * In one operation, you can:
 *
 * Choose a star in s.
 * Remove the closest non-star character to its left, as well as remove the star itself.
 * Return the string after all stars have been removed.
 *
 * Note:
 *
 * The input will be generated such that the operation is always possible.
 * It can be shown that the resulting string will always be unique.
 *
 * ref: https://leetcode.com/problems/removing-stars-from-a-string/description/
 */
public class RemoveStartFromString {
    public String removeStars(String s) {
        //char[] result = new char[l.length()];
        StringBuilder result = new StringBuilder();

        for(int i=0; i < s.toCharArray().length; i++){
            char theChar = s.toCharArray()[i];

            if(theChar == '*'){
                result.deleteCharAt(result.length() - 1);
            }
            else{
                result.append(theChar);
            }
        }

        return result.toString();
    }
}
