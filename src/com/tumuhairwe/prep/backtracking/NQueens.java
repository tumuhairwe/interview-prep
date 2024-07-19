package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 51 (hard)
 *
 *  The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens
 *  attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * You may return the answer in any order.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.'
 * both indicate a queen and an empty space, respectively.
 *
 * ref: https://leetcode.com/problems/n-queens/?envType=problem-list-v2&envId=plakya4j
 */
public class NQueens {

    int size;
    List<List<String>> solutions;
    public List<List<String>> solveNQueens(int n){
        //0. init var
        size = n;
        solutions = new ArrayList<>();

        //1. create empty board
        char[][] emptyBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                emptyBoard[i][j] = '.';
            }
        }

        //2. backtrack
        Set<Integer> diagonals = new HashSet<>();
        Set<Integer> antiDiagonals = new HashSet<>();
        Set<Integer> columns = new HashSet<>();

        backtrack(emptyBoard, 0, diagonals, antiDiagonals, columns);

        //3. return solutions
        return solutions;
    }

    private void backtrack(char[][] board, int row, Set<Integer> diagonals, Set<Integer> antiDiagonals, Set<Integer> columns) {
        //1.0 base case - N queens hav been placed ... at to solution
        if(row == size){
            solutions.add(createBoard(board));
        }

        //1.1 for each col, if queen is placeable ... add queen, call recursive function, backtrack
        for (int col = 0; col < board.length; col++) {
            int currDiagonal = row - col;
            int currAntiDiagonal = row + col;

            boolean isQueenNotPlaceable = columns.contains(col)
                    || diagonals.contains(currDiagonal)
                    || antiDiagonals.contains(currAntiDiagonal);
            if(isQueenNotPlaceable){
                continue;
            }

            //1.1 else ... add queen to board ... backtrack ... remove queen from board
            columns.add(col);
            diagonals.add(currDiagonal);
            antiDiagonals.add(currAntiDiagonal);
            board[row][col] = '.';

            backtrack(board, row + 1, diagonals, antiDiagonals, columns);

            //1.1 remove Queen since we've explored all valid paths
            columns.remove(col);
            diagonals.remove(currDiagonal);
            antiDiagonals.remove(currAntiDiagonal);
            board[row][col] = '.';
        }
    }

    private List<String> createBoard(char[][] state) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < state.length; row++) {  // length = size
            String currentRow = new String(state[row]);
            board.add(currentRow);
        }
        return board;
    }
}
