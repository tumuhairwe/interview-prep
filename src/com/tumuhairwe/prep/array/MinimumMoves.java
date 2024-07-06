package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: You are given a 0-indexed integer matrix grid of size 3 x 3.
 * Representing the number of stones in each cell. The grid contains exactly 3 stones and there can be
 * <b>multiple</b> stones in a single cell
 *
 * In one move,you can move a single stone from its current cell to any other cell if the two cells share a side
 *
 * Return the minimum number of moves required to place one stone in each cell (i.e. spread the stones so that
 * there is 1 stone in each cell).
 *
 * LeetCode 2850 - Medium
 * Dynamic Programming
 * ref : https://www.geeksforgeeks.org/maximum-bipartite-matching/
 * ref: https://leetcode.com/problems/minimum-moves-to-spread-stones-over-grid/description/4027805
 *
 */
public class MinimumMoves {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,1,0},{1,1,1},{1,2,1}};
        int moves = minimumMoves(grid);

        System.out.println("The minimum member of moves = " + moves);
    }
    private static int minMoves = -1;
    public static int minimumMoves(int[][] grid){
        minMoves = Integer.MAX_VALUE;
        //int rows = grid.length;
        //int cols = grid[0].length;

        List<int[]> zeros = new ArrayList<>(), ones = new ArrayList<>();
        for(int row = 0; row< grid.length; row++){
            for(int col = 0; col< grid[row].length; col++){
                int value = grid[row][col];
                if(value> 1){
                    ones.add(new int[]{row, col, value});
                }
                else if(grid[row][col] == 0){
                    zeros.add(new int[]{row, col, value});
                }
            }
        }

        findMoves(zeros, ones, 0, 0, grid);
        return minMoves;
    }

    private static void findMoves(List<int[]> zeros, List<int[]> ones, int startIndex, int moves, int[][]grid){
        int sizeOfZeros = zeros.size();
        int sizeOfOnes = ones.size();;

        if(sizeOfZeros == startIndex){
            minMoves = Math.min(minMoves, moves);
        }

        int[] zeroCell = zeros.get(startIndex);
        for (int index = 0; index < sizeOfOnes; index++) {
            int[] oneCell = ones.get(index);
            if(grid[oneCell[0]][oneCell[1]] > 1  ){   // is over-populated
                grid[oneCell[0]][oneCell[1]]--;

                int val = Math.abs(zeroCell[0] - oneCell[0]) +
                        Math.abs(zeroCell[1] - oneCell[1]);
                findMoves(zeros, ones, startIndex+1, moves + val, grid);
                grid[oneCell[0]][oneCell[1]]++;
            }
        }
    }
}
