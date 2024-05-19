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
                {'O','O'},
                {'O','O',}
        };
        int[] arr = new int[]{1, 1};

//        Queue<Orange> rottenOranges = new LinkedList<>();
//        rottenOranges.add(new Orange(1, 1).);
//        boolean contains = rottenOranges.contains(new Orange(1, 1));
//        System.out.println(contains);
        //System.out.println(rottenOranges.peek().equals(arr));
        //new SurroundedRegions().solve(board);
        char[][] actual = new char[][]{
                {'O','O'},
                {'O','O',}
        };
    }

    private Queue<int[]> eligibleCells = new LinkedList<>();
    public void solve(char[][] board) {
        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[row].length; col++){

                boolean isEligible = board[row][col] == 'O';
                if(isEligible){
                    eligibleCells.offer(new int[]{row, col});
                }
            }
        }

        for(int[] cell : eligibleCells){
            int row = cell[0];
            int col = cell[1];
            flipNeighbors(board, row, col);  // flip all neighbors to X
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

            int proposedRow = row + rowOffset;
            int proposedCol = col + colOffset;

            boolean rowIsWithinBounds = 0 <= proposedRow && proposedRow < board.length;
            boolean colIsWithinBounds = 0 <= proposedCol && proposedCol < board[proposedRow].length;
            boolean cellIsWithinBounds = rowIsWithinBounds && colIsWithinBounds;

            boolean isOnBorder = (proposedRow == 0) || (proposedCol == 0)
                    || (proposedRow == board.length - 1)
                    || (proposedCol == board[row].length - 1);

            //boolean canBeFlipped = hasUnflippableNeighbors(proposedRow, proposedCol);
            if(cellIsWithinBounds && !isOnBorder){
                board[proposedRow][proposedCol] = 'X';
            }
        }
    }

    public boolean hasUnflippableNeighbors(int row, int col){
        int[][] offsets = {
                {1, 0}, {0, 1},
                {0, -1}, {-1, 0}
        };
        boolean isAdjacent = true;
        for(int[] offset : offsets) {
            int rowOffset = offset[0];
            int colOffset = offset[1];

            int proposedRow = row + rowOffset;
            int proposedCol = col + colOffset;

            isAdjacent &= this.eligibleCells.contains(new int[]{proposedRow, proposedCol});
        }
        return isAdjacent;
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
