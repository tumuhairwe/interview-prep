package com.tumuhairwe.prep.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 778 (hard)
 *
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 *
 * The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 *
 * ref: https://leetcode.com/problems/swim-in-rising-water/description/
 * ref: https://www.youtube.com/watch?v=amvrKlMLuGY
 */
public class SwimInRisingWater {

    /**
     * Solution summary
     * - Create pq with a comparator that sorts by time/weight (weed pq with origin)
     * - Create & seed visited boolean[] (seed with origin)
     * - iterative BFS (for each entry in pq)
     *      - pull entry/[] from pq
     *          - if its target, break/exit
     *          - calculate result = max(result, grid[r][c]) of current cell
     *          - visit all neighbors ( and to pq if unseen && in bounds)
     *          - mark neighbor as seen
     *  - return result
     */
    public int swimInWater(int[][] grid) {
        //0. create pq sorted by time
        Comparator<int[]> comp = Comparator.comparingInt(a -> a[2]);
        PriorityQueue<int[]> pq = new PriorityQueue<>(comp);
        pq.offer(new int[]{0, 0, grid[0][0]});  // entry= (row, col, heightOfWater)

        //1. mark as visited
        boolean[][] visited = new boolean[grid.length-1][grid.length-1];
        visited[0][0] = true;

        //2. Iterative bfs
        int result = 0;
        while (!pq.isEmpty()){
            int[] cell = pq.poll();

            result = Math.max(result, grid[cell[0]][cell[1]]);

            // if we've reach target cell, break
            if(cell[0] == grid.length -1 && cell[1] == grid[0].length - 1){
                break;
            }

            int[][] offsets = {
                    {1, 0}, {0, 1},
                    {-1, 0}, {0, -1}
            };
            for (int[] dir : offsets){
                int row = dir[0] + cell[0];
                int col = dir[1] + cell[1];

                if(row < 0 || row > grid.length || col < 0 || col > grid[0].length || visited[row][col]){
                    continue;
                }

                pq.offer(new int[]{row, col, grid[row][col]});
                visited[row][col] = true;
            }
        }

        return result;
    }
}
