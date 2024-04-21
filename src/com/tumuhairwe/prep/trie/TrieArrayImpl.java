package com.tumuhairwe.prep.trie;

/**
 * @Deprecated: Prefer Map-based Trie implementation
 */
public class TrieArrayImpl {

    class TrieNode {
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

    private TrieNode root;
    public TrieArrayImpl(){
        root = new TrieNode();
    }
    // function to get the index of a character x
    public int getIndex(char x){
        return x - 'a'; // the index is based on the position of the character in the alphabet
    }

    // function to insert a key into the trie
    // TC = O(l) == l = the string to insert
    public void insert(String key){
        if(key == null){
            System.err.println("Null keys can not be inserted");
        }

        key = key.toLowerCase();
        TrieNode currentNode = this.root;
        int index = 0;

        // iterate the trie with the given characters index
        // if null, then simply create a TrieNode and go down a level
        for(int level = 0; level < key.length(); level++){
            index = getIndex(key.charAt(level));
            if(currentNode.children[index] == null){
                currentNode.children[index] = new TrieNode();
            }
            currentNode = currentNode.children[index];
        }

        currentNode.unMarkAsLeaf();
    }

    // TC: O(l) -- l == length of
    public boolean search(String prefix){
        if(prefix == null){
            return false;
        }

        prefix = prefix.toLowerCase();
        TrieNode currentNode = this.root;
        int index = 0;

        // iterate the trie with the given character index
        // if its null @ any point, stop and return false;
        // if we reach endNode/have traversed the entire array (length of prefix) ... return isEndOfWord flag at last node
        for(int level = 0; level < prefix.length(); level++){
            index = getIndex(prefix.charAt(level));
            if(currentNode.children[index] == null){
                return false;
            }
            currentNode = currentNode.children[index];
        }

        return currentNode.isEndWord;
    }

    public void delete(String key){
        if(root == null || key == null){
            System.err.println("Null key or empty trie error");
            return;
        }

        deleteHelper(key, root, key.length(), 0);
    }
    private boolean hasNoChildren(TrieNode currentNode){
        for (int i = 0; i < currentNode.children.length; i++) {
            if(currentNode.children[i] != null){
                return false;
            }
        }
        return true;
    }
    private boolean deleteHelper(String key, TrieNode currentNode, int length, int level){
        boolean deletedSelf = false;

        if(currentNode == null){
            System.err.println("Key does not exist");
            return deletedSelf;
        }
        else {
            currentNode.unMarkAsLeaf();;
            deletedSelf = false;
        }

        // base case: we've reached the node which points to the alphabet at the end of the key
        if(level == length){
            if(hasNoChildren(currentNode)){
                currentNode = null;
                deletedSelf = true;
            }
        }

        return deletedSelf;
    }
}
