package com.tumuhairwe.prep.graphs;

/**
 * LeetCode 130 (medium)
 * Surrounding Regions - Given an M x N matrix board containing 'X' and 'O',
 * capture all regions that are 4-directionally surrounded by 'X'
 *
 *  A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * ref: https://leetcode.com/problems/surrounded-regions/
 * ref: https://www.youtube.com/watch?v=9z2BunfoZ5Y
 */
public class SurroundedRegions {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'O','O'},
                {'O','O',}
        };
        int[] arr = new int[]{1, 1};
        solve(board);
    }

    private static final char SPECIAL_CHARACTER = 'T';

    // TC : (n x M) -- size of grid bcoz we have to iterate over its 3 times
    public static void solve(char[][] board) {
        int nRows = board.length;
        int nCols = board[0].length;

        // 0. mark border cells with SPECIAL CHAR (top row & bottom row)
        for(int i=0; i<nCols; i++){
            // top row
            if(board[0][i] == 'O'){
                flipNeighbors(board, 0, i);
            }

            // bottom row
            if(board[nRows - 1][i] == 'O'){
                flipNeighbors(board, nRows - 1, i);
            }
        }

        // 1. mark boarder cells with SPECIAL_CHAR (left column & right column)
        for(int i=0; i<nRows; i++){
            if(board[i][0] == 'O'){
                flipNeighbors(board, 0, i);
            }
            if(board[i][nCols - 1] == 'O'){
                flipNeighbors(board, i, nCols - 1);
            }
        }

        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[row].length; col++){

                // capture unsurrounded regions -> O -> X
                if(board[row][col] == 'O'){
                    board[row][col] = 'X';
                }

                // capture unsurrounded regions -> O -> X
                if(board[row][col] == SPECIAL_CHARACTER){
                    board[row][col] = 'O';
                }
            }
        }
    }
    static void flipNeighbors(char[][] board, int row, int col){

        boolean rowIsWithinBounds = 0 <= row && row < board.length;
        boolean colIsWithinBounds = 0 <= row && col < board[col].length;
        boolean cellIsWithinBounds = rowIsWithinBounds && colIsWithinBounds;
        if(!cellIsWithinBounds){
            return;
        }

        board[row][col] = SPECIAL_CHARACTER;

        int[][] offsets = {
                {1, 0}, {0, 1},
                {0, -1}, {-1, 0}
        };
        for(int[] offset : offsets){
            int rowOffset = offset[0];
            int colOffset = offset[1];

            int proposedRow = row + rowOffset;
            int proposedCol = col + colOffset;

            flipNeighbors(board, proposedRow, proposedCol);
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
