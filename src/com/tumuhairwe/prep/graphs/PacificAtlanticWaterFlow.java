package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 417 (medium). Pacific Atlantic Water Flow
 */
public class PacificAtlanticWaterFlow {

    int lastRow;
    int lastCol;

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
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // 0. declare sets
        Set<int[]> atlantic = new HashSet<>();
        Set<int[]> pacific = new HashSet<>();

        //0.1 declare vars
        lastRow = heights.length - 1;
        lastCol = heights[0].length - 1;


        //2. call DFS on cell on pacific coast
        for (int col = 0; col < heights[0].length; col++) {
            dfs(0, col, pacific, heights[0][col], heights);
            dfs(lastRow, col, atlantic, heights[lastRow][col], heights);
        }

        //2. call DFS on cell on atlantic coast
        for (int row = 0; row < heights.length; row++) {
            dfs(row, 0, pacific, heights[row][0], heights);
            dfs(row, lastCol, atlantic, heights[row][lastCol], heights);
        }

        //3. find intersection of 2 sets
        List<List<Integer>> intersection = new ArrayList<>();
//        for(int[] cell : atlantic){
//            if(pacific.contains(cell)){
//                intersection.add(List.of(cell[0], cell[1]));
//            }
//        }
        for (int row = 0; row < heights.length; row++) {
            for (int col = 0; col < heights[0].length; col++) {
                int[] cell = new int[]{row, col};

                if(atlantic.contains(cell) && pacific.contains(cell)){
                    intersection.add(List.of(cell[0], cell[1]));
                }
            }
        }
        return intersection;
    }

    void dfs(int row, int col, Set<int[]> visited, int previousHeight, int[][] heights){
        // check if visited || out of bounds || heights is < previousHeight
        if(visited.contains(new int[]{row, col})
                || row < 0 || row >= lastRow || col < 0 || col >= lastCol
                || heights[row][col] <= previousHeight)
        {
            return;
        }

        // add to visited set
        visited.add(new int[]{row, col});

        // recursive call on neighbors
        dfs(row + 1, col, visited, heights[row][col], heights);
        dfs(row - 1, col, visited, heights[row][col], heights);
        dfs(row, col + 1, visited, heights[row][col], heights);
        dfs(row, col - 1, visited, heights[row][col], heights);
    }
}
