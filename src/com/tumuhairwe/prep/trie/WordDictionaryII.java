package com.tumuhairwe.prep.trie;

public class WordDictionaryII {
    class TrieNode{
        Character charValue;
        TrieNode[] children;
        boolean isEndWord;

        public TrieNode(char ch){
            charValue = ch;
            children = new TrieNode[26];
            isEndWord = false;
        }
    }

    TrieNode root ;
    public WordDictionaryII(){
        root = new TrieNode('\0');
    }

    public void addWord(String word){
        TrieNode curr = root;
        for (char ch : word.toCharArray()){
            int idx = ch - 'a';
            if(curr.children[idx] == null){
                curr.children[idx] = new TrieNode(ch);
            }

            curr = curr.children[idx];
        }

        curr.isEndWord = true;
    }

    public boolean search(String word){
        return searchNode(word, root, 0);
    }

    private boolean searchNode(String word, TrieNode curr, int index){
        for (int i = index; i < word.length(); i++) {
            char ch = word.charAt(i);

            if(ch == '.'){
                // check all children until we find string.substring(index+1) == child
                for (TrieNode child : curr.children){
                    if(child != null && searchNode(word, child, i + 1)){
                        return true;
                    }
                }

                // if we get here, we've searched all the children (skipping 1 wildcard) and found no matc
                return false;
            }

            // non-wildcard character doesn't exist
            int idx = ch - 'a';
            if(curr.children[idx] == null){
                return false;
            }

            // else it exists, forward to next char
            curr = curr.children[idx];
        }
        return curr.isEndWord;
    }
}
