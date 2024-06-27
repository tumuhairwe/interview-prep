package com.tumuhairwe.prep.strings;

/**
 * LeetCode 392 (easy)
 *
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 *
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * ref: https://www.youtube.com/watch?v=M_OB20n4hfo
 * ref: https://leetcode.com/problems/is-subsequence/description/
 */
public class IsSubsequence {


    public static void main(String[] args) {
        System.out.println("Should be true -> " + isSubsequence("aaaaaa", "bbaaaa"));
        System.out.println("Should be true -> " + isSubsequence("", "ahbgdc"));
        System.out.println("Should be false -> " + isSubsequence("axc", "ahbgdc"));
    }
    public static boolean isSubsequence(String subsequence, String entireString){
        //0. base case
        if(subsequence == null || subsequence.trim().length() == 0){
            return  true;
        }

        //1. case when subsequence is longer
        if(subsequence.length() > entireString.length()){
            return false;
        }

        //2. iterate thru the array and check for matching characters until you get tot the end
        int j =0;
        for (int i = 0; i < entireString.length(); i++) {
            if(j < subsequence.length() && subsequence.charAt(j) == entireString.charAt(i)){
                if(j == subsequence.length() - 1){
                    return true;
                }
                j++;
            }
        }

        return false;
    }
}
