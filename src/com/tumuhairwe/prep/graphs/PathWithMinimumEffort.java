package com.tumuhairwe.prep.graphs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 1631 (medium)
 *
 * ref: https://leetcode.com/problems/path-with-minimum-effort/description/?envType=list&envId=mbxk05sr
 * ref: https://leetcode.com/problems/path-with-minimum-effort/solutions/5597674/dijkstra-s-algorithm-with-a-priority-queue-simple-explanation/?envType=list&envId=mbxk05sr
 */
public class PathWithMinimumEffort {
    class Cell{
        int row;
        int col;
        int effort;
        public Cell(int r, int c, int e){
            row = r;
            col = c;
            effort = e;
        }
    }

    /**
     * Solution summary
     * - Create a 2D int matrix (differenceMatrix) and init with max_value
     * - create a 2D boolean matrix and (visited) to track visited coordinates
     * - Create a cell class to track difference in effort of every coordinate
     * - Create minHeap of Cells & seed with origin (0, 0, diffMatrix[0][0])
     * - do DFS on pq
     *     - find diff in height btwn current & neighbor
     *     - if neighborDiff is bigger, update diffMatrix, mark cell as visited and add to pq
     */
    public int minimumEffortPath(int[][] heights){
        int rows = heights.length;
        int cols = heights[0].length;

        //0. create 2D diff matrix & seed (0, 0) with 0
        int[][] effortMatrix = new int[rows][cols];
        Arrays.stream(effortMatrix).forEach(arr -> Arrays.fill(arr, Integer.MAX_VALUE));
        effortMatrix[0][0]= 0;

        //1. create pq (minHeap that sorts by difference)
        Comparator<Cell> comp = Comparator.comparingInt(c -> c.effort);
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(comp);
        minHeap.add(new Cell(0, 0, effortMatrix[0][0]));

        int[][] offsets = {
                {0, 1}, {1, 0},
                {-1, 0}, {0, -1}
        };

        // do dfs
        while (!minHeap.isEmpty()){
            Cell curr = minHeap.poll(); //TC: log n

            //2. if destination -> return
            boolean isDestination = curr.row == rows - 1 && curr.col == cols - 1;
            if(isDestination){
                return curr.effort;
            }

            // 3 check neighbors
            for (int[] dir : offsets) {
                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                // Check if new position is within bounds
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    int newEffort = Math.max(curr.effort, Math.abs(heights[newRow][newCol] - heights[curr.row][curr.col]));

                    // If the new effort is less than previously recorded effort, update and add to the queue
                    if (newEffort < effortMatrix[newRow][newCol]) {
                        effortMatrix[newRow][newCol] = newEffort;
                        minHeap.add(new Cell(newRow, newCol, newEffort));
                    }
                }
            }
        }

        return effortMatrix[rows - 1][cols - 1];
    }
}
