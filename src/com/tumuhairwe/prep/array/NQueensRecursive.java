package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a chess board of size N x N, determine how many ways N queens can be placed on the board,
 * such that no 2 queens attach each other.
 *
 * A queen can move horizontally, vertically or diagonally on a chess board. 1 queen can be attached by another
 * queen if both share the same row, column or diagonal
 *
 * Solution summary:
 * - Place the queen in the 1st column of the 1st row
 * - Place the queen wherever permissible in the next row
 * - Backtrack if not permission (i.e. no safe configuration exists)
 * - Once a solution is found, backtrack to find other possible solutions
 *
 * Constraint: 1 < n < 9
 *
 * This uses Backtracking
 *
 * Diff: Dynamic Programming is a technique that solves the optimization problem
 * using either minimum or maximum result.
 * Backtracking uses the brute force approach without considering the optimization problem
 */
public class NQueensRecursive {

    public static int solveNQueens(int n){
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> solution = new ArrayList<>(Collections.nCopies(n, -1));
        solveNQueensRecursive(n, solution, 0, results);
        return results.size();
    }

    private static void solveNQueensRecursive(int n, List<Integer> solution, int row, List<List<Integer>> results) {
        if(row == n){
            results.add(solution);
        }

        for (int i = 0; i < n; i++) {
            boolean isValid = isValidMove(row, i, solution);
            if(isValid){
                solution.set(row, i);
                solveNQueensRecursive(n, solution, row + 1, results);
            }
        }
    }

    /**
     * This method determines if a queen can be placed at proposed_row, proposed_col with the current solution
     * i.e. this move is vaslid only if no queen in current solution may attach the square at proposed_row and col
     */
    private static boolean isValidMove(int proposedRow, int proposedCol, List<Integer> solution) {
        int oldRow, oldCol, diagonalOffset = 0;
        for (int i = 0; i < proposedRow; i++) {
            oldRow = i;
            oldCol = solution.get(i);
            diagonalOffset = proposedRow - oldRow;

            if(oldCol == proposedCol
                    || oldCol == (proposedCol - diagonalOffset)
                    || oldCol == (proposedCol + diagonalOffset)){
                return false;
            }
        }

        return true;
    }

}
