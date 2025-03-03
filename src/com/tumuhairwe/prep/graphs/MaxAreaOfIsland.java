package com.tumuhairwe.prep.graphs;

/**
 * LeetCode 695 (medium)
 * Given a matrix of m x n.
 * An island if a group of 1's (LAND) connected 4-directionally horizontally or vertically. You may assume that all 4
 * 4 edges are surrounded by water
 *
 * The area of an island is the number of cells with a value 1 in the island
 *
 * Return the maximum area of an island in the grid. If there's no island, return 0;
 *
 * ref: https://leetcode.com/problems/max-area-of-island/description/
 */
public class MaxAreaOfIsland {
    static int LAND = 1;
    static int WATER = 0;
    public int maxAreaOfIsland(int[][] grid) {
        //0. base casee
        if(grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == LAND){
                    int area = getArea(grid, i, j);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }

        return maxArea;
    }

    private int getArea(int[][] grid, int row, int col) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length){
            return 0;
        }

        int area = 1;
        grid[row][col] = WATER;
        area += getArea(grid, row + 1, col);
        area += getArea(grid, row - 1, col);
        area += getArea(grid, row, col + 1);
        area += getArea(grid, row, col - 1);
        return area;
    }
}
