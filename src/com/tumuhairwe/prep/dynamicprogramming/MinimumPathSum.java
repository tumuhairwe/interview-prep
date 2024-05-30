package com.tumuhairwe.prep.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 64 (Medium)
 *
 * Given a m x n grid, filled with non-negative numbers, find a path from the top left
 * to the bottom right, which minimizes the sum of all numbers along its path
 *
 * Example; [[1,3,1],[1,5,1],[4,2,1]]
 * output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 *
 * ref: https://leetcode.com/problems/minimum-path-sum/
 * ref: https://www.youtube.com/watch?v=pGMsrvt0fpk
 */
public class MinimumPathSum {
    public static void main(String[] args) {

    }
    public int minPathSum_pq(int[][] grid) {
        int rows = grid.length;
        int cols = grid.length;

        int[][] dp = new int[rows + 1][cols + 1];
        for (int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        return helper(grid, rows, cols, dp);
    }

    private int helper(int[][] grid, int rows, int cols, int[][] dp) {
        if(rows == 0 && cols == 0){
            return grid[0][0];
        }

        if (rows == 0){
            dp[rows][cols] = grid[rows][cols] + helper(grid, rows, cols -1, dp);
            return dp[rows][cols];
        }
        if(cols == 0){
            dp[rows][cols] = grid[rows][cols] + helper(grid, rows -1, cols, dp);
            return dp[rows][cols];
        }
        if(dp[rows][cols] != -1){
            return dp[rows][cols];
        }
        int rowToTheLeft = helper(grid, rows, cols - 1, dp);
        int colAbove = helper(grid, rows-1, cols, dp);
        dp[rows][cols] = grid[rows][cols] + Math.min(rowToTheLeft, colAbove);

        return dp[rows][cols];
    }

    public int minPathSum_pq_impl(int[][] grid) {
        int[] src = grid[0];
        int[] destination = grid[grid.length  - 1];

        // 0. prioritize minHeap by weight
        Comparator<Node> comp = Comparator.comparingInt(node -> node.weight);
        PriorityQueue<Node> pq = new PriorityQueue<>(comp);

        // 1. seed minHeap
        pq.add(new Node(0, 0, grid[0][0]));

        int minCost = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();

            int weight = minCost + node.weight;
            minCost = Math.min(minCost, weight);    // unlikely to change since grid has non-negative numbers i.e. all edges have a weight

            // 2.get next node/neighbor to add to pq
            for(int row = node.row + 1; row<grid.length; row++){
                for(int col = node.col + 1; col<grid[row].length; col++){

                    int cost = grid[row][col];
                    pq.offer(new Node(row, col, weight));
                }
            }
        }

        // 3. return minCost
        return minCost;
    }
    class Node implements Comparable<Node>{
        int row;
        int col;
        int weight;
        public Node(int x, int y, int w){
            this.row = x;
            this.col = y;
            this.weight = w;
        }

        public int compareTo(Node n){
            return Integer.compare(this.weight, n.weight);
        }
    }

}
