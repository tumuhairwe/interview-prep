package com.tumuhairwe.prep.array;

/**
 * Given a 2D board and a grid, find if the word exists in the board
 *
 * The word can be constructed from letters and sequentially adjacent cells ... where cells are horizontally
 * or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 *  LeetCode 79 (Medium) - Searching problem (using recursion)
 *
 *  Solution:
 *  - create a isExists() function that traverses the whole 2D array, cell by cell, if exists, return true
 *  - create a searchWord(int i, int j, int indexOfCurrentChar, String word, int[][] board) function that calls itself
 *      - add cell to be visited 2D array (to tracked visited nodes)
 *      - add boundary checks (x-out-of-bound, y-out-of-bound, is-visited, and is-a-diff-letter)
 *      - call recursively passing <i>indexOfCurrentChar + 1</i>, and all 4 neighboring cells (2 horizontal & 2 vertical)
 *      - recursive call should return true if we've reached end-of-word
 *
 * NOTE: This is an iterative solution --> NOT backtracking
 *
 * ref: https://leetcode.com/problems/word-search/description/
 * ref: https://www.youtube.com/watch?v=m9TrOL1ETxI
 */
public class WordSearch {

    public static void main(String[] args) {
        char[][] board = new char[][]{
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        String word = "ABCCED";
        System.out.println("Word: " + word + ", exists: " + exists(board, word));

        word = "SEE";
        System.out.println("Word: " + word + ", exists: " + exists(board, word));

        word = "ABCB";
        System.out.println("Word: " + word + ", exists: " + exists(board, word));
    }

    static boolean visited [][];
    public static boolean exists(char[][] board, String word){
        int rows = board.length;
        int columns = board[0].length;

        visited = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(word.charAt(i) == board[i][j] && searchWord(i, j, 0, word, board)){
                    return true;
                }
            }
        }
        return false;
    }

    static boolean searchWord(int i, int j, int index,String word,  char[][] board){
        if(index == word.length()){
            return true;
        }

        boolean isADifferentLetter = word.charAt(index) != board[i][j];
        boolean rowIsOutOfBounds = i < 0 || i >= board.length;
        boolean colIsOutOfBounds = j < 0 || j >= board[i].length;
        if(rowIsOutOfBounds || colIsOutOfBounds || isADifferentLetter || visited[i][j]){
            return false;
        }
        visited[i][j] = true;
        if(searchWord(i+1, j, index+1, word, board) ||
                searchWord(i-1, j, index+1, word, board) ||
                searchWord(i, j+1, index+1, word, board) ||
                searchWord(i, j-1, index+1, word, board)
        ){
            return true;
        }

        visited[i][j] = false;
        return false;
    }
}
