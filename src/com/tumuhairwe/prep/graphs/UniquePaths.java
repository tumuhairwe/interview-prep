package com.tumuhairwe.prep.graphs;

/**
 * LeetCode 62 Unique Paths (medium)
 * There's a robot on an (m x n) grid, The robot is initially located at the top-left corner
 * (i.e. grid[0][0]). The robot tries to move to the bottom-right cornder (i.e. grid[m-1][n-1]
 * The robot can only move either down or right at any point in time.
 * Given the 2 integers m & n, return the number of possible unique paths that the robot
 * can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 10^9
 */
public class UniquePaths {
    int[][] graph;
    Integer[][] memo;
    public int uniquePaths(int m, int n) {
        //0. declare vars
        graph = new int[m][n];
        memo = new Integer[m][n];

        int startRow = 0;
        int startCol = 0;

        return getCost(graph, startRow, startCol);
    }

    int getCost(int[][] graph, Integer startRow, int startCol){
        boolean isTargetCell = startRow == graph.length - 1 && startCol == graph[0].length - 1;
        if(isTargetCell){
            return 1;
        }

        if(memo[startRow][startCol] != null){
            return memo[startRow][startCol];
        }

        memo[startRow][startCol] = getCost(graph, startRow + 1, startCol) + getCost(graph, startRow, startCol + 1);
        return memo[startRow][startCol];
    }
}
