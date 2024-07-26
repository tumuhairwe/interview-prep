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

    /**
     * Solution summary
     * - initialize 2 pointers to 0 (p1 -> subsequence ptr, p2 -> entireString ptr)
     * - while each pointer is less than its String's length ...
     *      - if the characters at each pointer match .. increment the subsequencePointer
     *      - increment the entireString pointer regardless
     * - At the end, subsequence pointer should be equal to subsequence length if isSubsequece, false otherwise
     *
     * SC: O(1)
     * TC: O( T ) where T is the
     */
    private static boolean isSubsequence(String entireString, String subsequence){
       int p1= 0;
       int p2= 0;

       while (p1 < subsequence.length() && p2 < entireString.length()){
           if(subsequence.charAt(p1) == entireString.charAt(p2)){
               p1++;
           }
           p2++;
       }

       return p1 == subsequence.length();
    }
}
