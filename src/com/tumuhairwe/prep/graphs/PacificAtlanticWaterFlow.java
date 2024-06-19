package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 417 (medium). Pacific Atlantic Water Flow
 *
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 */
public class PacificAtlanticWaterFlow {

    public static void main(String[] args) {
        int[][] grid = {
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
            {5,1,1,2,4}
        };
        List<List<Integer>> results = pacificAtlantic(grid);
        System.out.println(results);
    }

    /**
     * Solution summary
     * - Define 2 sets to hold cells that can reach each ocean (pacific and atlantic)
     * - define dfs() that will
     *      - check if a cell is already visited (return)
     *      - check if cell's height is <= previousHeight
     *      - do recursive dfs on all 4 neighbors
     * - Call recursive dfs on top row and bottom row
     * - Call recursive dfs on left col and right col
     * - calculate the cells that have an intersection of the 2 sets (cells visible to atlantic & pacific)
     */
    // TC: O(m x n)
    // SC: O(m x n)
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        // 0. declare sets
        int rows = heights.length;
        int cols = heights[0].length;

        // 0. declare visited trackers
        boolean[][] atlantic = new boolean[rows][cols];
        boolean[][] pacific = new boolean[rows][cols];

        //2. call DFS on each cell on top-row and bottom-row
        for (int col = 0; col < cols; col++) {
            dfs(0, col, pacific, Integer.MIN_VALUE, heights);
            dfs(rows - 1, col, atlantic, Integer.MIN_VALUE, heights);
        }

        //2. call DFS on cell on left col and right col coast
        for (int row = 0; row < rows; row++) {
            dfs(row, 0, pacific, Integer.MIN_VALUE, heights);       // facing pacific -> used pacific visited tracker
            dfs(row, cols -1, atlantic, Integer.MIN_VALUE, heights);    // facing atlantic -> use atlantic visted-tracker
        }

        //3. find intersection of 2 sets
        List<List<Integer>> intersection = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if(atlantic[row][col] && pacific[row][col]){
                    intersection.add(List.of(row, col));
                }
            }
        }
        return intersection;
    }

    static void dfs(int row, int col, boolean[][] visited, int previousHeight, int[][] heights){

        // check if out of bounds
        if(row < 0 || row >= visited.length || col < 0 || col >= visited[0].length){
            return;
        }
        // check if visited
        if(visited[row][col]){
            return;
        }
        // check if height is less than previousHeight
        if(heights[row][col] < previousHeight){
            return;
        }

        // add to visited set
        visited[row][col] = true;

        // recursive call on neighbors
        int[][] offsets = {
                {1, 0}, {0, 1},
                {-1, 0}, {0, -1}
        };
        for(int[] direction : offsets){
            int x = row + direction[0];
            int y = col + direction[1];
            dfs(x, y, visited, heights[row][col], heights);
        }
    }
}
