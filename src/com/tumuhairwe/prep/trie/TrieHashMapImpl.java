package com.tumuhairwe.prep.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * ref: LeetCode 208 (medium)
 *
 * Implement Trie (prefix trie)
 *
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 */
public class TrieHashMapImpl {

    class TrieNode {
        boolean endOfWord;
        Map<Character, TrieNode> children = new HashMap<>();
    }

    TrieNode root;
    public TrieHashMapImpl(){
        root = new TrieNode();
    }

    // TC = O(1) == where n = length of word
    public void insert(String word){
        TrieNode curr = this.root;

        for (char c : word.toCharArray()){
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }

            // so that by the time loop ends, curr is the last node/char
            curr = curr.children.get(c);
        }

        curr.endOfWord = true;   // mark end-of-word
    }

    // TC = O(n) == where n = length of word
    // SC: O(W * L): where W == number of works and L = avg length of word
    public boolean search(String word){
        TrieNode result = getLast(word);
        return (result != null && result.endOfWord);
    }

    TrieNode getLast(String word){
        TrieNode curr = root;
        for (char c : word.toCharArray()){
            if(!curr.children.containsKey(c)){
                return null;
            }
            curr = curr.children.get(c);    // get next in tree
        }
        return curr;
    }

    //TC: O(m) where m == length of prefix
    //SC: O(W * L): where W == number of works and L = avg length of word
    public boolean startsWith(String prefix){
        TrieNode curr = this.root;
        for(char c : prefix.toCharArray()){
            if(!curr.children.containsKey(c)){
                return false;
            }

            curr = curr.children.get(c);
        }

        return true;
    }
}
