package com.tumuhairwe.prep.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    class TrieNode{
        Map<Character, TrieNode> children;
        String word;
        public TrieNode(){
            children = new HashMap<>();
            word = null;
        }
    }

    List<String> results;
    char[][] _board;

    /**
     * 1. Create TrieNode class + construct a Trie for words[]
     *   a). For each word in words[]
     *      - add each char to trieNode
     * 3. init vars List<String> results && 2D char[])
     * 4. Traverse 2D array, by checking all 4 neighbors when you find char -> call recursively
     * 5. when you find terminalNode where node.word != null, add word to resultList
     * 6. Remember to mark ON_PATH with char & to unset it after returning
     */
    public List<String> findWords(char[][] board, String[] words) {
        //0. construct a Trie for words[]
        TrieNode root = new TrieNode();
        for(String word : words){
            TrieNode node = root;
            for(Character letter : word.toCharArray()){
                if(node.children.containsKey(letter)){
                    node = root.children.get(letter);
                }
                else {
                    TrieNode curr = new TrieNode();
                    node.children.put(letter, curr);
                }

                node = node.children.get(letter);
            }
        }

        // init vars
        this._board = board;
        this.results = new ArrayList<>();

        //1. backtrack on the first char
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Character firstChar = board[row][col];
                if(root.children.containsKey(firstChar)){
                    recursiveDfs(row, col, root);
                }
            }
        }

        //2. return results
        return this.results;
    }

    private void recursiveDfs(int row, int col, TrieNode parent) {
        Character letter = _board[row][col];
        TrieNode currentNode = parent.children.get(letter);

        if(currentNode.word != null){
            results.add(currentNode.word);
            currentNode.word = null;
        }

        //temporarily mark as ON_PATH
        //2. temporarily mark current letter as ON_PATH
        char ON_PATH = '#';
        this._board[row][col] = ON_PATH;

        int[][] offset = {
                {1, 0}, {0, 1},
                {-1, 0}, {0, -1}
        };
        for (int[] dir : offset){
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if(newRow < 0 || newRow >= _board.length || newCol < 0 || newCol >= _board[0].length){
                continue;
            }

            if(currentNode.children.containsKey(letter)){
                recursiveDfs(newRow, newCol, currentNode);
            }
        }

        // unmark ON_PATH
        //2. temporarily mark current letter as ON_PATH
        this._board[row][col] = letter;

        //4. optimization (remove leaf nodes that are empty) ... not required
//        if(currentNode.children.isEmpty()){
//            parent.children.remove(letter);
//        }
    }

    public static void main(String[] args) {

    }
}
