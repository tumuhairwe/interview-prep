package com.tumuhairwe.prep.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 542 (01 Matrix)
 *
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 */
public class MatrixNearestZero {

    static class Entry{
        private int row;
        private int col;
        private int dist;
        public Entry(int r, int c, int d){
            this.row = r;
            this.col = c;
            this.dist = d;
        }
    }

    /**
     * Solution Summary
     *- Loop over 2D array ...
     *      a) store all cells in queue (with distance 0) (i.e. Entry(x, y, 0)
     * -do BFS on queue
     *      - for each entry
     *          - if matrix[row][col] == MARKER, set value to entry.distance
     *          - check neighbors, if they're in bounds (if in bounds && matrix[neighbor.row][neighbor.col] == MARKER, add to queue
     * - At the end matrix will have been traversed and all cells with non-zero value will have value=distance to nearest zerp
     * - return matrix
     */
    public int[][] updateMatrix(int[][] mat) {
        Queue<Entry> que = new LinkedList<>();

        // 0. put zeroes on queue
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if(mat[i][j] == 0){
                    que.offer(new Entry(i, j, 0));
                }
                else{
                    mat[i][j] = -1;
                }
            }
        }

        //1. do BFS on queu
        while (!que.isEmpty()){
            Entry entry = que.peek();

            // update cell to distance
            if(mat[entry.row][entry.col] < 0){
                mat[entry.row][entry.col] = entry.dist;
            }

            // visit neighbors
            int[][] offsets = {
                    {0, 1}, {1, 0},
                    {-1, 0}, {0, -1}
            };
            for (int[] direction : offsets){
                int row = entry.row + direction[0];
                int col = entry.col + direction[1];

                boolean isRowInbounds = row >= 0 && row < mat.length;
                boolean isColInBounds = col >= 0 && col < mat[0].length;
                if(isRowInbounds && isColInBounds && mat[row][col] == -1){
                    int newDist = entry.dist + 1;
                    que.offer(new Entry(row, col, newDist ));
                }
            }
        }

        return mat;
    }
}
