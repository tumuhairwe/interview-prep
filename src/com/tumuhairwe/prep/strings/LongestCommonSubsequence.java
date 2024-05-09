package com.tumuhairwe.prep.strings;

/**
 * LeetCode 1143 (medium)
 * Given 2 strings st1 and st2, find the length of the longest common subsequence .
 * If there is none return 0;
 *
 * A subsequence of astring iis a new string generated from the original string with the same characters
 * (can be none) deleted, without changing the relative order of the remaining characters
 *
 * Example: For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * ref: https://leetcode.com/problems/longest-common-subsequence/description/
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {

    }
    public int longestCommonSubsequence(String s1, String s2){
        int[][] dp = new int[s1.length()][s2.length()];
        return dynamicDP_recursive(s1, s2, 0, 0, dp);
    }

    private int dynamicDP_recursive(String s1, String s2, int i, int j, int[][] dp) {
        if(i >= s1.length() || j >= s2.length()){
            return 0;
        }
        else if (dp[i][j] != 0){
            return dp[i][j];
        }
        else if(s1.charAt(i) == s2.charAt(j)){
            int resultOfRecursiveCall = 1 + dynamicDP_recursive(s1, s2, i + 1, j + 1, dp);
            return resultOfRecursiveCall;
        }
        else {
            int lowerRow =dynamicDP_recursive(s2, s2, i + 1, j, dp);
            int rightNeighbor =dynamicDP_recursive(s2, s2, i, j + 1, dp);
            dp[i][j] = Math.max(lowerRow, rightNeighbor);
            return dp[i][j];
        }
    }

    private static int iterative_dp(String s1, String s2){
        int[][] dp = new int[s1.length()][s2.length()];

        for (int i = s1.length(); i > 0; i--) {
            for (int j = s2.length(); j > 0; j--) {
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i][j] = dp[i + 1][j + 1];
                }
                else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp[0][0];
    }
}
