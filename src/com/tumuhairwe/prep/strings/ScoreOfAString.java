package com.tumuhairwe.prep.strings;

/**
 * LeetCode 3110 (easy)
 * You are given a string s. The score of a string is the sum, of the absolute diff
 * between the ASCII values of the adjacent characters
 *
 * ref: https://leetcode.com/problems/score-of-a-string/description/
 */
public class ScoreOfAString {
    public static void main(String[] args) {
        System.out.println("Should be 13 " + scoreOfSstring("hello"));
        System.out.println("Should be 50 " + scoreOfSstring("zaz"));
    }
    static int scoreOfSstring(String s){
        int score = 0;
        for(int i=0; i<s.length() - 1; i++){
            score += Math.abs(s.charAt(i) - s.charAt(i + 1));
        }
        return score;
    }
}
