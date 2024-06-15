package com.tumuhairwe.prep.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

        // 1. collect count of square to be revealed;
        for (int i = 0; i < click.length; i++) {
            for (int j = 0; j < click.length; j++) {
                if(board[i][j] == UNREVEALED_EMPTY_SQ || board[i][j] == UNREVEALED_MINE){
                    squaresToBeRevealed.add(new int[]{i, j});
                }
            }
        }

        int countRemainingUnrevealedSquares = 0;
        List<Character> revealableStates = List.of(UNREVEALED_EMPTY_SQ);

        // 2. reveal
        char[][] board2 = reveal(board, click[0], click[1], revealableStates);

        for (int i = 0; i < board2.length; i++) {
            for (int j = 0; j < board2[0].length; j++) {
                if(board2[i][j] == UNREVEALED_EMPTY_SQ || board[i][j] == UNREVEALED_MINE){
                    countRemainingUnrevealedSquares++;
                }
            }
        }

        // no more squares to reveal
        if(squaresToBeRevealed.size() - countRemainingUnrevealedSquares == 0){
            return board2;
        }
        return board2;
    }

    char[][] reveal(char[][] board, int row, int col, List<Character> revealableStates){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == UNREVEALED_MINE){
                    board[i][j] = REVEALED_MINE;
                    return board;   // rule 1: game over
                }
                else if(board[i][j] == UNREVEALED_EMPTY_SQ){
                    int numAdjacentMines = getAdjacentMines(board, i, j);
                    if(numAdjacentMines == 0){
                        board[i][j] = REVEALED_BLANK_WITH_NO_ADJACENT_MINES;    // rule 2

                        // set neighbors to original state
                        reveal(board, i, j, List.of(UNREVEALED_EMPTY_SQ));
                    }
                    else {
                        board[i][j] = String.valueOf(numAdjacentMines).charAt(0);
                    }
                }
            }
        }

        return board;
    }

    int getAdjacentMines(char[][] board, int row, int col){
        int numberOfMines = 0;
        boolean isRowOutOfBounds = row < 0 || row >= board.length;
        boolean isColOutOfBounds = col < 0 || col >= board[0].length;

        if(isRowOutOfBounds || isColOutOfBounds){
            return numberOfMines;
        }

        int[][] offsets = {
                {0, 1}, {1, 0},
                {-1, 0}, {0, -1},
        };
        for(int[] direction : offsets){
            int x = row + direction[0];
            int y = col + direction[1];

            boolean rowInBounds = x >= 0 && x < board.length;
            boolean colInBounds = y >= 0 && y < board[0].length;

            if(!rowInBounds || !colInBounds){
                continue;
            }
            if(board[x][y] == UNREVEALED_MINE){
                numberOfMines++;
            }
        }

        return numberOfMines;
    }
}
