package com.tumuhairwe.prep.trie;

import java.util.HashMap;
import java.util.Map;


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

    // TC = O(1) == where n = length of word
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
            curr = curr.children.get(c);    /// get next in tree
        }
        return curr;
    }

    public boolean startsWith(String prefix){
        TrieNode curr = this.root;
        for(char c : prefix.toCharArray()){
            if(!curr.children.containsKey(c)){
                return false;
            }

            curr.children.get(c);
        }

        return true;
    }
}
