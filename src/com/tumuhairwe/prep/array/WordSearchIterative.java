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
 *  - create a nest for loop to loop over the 2D array
 *  - only start when you meet the 1st character of the word (i.e. word.charAt(i) == board[i][j] )
 *
 *  - create a isExists() function that traverses the whole 2D array, cell by cell, if exists, return true
 *  - create a searchWord(int i, int j, int indexOfCurrentChar, String word, int[][] board) function that calls itself
 *      - add cell to be visited 2D array (to tracked visited nodes)
 *      - add boundary checks
 *          - (x-out-of-bound,
 *          - y-out-of-bound,
 *          - is-already-visited, and
 *          - is-a-diff-letter) i.e. word.charAt(indexOfCurrentChar) != board[i][j];
 *      - call recursively passing <i>indexOfCurrentChar + 1</i>, and all 4 neighboring cells (2 horizontal & 2 vertical)
 *      - recursive call should return true if we've reached end-of-word
 *
 * NOTE: This is an iterative solution --> NOT backtracking
 *
 * ref: https://leetcode.com/problems/word-search/description/
 * ref: https://www.youtube.com/watch?v=m9TrOL1ETxI
 */
public class WordSearchIterative {

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
        final int BEGINNING_INDEX_WORD = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // word can start from any index, so we check if char_at_index_0 is at any cell
                boolean foundTheFirstLetter = word.charAt(i) == board[i][j];
                if(foundTheFirstLetter && searchWord(i, j, BEGINNING_INDEX_WORD, word, board)){
                    return true;
                }
            }
        }
        return false;
    }

    static boolean searchWord(int i, int j, int index,String word,  char[][] board){
        if(index == word.length()){ // base case -> if we have reached end of word
            return true;
        }

        boolean isADifferentLetter = word.charAt(index) != board[i][j];
        boolean rowIsOutOfBounds = i < 0 || i >= board.length;
        boolean colIsOutOfBounds = j < 0 || j >= board[i].length;
        boolean isAlreadyVisited = visited[i][j];   // i.e. currently in the path we are traversing
        if(rowIsOutOfBounds || colIsOutOfBounds || isADifferentLetter || isAlreadyVisited){
            return false;
        }
        visited[i][j] = true;
        if(searchWord(i+1, j, index+1, word, board) ||
                searchWord(i-1, j, index+1, word, board) ||
                searchWord(i, j+1, index+1, word, board) ||
                searchWord(i, j-1, index+1, word, board)
        ){
            return true;    // we've recursively searched and reached the end of the word
        }

        visited[i][j] = false;
        return false;
    }
}
