package com.tumuhairwe.prep.twopointers;

/**
 * LeetCode 2825 (medium)
 *
 * Make String a Subsequence Using Cyclic Increments
 *
 * You are given two 0-indexed strings str1 and str2.
 *
 * In an operation, you select a set of indices in str1, and for each index i in the set, increment str1[i] to the next
 * character cyclically. That is 'a' becomes 'b', 'b' becomes 'c', and so on, and 'z' becomes 'a'.
 *
 * Return true if it is possible to make str2 a subsequence of str1 by performing the operation at most once,
 * and false otherwise.
 *
 * Note: A subsequence of a string is a new string that is formed from the original string by deleting some
 * (possibly none) of the characters without disturbing the relative positions of the remaining characters.
 *
 * ref: https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments
 * ref: https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments/solutions/4064945/simple-and-easy-java-solution-100-beats/
 * ref: https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments/solutions/3933697/java-two-pointers-o-n-solution-fully-explained-simple-and-easy-to-understand/
 */
public class MakeStringASubsequenceUsingCyclicIncrements {
    /**
     * Solution summary
     * - if str2 is longer, return false
     * - init 2 pointers to track chars in each str
     * - grab the chars at each pointer in each respective string
     * - if the chars match or if char1 is next to char2 (front or back), increment both pointers
     * - else increment just the shorter pointer
     * - return true if p2 has reached the end of str2
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean canMakeSubsequence(String str1, String str2){
        //0. base case: its not possible for a longer string to be a subsequenc of a shorter string
        if(str2.length() > str1.length()){
            return false;
        }

        //1 create 2 pointers
        int p1 = 0;
        int p2 = 0;

        while (p1 <str1.length() && p2 < str2.length()){
            char ch1 = str1.charAt(p1);
            char ch2 = str2.charAt(p2);

            //if its possible to increment current letter forward or backward by one character ... increment both pointers
            if(ch1 == ch2 || ch1 + 1 == ch2 || ch1 - 25 == ch2){
                p1++;
                p2++;
            }
            else {
                p1++;
            }
        }
        return p2 == str2.length();
    }
}
