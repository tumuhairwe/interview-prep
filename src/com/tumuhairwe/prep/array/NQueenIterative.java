package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * Problem:
 * Given n x a chess board (n = integer)
 * Return all distinct solutions to the n-queens puzzel (i.e. place queens on the chess board so that
 * no 2 queens can attach each other.
 * You may return the answer in any order
 *
 * LeetCode 51 (Hard)
 * ref: https://leetcode.com/problems/n-queens/description/
 *
 * Solution summary:
 * - Place a queen in the 1st column of the 1st row
 * - Use a stack to keep track of the current solution
 * - Place a queen wherever permissible in the next row;
 * - Backtrack by popping from the stack to find the next solution
 *
 * TC: O(n!) - where n is the dimension of the chessboard
 * SC: O(n) - Where n is the dimension of the chessboard
 */
// this solution uses a stack to store the solution.
// stack will hold only the column values and one solution will be stored in the stack at a time
public class NQueenIterative {

    public static void main(String[] args) {
        int n = 4;
        List<List<Integer>> results = solveNQueens(n);
        System.out.println("There results : ");
        for (List<Integer> arr : results){
            System.out.println(Arrays.toString(arr.toArray()));
        }
    }
    static List<List<Integer>> solveNQueens(int n){
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> solution = new ArrayList<>(Collections.nCopies(n, -1));
        Stack<Integer> solutionStack = new Stack<>();

        int row = 0;
        int col = 0;
        while (row < n){
            // for the current state of the solution, check if a queen can be placed in any col of this row
            while (col < n){
                if(isValidMove(row, col, solution)){
                    // if this is a safe position for a queen, save it to the current solution on the stack
                    solutionStack.push(col);
                    solution.set(row, col);

                    row++;
                    col = 0;

                    // (break out of the inner loop) .. and move on to checking the next row
                    break;
                }
            }

            // if we have checked all the columns
            if(col == n){
                if (!solutionStack.isEmpty()){
                    // backtrack since current row does not offer a safe spot given the previous move
                    // so, get set up to check the previous row with the next column
                    col = solutionStack.peek() + 1;
                    solutionStack.pop();
                    row--;
                }
                else {
                    // if we have backtracked all the way and found this to be a dead-end,
                    // break out of the inner loop as no more solutions exist
                    break;
                }
            }

            //if we have found a safe spot for a queen in each of the rows
            if(row == n){
                results.add(new ArrayList<>(solution));

                // backtrack to find the next solution
                row--;
                col = solutionStack.peek() + 1;
                solutionStack.pop();
            }
        }

        return results;
    }

    private static boolean isValidMove(int proposedRow, int proposedCol, List<Integer> solution) {
        // we need to check with all queens in the current solution

        int oldRow, oldCol, diagonalOffset = 0;
        for (int i = 0; i < proposedRow; i++) {
            oldRow = i;
            oldCol = solution.get(i);
            diagonalOffset = proposedRow - proposedCol;

            // oldCol == proposedCol --> Checks if there are any queens in the proposed column
            // oldCol == proposedCol - diagonalOffset --> Checks if there are any queens on the bottom left diagonal to the proposed place
            // oldCol == proposedCol + diagonalOffset --> Checks if there are any queens on the bottom right diagonal to the proposed place
            if (oldCol == proposedCol || oldCol == proposedCol - diagonalOffset || oldCol == proposedCol + diagonalOffset) {
                return false;
            }
        }

        return true;
    }

    //
}
