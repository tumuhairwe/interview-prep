package com.tumuhairwe.prep.strings;

import java.util.Stack;

/**
 * LeetCode 2390. Removing Stars From a String
 *
 * You are given a string s, which contains stars *.
 *
 * In one operation, you can:
 *
 * Choose a star in s.
 * Remove the closest NON-STAR character to its left, as well as remove the star itself.
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
    public String removeStars(String s){
        //0. Create stack to track all chars in order
        Stack<Character> st = new Stack<>();

        //1. populate stack
        for(char ch : st){
            if(ch == '*'){
                st.pop();
            }
            else {
                st.push(ch);
            }
        }

        //2. re-construct string & return
        StringBuilder sb = new StringBuilder();
        for(char ch : st){
            sb.append(ch);
        }

        return sb.toString();
    }
    //TLE
    public String removeStars_tle(String s) {
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
