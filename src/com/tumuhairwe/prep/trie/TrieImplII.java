package com.tumuhairwe.prep.trie;

/**
 * LeetCode 1804 (medium)
 * A trie or prefix tree is a data structure used to efficiently store and retrieve keys in a dataset of strings
 * There are various applications e.g. autocomplete and spellchecker
 *
 * Implement the Trie class:
 * - Trie() - initializes the trie object
 * void insert(String word) - inserts the string word into the table
 * int countWordsEqualTo(String word) - returns the number of instances of the string word in the trie
 * int countWordsStartingWith(String prefix) - return the number of string in the trie that have the prefix
 * void erase(String word) - Erase the string word from the trie;
 *
 * ref: https://leetcode.com/problems/implement-trie-ii-prefix-tree/description/
 */
public class TrieImplII {
    private TrieNode root;

    public TrieImplII(){
        root = new TrieNode();
    }

    void insert(String word){
        TrieNode curr = root;
        for(char ch : word.toCharArray()){
            int charVal = ch - 'a';
            if(curr.children[charVal] == null){
                curr.children[charVal] = new TrieNode();
            }

            curr = curr.children[charVal];
            curr.prefixCount++;
        }

        curr.wordsEndingHere++;
    }

    public int countWordsEqualTo(String word){
        TrieNode curr = root;
        for(char ch : word.toCharArray()){
            int charVal = ch - 'a';
            if(curr.children[charVal] == null){
                return 0;
            }

            curr = curr.children[charVal];
        }

        return curr.wordsEndingHere;
    }

    public int countWordsStartingWith(String word){
        TrieNode curr = root;
        for(char ch : word.toCharArray()){
            int charVal = ch - 'a';
            if(curr.children[charVal] == null){
                return 0;
            }

            curr = curr.children[charVal];
        }

        return curr.prefixCount;
    }

    public void erase(String word){
        TrieNode curr = root;
        for(char ch : word.toCharArray()){
            int charVal = ch - 'a';
            curr = curr.children[charVal];
            curr.prefixCount--;
        }

        curr.wordsEndingHere--;
    }
}

class TrieNode{
    TrieNode[] children;
    int prefixCount;
    int wordsEndingHere;
    public TrieNode(){
        this.children = new TrieNode[26];
    }
}
