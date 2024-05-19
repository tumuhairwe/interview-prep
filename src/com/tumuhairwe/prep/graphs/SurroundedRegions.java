package com.tumuhairwe.prep.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 130 (medium)
 * Surrounding Regions - Given an M x N matrix board containing 'X' and 'O',
 * capture all regions that are 4-directionally surrounded by 'X'
 *
 *  A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * ref: https://leetcode.com/problems/surrounded-regions/
 */
public class SurroundedRegions {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };
        new SurroundedRegions().solve(board);
        char[][] actual = new char[][]{
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };
    }
    public void solve(char[][] board) {
        Queue<int[]> eligibleCells = new LinkedList<>();

        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[row].length; col++){

                boolean isOnBorder = (row == 0) || (col == 0)
                        || (row == board.length - 1)
                        || (col == board[row].length - 1);

                boolean isEligible = !isOnBorder && board[row][col] == 'O';
                if(isEligible){
                    eligibleCells.offer(new int[]{row, col});
                }
            }
        }

        for(int[] cell : eligibleCells){
            int row = cell[0];
            int col = cell[1];
            if(!isSurrounded(board, row, col)){
                flipNeighbors(board, row, col);  // flip all neighbors to X
            }
        }
    }

    void flipNeighbors(char[][] board, int row, int col){
        int[][] offsets = {
                {1, 0}, {0, 1},
                {0, -1}, {-1, 0}
        };
        for(int[] offset : offsets){
            int rowOffset = offset[0];
            int colOffset = offset[1];

            int x = row + rowOffset;
            int y = col + colOffset;

            boolean rowIsWithinBounds = 0 <= x && x < board.length;
            boolean colIsWithinBounds = 0 <= y && y < board[x].length;
            boolean cellIsWithinBounds = rowIsWithinBounds && colIsWithinBounds;
            if(cellIsWithinBounds){
                board[x][y] = 'X';
            }
        }
    }


    boolean isSurrounded(char[][] board, int row, int col){
        int[][] offsets = {
                {1, 0}, {0, 1},
                {0, -1}, {-1, 0}
        };
        for(int[] offset : offsets){
            int rowOffset = offset[0];
            int colOffset = offset[1];

            if(board[row + rowOffset][col + colOffset] != 'X'){
                return false;
            }
        }

        return true;
    }
}
