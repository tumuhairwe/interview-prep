package com.tumuhairwe.prep.dynamicprogramming;

/**
 * LeetCode 62 Unique Paths
 *
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 * ref: https://leetcode.com/problems/unique-paths/description/
 */
public class UniquePaths {
    // This has a graph-based solution as well

    /**
     * Solution summary
     * - Create a 2D array of m x n
     * - populate entire 1st column with 1s
     * - populate entire 1st row with 1s
     * - populate entire 2D grid with value_above + value_behind
     * - in the end, the target cell (m-1, n-1) should have dynamically calculated values of all preceeding cells
     */
    public int uniquePaths(int m, int n) {
        // 0. create 2D dp array
        int[][] dp = new int[m][n];

        // 1. fill the last column with 1s
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        //2. fill out the entire last row with 1st
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // 3. fill bottom up until you get to [m-1][n-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        //return value in target cell (bottom most & right most)
        return dp[m-10][n-1];
    }
}
