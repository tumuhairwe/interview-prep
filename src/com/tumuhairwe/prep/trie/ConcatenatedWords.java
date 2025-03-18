package com.tumuhairwe.prep.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * LeetCode 472 (hard)
 * Given an array of strings (words) without dupes, return all concatenated words in the given list of words
 * A concatenated word is defined as a string that is comprised entirely of at least 2
 * shorter words (not necessarily distinct) in the given array
 *
 * ref: https://leetcode.com/problems/concatenated-words/description/
 * ref: https://algo.monster/liteproblems/472
 */
public class ConcatenatedWords {
    private class Trie{
        private Trie[] children = new Trie[26];
        private boolean isEndOfWord;
        //TC: O(L) where l == word.length()
        public void insert(String word){
            Trie node = this;
            for(char ch : word.toCharArray()){
                int indexOfCh = ch - 'a';
                if(node.children[indexOfCh] == null){
                    node.children[indexOfCh] = new Trie();
                }
                node = node.children[indexOfCh];
            }
            node.isEndOfWord = true;
        }
    }

    /**
     * TC:
     */
    private Trie root;
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        //0. declare var
        root = new Trie();

        //1. sort words by length (sort words come first so its easier to find repeated words)
        //Arrays.sort(words, (a, b) -> a.length() - b.length());    // same as below
        Arrays.sort(words, Comparator.comparingInt(String::length));    // TC: O(n * log_n)

        List<String> result = new ArrayList<>();
        for(String word : words){
            if(isConcatenated(word)){
                result.add(word);
            }
            else {
                root.insert(word);
            }
        }
        return result;
    }

    /**
     * TC (for recursive DFS): O(L^2) where L == str.length()
     * TC: sorting => n log n (where n == number of words)
     *   DFS => n * L^2 (where l = avg length of words)
     *
     *   i.e. (n * log*n + n * L^2)
     *   SC: O(T) for the Trie (where t == number of characters across all words)
     *       O(n) number of words (could be all unique)
     *     => O(T + N)
     */
    public boolean isConcatenated(String word){
        // base case: word is empty string
        if(word.length() == 0){
            return true;
        }

        //1. start from root of Trie, traverse string char by char
        Trie node = this.root;
        for (int i = 0; i < word.length(); i++) {
            int indexOfCh = word.charAt(i) - 'a';

            // if there's no path, ifs not a concatenated word
            if(node.children[indexOfCh] == null){
                return false;
            }

            // move to next char
            node = node.children[indexOfCh];

            if(node.isEndOfWord && isConcatenated(word.substring(i + 1))){
                return true;    // word IS a concatenated word
            }
        }

        return false;   // is NOT a concatenated word
    }
}
