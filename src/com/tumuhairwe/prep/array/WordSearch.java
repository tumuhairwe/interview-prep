package com.tumuhairwe.prep.array;

/**
 * Given a 2D board and a grid, find if the word exists in the board
 *
 * The word can be constructed from letters and sequentially adjacent cells ... where cells are horizontally
 * or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * ref: https://leetcode.com/problems/word-search/description/
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

        boolean isSameLetter = word.charAt(index) != board[i][j];
        boolean rowIsOutOfBounds = i < 0 || i >= board.length;
        boolean colIsOutOfBounds = j < 0 || j >= board[i].length;
        if(rowIsOutOfBounds || colIsOutOfBounds || isSameLetter || visited[i][j]){
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
