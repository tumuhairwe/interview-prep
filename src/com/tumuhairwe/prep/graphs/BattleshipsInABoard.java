package com.tumuhairwe.prep.graphs;

/**
 * LeetCdode 419. Battleships in a Board (medium)
 *
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.',
 * return the number of the battleships on board.
 *
 * Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size.
 * At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).
 */
public class BattleshipsInABoard {

    public static void main(String[] args) {
        char[][] board = {
                {'X', '.', '.', 'X'},
                {'.', '.','.','X'},
                {'.','.','.','X'}
        };
        System.out.println(countBattleships(board));
    }
    /**
     * Solution summary
     * - traverse 2D array
     * - check if neighbor-to-the-top && neighbor-to-the-left is NOT a battleship AND cell is itself a BATTLESHIP
     * - increment
     *
     * - at end of iteration, return count
     * ref: https://leetcode.com/problems/battleships-in-a-board/solutions/1632589/java-5-lines-follow-up-one-pass-o-1-space/
     */
    public static int countBattleships(char[][] board){
        int count = 0;
        char BATTLESHIP = 'X';

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if( board[i][j] == BATTLESHIP
                    && (i == 0 || board[i - 1][j] != BATTLESHIP)
                    && (j == 0) || board[i][j - 1] != BATTLESHIP){
                    count++;
                }
            }
        }

        return count;
    }
}
