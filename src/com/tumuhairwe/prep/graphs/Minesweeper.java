package com.tumuhairwe.prep.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 529
 * ref: https://leetcode.com/problems/minesweeper/description/
 *
 * Solution summary (dfs)
 * - Create queue<int[]> of cells and seed it with click cell
 * - while queue is not empty
 *      - case a: if cell is UNREVEALED_MINE, set to REVEALED_MINE
 *      - case b:
 *          - count mines in neighboring cells
 *          - if mineCount > 0, set cell value to be $mineCount
 *          - if mineCount = 0,
 *                  - set cell value to be REVEALED_BLANK_WITH_NO_ADJACENT_MINES
 *                  - check neighbors, if a neighbor is an UNREVEALED_EMPTY_SQ,
 *                  - if neighbor is UNREVEALED_EMPTY_SQ,
 *                      - update board and set cell = REVEALED_BLANK_WITH_NO_ADJACENT_MINES
 *                      - add cell to queue to be popped() in the next iteration
 * TC: O(m * n)
 */
public class Minesweeper {
    char UNREVEALED_MINE = 'M';
    char UNREVEALED_EMPTY_SQ = 'E';

    char REVEALED_BLANK_WITH_NO_ADJACENT_MINES = 'B';
    char REVEALED_MINE = 'X';
    Queue<int[]> squaresToBeRevealed = new LinkedList<>();
    public char[][] updateBoard(char[][] board, int[] click) {
        //0. base case
        if(board.length == 0){
            return board;
        }

        int numRows = board.length;
        int numCols = board[0].length;
        Queue<int[]> squaresToBeRevealed = new LinkedList<>();
        squaresToBeRevealed.add(click);

        while(!squaresToBeRevealed.isEmpty()){
            int[] cell = squaresToBeRevealed.poll();
            int row = cell[0];
            int col = cell[1];

            if(board[row][col] == UNREVEALED_MINE){ //M = Mine
                board[row][col] = REVEALED_MINE;    //X
            }
            else{
                //1. get number of mines
                //int count = getAdjacentMines(board, row, col);
                int count = 0;

                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;
                        int r = row + i, c = col + j;
                        if (r < 0 || r >= numRows || c < 0 || c >= numCols) continue;
                        if (board[r][c] == 'M' || board[r][c] == 'X') count++;
                    }
                }

                System.out.println("There are " + count + " mines on (" + row + ", " + col + ")");
                if(count > 0){  // stop further DFS
                    board[row][col] = (char)(count + '0');
                }
                else{   // continue DFS on adjacent cells
                    board[row][col] = REVEALED_BLANK_WITH_NO_ADJACENT_MINES;    // B

                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i == 0 && j == 0) continue;
                            int r = row + i, c = col + j;
                            if (r < 0 || r >= numRows || c < 0 || c >= numCols) continue;
                            if (board[r][c] == UNREVEALED_EMPTY_SQ) {
                                //squaresToBeRevealed.add(new int[]{r, c});
                                board[r][c] = REVEALED_BLANK_WITH_NO_ADJACENT_MINES;
                                updateBoard(board, new int[]{r, c});
                            }
                        }
                    }
                }
            }
        }
        return board;
    }
}
