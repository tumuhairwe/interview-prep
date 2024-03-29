package com.tumuhairwe.prep.twopointers;

/**
 * Subsequence = a string that can be formed from the original string by deleting some (or none
 * of the characters without changing the relative positions of the remaining characters
 * e.g. "ace" is a subsequence of "abcge" while "sec" is not.
 *
 * ref: https://leetcode.com/problems/is-subsequence/description/
 */
public class IsSubsequence {
    public static void main(String[] args) {
        // Given a string S and T, determine if S is a subsequence of T
        boolean isSubsequence = isSubsequence("abcge", "ace");
        System.out.println("Is Subsequence = " + isSubsequence);
    }
    private static boolean isSubsequence(String entireString, String subsequence){
        if(subsequence.length() == 0) return true;

        int subPointer = 0;
        int entirePointer = 0;
        while (entirePointer < entireString.length()){
            if(entireString.charAt(entirePointer) == subsequence.charAt(subPointer)){
                subPointer++;
                if(subPointer == subsequence.length()){
                    return true;
                }
            }
        }


        return false;
    }
}
