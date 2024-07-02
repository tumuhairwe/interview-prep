package com.tumuhairwe.prep.graphs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

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
    static class Pair<K, V>{
        K key;
        V value;
        public Pair(K k, V v){
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
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
    Set<Pair<Integer, Integer>> visited;

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
        numRows = grid.length;
        numCols = grid.length;
        visited = new HashSet<>();

        for(int i=0; i< grid.length; i++)    {
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == LAND){
                    dfs(grid, i, j);    // fill up visited set with cells
                    return bfs(grid, visited);   // take visited cells & return numCells
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
    int bfs(int[][] grid, Set<Pair<Integer, Integer>> visited){
        int result = 0;
        Deque<Pair<Integer, Integer>> deque = new ArrayDeque<>(visited);

        while(!deque.isEmpty()){
            int queDepth = deque.size();
            Pair<Integer, Integer> coord = deque.pop();

            while(queDepth-- > 0){
                for(int[] dir : offsets){
                    int row = coord.getKey() + dir[0];
                    int col = coord.getValue() + dir[1];
                    Pair<Integer, Integer> cell = new Pair<>(row, col);

                    if(!isValid(row, col) || visited.contains(cell)){
                        continue;
                    }

                    if(grid[row][col] == LAND){ // we've reached another island
                        return result;
                    }
                    else{   // its water -> mark visited and add to que
                        visited.add(cell);
                        deque.add(cell);
                    }
                }
            }

            result++;
        }

        return result;
    }

    // goal is to fill up the visit hashSet with land cells

    /**
     * method summary
     * - if a cell is !WATER, isValid and is not net visited,
     * - add to visited set
     * - recursively 4-diagonally traverse grid until you meet a WATER cell
     */
    void dfs(int[][] grid, int row, int col ){
        if(!isValid(row, col) || grid[row][col] == WATER || visited.contains(new Pair<>(row, col))){
            return;
        }

        // add to visited Set
        visited.add(new Pair<>(row, col));

        //bfs
        for(int[] direction : offsets){
            int r = row + direction[0];
            int c = col + direction[1];

            dfs(grid, r, c);
        }
    }

    boolean isValid(int row, int col){
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }
}
