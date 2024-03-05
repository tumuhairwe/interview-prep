package com.tumuhairwe.prep.trie;

public class TrieNode {
    TrieNode[] children;
    boolean isEndWord;
    public static final int ALPHABET_SIZE = 26;

    public TrieNode(){
        this.isEndWord = false;
        this.children = new TrieNode[ALPHABET_SIZE];
    }
    public void markAsEndOfWord(){
        this.isEndWord = true;
    }

    public void unMarkAsLeaf() {
        isEndWord = false;
    }
}
