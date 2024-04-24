package com.tumuhairwe.prep.array;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Given a 2D binary grid, which represents a map of '1's (land) and '0's (water) ...
 * return the number of islands
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally
 * or vertically.
 * You may assume all four edges of the grid are surrounded by water
 *
 * LeetCode 200 (medium)
 * ref: https://www.youtube.com/watch?v=pV2kpPD66nE
 * ref: https://leetcode.com/problems/number-of-islands/description/
 */
public class NumberOfIslands {

    static class Cell{
        int row;
        int col;
        public Cell(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1', '0'},
                {'1','1','0','0', '0'},
                {'0','0','0','0', '0'}
        };

        System.out.println("Iterative: There are " + numIslands2Iterative(board) + " islands");
        System.out.println("Recursive: There are " + numIslandsRecursive(board) + " islands");
    }
    static char WATER = '0';
    static char LAND = '1';
    static Set<Cell> visited = new HashSet<>();
    public static int numIslands2Iterative(char[][] grid){
        if(grid.length == 0){
            return 0;
        }

        int numIslands = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {

                Cell cell = new Cell(row, col);
                if(grid[row][col] == LAND && visited.contains(cell)){
                    numIslandsIterative(grid, row, col);
                    numIslands++;
                }
            }
        }

        return numIslands;
    }

    /**
     * Solution summary
     * - Create cell
     * - Add cell to visited Set (to avoid re-visiting which could lead to circular interations)
     * - Add cell to queue
     * - Iterate over que -> find adjacent neighbors and add them to the queue
     */
    static void numIslandsIterative(char[][] grid, int row, int col){
        //BlockingDeque<Cell> que = new LinkedBlockingDeque<>();    // is thread-safe && has fixed size
        Deque<Cell> que = new ArrayDeque<>();   // not thready-safe

        Cell cell = new Cell(row, col);

        // 1. add cell to visited Set -> so we don't visit already visited cells
        visited.add(cell);

        // 2. add cell to visited Set ->
        // so we can q.pop() off at beginning of traversal and q.add() when we encounter a neighbor
        que.add(cell);

        while (!que.isEmpty()){
            Cell current = que.pop();

            // check boundary to make sure cell isn't out of bounds || is not WATER
//            BiFunction<Cell, char[][], Boolean> isWithinBounds = (cell1, ints) ->
//                    cell.row > 0 && ints.length < cell1.row
//                            && cell1.col > 0 && ints[cell1.row].length < cell1.col;
//            if(!isWithinBounds.apply(current, grid)){
//                continue;
//            }
            boolean rowIsOutOfBounds = current.row < 0 || current.row >= grid.length;
            boolean colIsOutOfBounds = current.col < 0 || current.row >= grid[current.row].length;
            if(rowIsOutOfBounds || colIsOutOfBounds || grid[current.row][current.col] == WATER){
                continue;
            }

            // this might unnecessarily add out-of-bound cells to
            List<Cell> neighbors = new ArrayList<>();
            if(grid[current.row + 1][current.col] == LAND){
                Cell up = new Cell(current.row + 1, current.col);
                neighbors.add(up);
            }
            else if(grid[current.row - 1][current.col] == LAND){
                Cell down = new Cell(current.row - 1, current.col);
                neighbors.add(down);
            }
            else if(grid[current.row][current.col + 1] == LAND){
                Cell right = new Cell(current.row, current.col + 1);
                neighbors.add(right);
            }
            else if(grid[current.row][current.col - 1] == LAND){
                Cell left = new Cell(current.row, current.col - 1);
                neighbors.add(left);
            }
            // alternate implementation of checking all neighbors
//            int[][] offsets = {
//                    {0, 1}, {1, 0},
//                    {0, -1}, {-1, 0}
//            };
//            for (int[] offset : offsets){
//                int rowOffset = offset[0];
//                int colOffset = offset[1];
//
//                // 3. repeat from the neighboring cell (index + 1) until the entire grid is traversed of the word is found
//                // result = depthFirstSearch(row + rowOffset, col + colOffset, word, index+1, grid);
//                Cell c = new Cell(current.row + rowOffset, current.col + colOffset);
//                BiFunction<Cell, char[][], Boolean> isWithinBounds = (cell1, ints) -> ints.length < cell1.row && ints[cell1.row].length < cell1.col;
//                if(isWithinBounds.apply(c, grid) && grid[c.row][c.col] == LAND){
//                    neighbors.add(c);
//                }
//            }

            neighbors.stream().forEach(n -> {
                if(!visited.contains(n)){
                    que.push(n);   // add to queue so while
                    visited.add(n);
                }
            });
        }
    }

    /**
     * Solution summary
     * - Iterate over grid & for each cell that is land -> callBFS()
     * - callBFS() will
     *      - do boundary checks
     *      - set cell value to water (i.e. marking it as visited)
     *      - call calBFS() recursively on all 4 neighbors
     * - increment count++;
     * NB: At the end parent for-loop will check if cell is ALDN (i.e all neighboring contigous cells were markeed as water)
     */
    public static int numIslandsRecursive(char[][] grid){
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
        boolean colIsOutOfBounds = j < 0 || j >= grid[0].length;

        if(rowIsOutOfBounds || colIsOutOfBounds || grid[i][j] == WATER){
            return;
        }
        // check above, visit it ... recursively call neighbors
        // goal is to set all adjacent cells to, so we don't see them again
        grid[i][j] = '0';   // mark it as water == marking it as visited
        callBFS(grid, i+1, j);
        callBFS(grid, i-1, j);

        callBFS(grid, i, j+1);
        callBFS(grid, i, j-1);
    }
}
