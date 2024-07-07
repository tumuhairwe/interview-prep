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

    private static int iterative_dp_bottom_up(String s1, String s2){
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
    public int iterative_dp_top_down(String text1, String text2) {
        // 0. create dynamic programming grid
        int[][] dpGrid = new int[text1.length() + 1][text2.length() + 1];

        // 1. iterate thru 2D array of both strings & populate 2D array
        for(int row = 1; row <= text1.length(); row++){
            for(int col = 1; col <= text2.length(); col++){

                int answer = 0;

                // if corresponding characters of each cell are the same ... add 1
                if(text1.charAt(row - 1) == text2.charAt(col - 1)){
                    answer = 1 + dpGrid[row - 1][col - 1];
                }
                // otherwise, they must be different
                else{
                    int cellAbove = dpGrid[row - 1][col];
                    int cellBelow = dpGrid[row][col - 1];

                    answer = Math.max(cellAbove, cellBelow);
                }

                dpGrid[row][col] = answer;
            }
        }

        // 3. problem's answer is in dp_grid[0][0]. Return it.
        return dpGrid[text1.length()][text2.length()];
    }
}
