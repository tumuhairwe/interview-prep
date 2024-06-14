package com.tumuhairwe.prep.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 1162 (medium
 *
 * ref: https://leetcode.com/problems/as-far-from-land-as-possible/
 */
public class ASFarFromLandAsPossible {
    private static class Pair {
        int key;
        int value;

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1,0,1},{0,0,0},{1,0,1}
        };
        int d = maxDistance(arr);
        System.out.println(d);
    }
    public static int maxDistance(int[][] grid) {
        int WATER = 0;
        int LAND = 1;
        int waterCells = 0;
        int numRows = grid.length;
        int numCols = grid[0].length;
        int[][] visited = new int[grid.length][grid[0].length];

        //0. create queue of LAND cells | Aat same time, count number of water cells | copy + paste all cells into visited matrix
        Queue<Pair> que = new LinkedList<>();
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                visited[i][j] = grid[i][j];   // copy cell to visited matrix

                if(grid[i][j] == WATER){
                    waterCells++;
                }
                else if(grid[i][j] == LAND){
                    que.add(new Pair(i, j));
                }
            }
        }

        //1. if it's all water or there's no water, return -1
        boolean theresNoWater = waterCells == 0;
        boolean isEntirelyWater = waterCells == (numRows * numCols);
        if(isEntirelyWater || theresNoWater){
            return -1;
        }

        //3. do DFS
        //When the matrix doesn't have any land cell, the while loop will not start at all, and hence distance will have its initial value (-1).
        int distance = -1;
        while (!que.isEmpty()) {
            int depth = que.size();

            for(int i=0; i<depth; i++){
                Pair cell = que.poll();

                int[][] offsets = {
                        {0, 1}, {1, 0},
                        {-1, 0}, {0, -1}
                };
                for(int[] direction : offsets){
                    int row = cell.key + direction[0];
                    int col = cell.value + direction[1];

                    boolean isRowOutOfBounds =  row < 0 || row >= grid.length;
                    boolean isColOutOfBounds = col < 0 || col >= grid[0].length;
                    if(isRowOutOfBounds || isColOutOfBounds){
                        continue;
                    }
                    if(grid[row][col] == WATER){
                        que.offer(new Pair(row, col));
                    }

                    // mark it as visited to avoid re-traversing it again
                    visited[row][col] = LAND;
                }
            }

            // after each iteration, increment distance since we've covered 1 unit of distance from all cells in every direction
            distance++;
        }

        // distance == 0, there's no water in the cell --> we wont get here
        // distance will be -1 if the queue was empty u.e. no LAND cell at all
        return distance;
    }
}
