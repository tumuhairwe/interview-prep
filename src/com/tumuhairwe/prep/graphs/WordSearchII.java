package com.tumuhairwe.prep.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 212 (hard)
 *
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 */
public class WordSearchII {

    public List<String> findWords(char[][] board, String[] words) {
        int rows = board.length;
        int cols = board[0].length;
        Set<String> results = new HashSet<>();

        wordLoop :
        for(String word: words){
            for(int i =0; i < rows; i++){
                for (int j = 0; j < cols; j++) {
                    if(word.charAt(0) == board[i][j]){
                        boolean[][] visited = new boolean[rows][cols];
                        boolean found = doBFS(board, i, j, word, 0, visited);
                        if (found){
                           results.add(word);
                           break wordLoop;
                        }
                    }
                }
            }
        }

        return new ArrayList<>(results);
    }

    private boolean doBFS(char[][] board, int row, int col, String word, int index, boolean[][] visited) {
        //0. base case
        if(index == word.length()){
            return true;
        }

        //1 check bounds
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length
            || visited[row][col]){
            return false;
        }

        // mark visited
        visited[row][col] = true;
        if(doBFS(board, row + 1, col, word, index+1, visited)
            || doBFS(board, row - 1, col, word, index+1, visited)
            || doBFS(board, row, col + 1, word, index+1, visited)
            || doBFS(board, row , col-1, word, index+1, visited)){
            return true;
        }
        visited[row][col] = false;

        return false;   // it means we were not able to traverse the grid to find the word (found partial word)
    }
}
