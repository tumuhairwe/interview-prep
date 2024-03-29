package com.tumuhairwe.prep.array;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Given a 2D binary grid, which represents a map of '1's (land) and '0's (water) ...
 * return the number of islands
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally
 * or vertically.
 * You may assume all four edges of the grid are surrounded by water
 *
 * ref: https://leetcode.com/problems/number-of-islands/description/
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1', '0'},
                {'1','1','0','0', '0'},
                {'0','0','0','0', '0'}
        };
        String word = "ABCCED";
        System.out.println("Word: " + word + ", exists: " + numIslands(board));
    }
    static int WATER = 0;
    static int LAND = 1;
    static BlockingDeque<String> visited = new LinkedBlockingDeque<>();
    public static int numIslands(char[][] grid){
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == LAND){
                    count++;
                    callBFS(grid, i, j);
                }
            }
        }

        return count;
    }

    private static void callBFS(char[][] grid, int i, int j) {
        boolean rowIsOutOfBounds = i < 0 || i >= grid.length;
        boolean colIsOutOfBounds = j < 0 || j >= grid[i].length;

        if(rowIsOutOfBounds || colIsOutOfBounds || grid[i][j] == WATER){
            return;
        }
        // check above, visit it ... recursively call neighbors
        // goal is to set all adjacent cells to so we don't see them again
        grid[i][j] = '0';
        callBFS(grid, i+1, j);
        callBFS(grid, i-1, j);

        callBFS(grid, i, j+1);
        callBFS(grid, i, j-1);
    }
}
