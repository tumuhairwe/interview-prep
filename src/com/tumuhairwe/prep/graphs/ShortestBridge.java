package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * 934. Shortest Bridge (medium)
 *
 * You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
 * An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
 * You may change 0's to 1's to connect the two islands to form one island.
 *
 * Return the smallest number of 0's you must flip to connect the two islands.
 *
 * ref: https://www.youtube.com/watch?v=gkINMhbbIbU
 * ref: https://leetcode.com/problems/shortest-bridge/description/
 *
 * See also LeetCode 1162
 */
public class ShortestBridge {
    public static class Pair{
        int key;
        int value;
        public Pair(Integer k, Integer v){
            this.key = k;
            this.value = v;
        }

        public Integer getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "r="+key+ ", c=" + value;
        }
    }
    //TC: n^2
    //SC: n^2
    int LAND = 1;
    int WATER = 0;

    int numRows = 0;
    int numCols = 0;
    int[][] offsets = {
            {1, 0}, {0, 1},
            {-1, 0}, {0, -1}
    };
    Set<Pair> visited;

    public static void main(String[] args) {
        int[][] grid = {
                {0,1,0},{0,0,0},{0,0,1}
        };
        System.out.println(new ShortestBridge().shortestBridge(grid));
    }

    /**
     * Solution summary
     * - iterate the grid, find the first LAND cell,
     * - call dfs() on it -- Goal is to 4-diagonally visit all VALID cells with LAND
     * - call bfs() on it -- Goal is to count the number of steps needed to 4-diagonally traverse the grid until you reach a LAND cell
     */
    public int shortestBridge(int[][] grid) {
        // board is n x n
        int numRows = grid.length;
        int numCols = grid.length;
        Queue<int[]> landCells = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for(int i=0; i< grid.length; i++)    {
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == LAND){
                    collectLandCells(grid, i, j, landCells, visited);    // fill up visited set with cells
                    return dfs(grid, visited, landCells);   // take visited cells & return numCells
                }
            }
        }

        return -1;
    }

    /**
     * Method summary
     * - Create dq and seed with visited set
     * - for each iteration of draining dq, if a cell is valid and unvisited, add to dq and visited set ...
     * - increment number of steps for each iteration (not qDepth)
     * - repeat until you arrive at a LAND cell
     * - return number of steps
     */
    int dfs(int[][] grid, boolean[][] visited, Queue<int[]> landCells){
        int numberOfCellsToFlip = 0;

        while(!landCells.isEmpty()){
            int queDepth = landCells.size();

            while(queDepth-- > 0){
                int[] cell = landCells.poll();

                for(int[] dir : offsets){
                    int row = cell[0] + dir[0];
                    int col = cell[1] + dir[1];

                    if(!isValid(grid, row, col) || visited[row][col]){
                        continue;
                    }

                    // we've found the neighboring/2nd land mass
                    if(grid[row][col] == LAND){
                        return numberOfCellsToFlip;
                    }
                    // its water -> mark visited and add to que
                    visited[row][col] = true;
                    landCells.add(new int[]{row, col});
                }
            }

            numberOfCellsToFlip++;
        }

        return -1;
    }

    // goal is to fill up the visit hashSet with land cells

    /**
     * method summary
     * - if a cell is !WATER && isValid() && !visited -> add to visited set
     * - recursively 4-diagonally traverse grid until you meet WATER cell
     */
    void collectLandCells(int[][] grid, int row, int col, Queue<int[]> landCells, boolean[][] visited){
        if(!isValid(grid, row, col) || grid[row][col] == WATER || visited[row][col]){
            return;
        }

        // add to visited Set
        visited[row][col]= true;
        landCells.add(new int[]{row, col});

        //bfs
        int[][] offsets = {
                {1, 0}, {0, 1},
                {-1, 0}, {0, -1}
        };
        for(int[] direction : offsets){
            int r = row + direction[0];
            int c = col + direction[1];

            collectLandCells(grid, r, c, landCells, visited);
        }
    }

    boolean isValid(int[][] grid, int row, int col){
        boolean rowIsValid = row >= 0 && row < grid.length;
        boolean colIsValid = col >= 0 && col < grid[0].length;
        return rowIsValid && colIsValid;
    }
}
