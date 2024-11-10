package com.tumuhairwe.prep.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 648
 * In English, we have a concept called "root", which can be followed by some other word to form a longer word
 * ("derivative"). e.g. "help" == root, derivative = "helpful"
 *
 * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces,
 * replace all the derivatives in the sentence with the root forming it. If a derivative can be replaced
 * by more than one root, replace it with the root that has the shortest length.
 *
 * Return the sentence after the replacement.
 *
 * ref: https://leetcode.com/problems/replace-words/description/
 * ref: https://algo.monster/liteproblems/648
 */
public class ReplaceWords {
    class Trie {
        private Map<Character, Trie> children;
        private int wordIndex;

        public Trie() {
            children = new HashMap<>();
            wordIndex = -1;
        }

        public void insert(String word, int value) {
            Trie root = this;
            for (char ch : word.toCharArray()) {
                if (!root.children.containsKey(ch)) {
                    root.children.put(ch, new Trie());
                }
                root = root.children.get(ch);
            }

            root.wordIndex = value;
        }

        public int search(String word) {
            Trie root = this;
            for (char ch : word.toCharArray()) {
                if (!root.children.containsKey(ch)) {
                    return -1;
                }

                if (root.wordIndex != -1) {
                    return wordIndex;
                }
            }

            return -1;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence){
        Trie dict = new Trie();
        for (int i = 0; i < dictionary.size(); i++){
            dict.insert(dictionary.get(i), i);
        }

        List<String> found = new ArrayList<>();
        String[] words = sentence.split("/");
        for (String w : words){
            int index = dict.search(w);
            if(index == -1){
                found.add(w);
            }
            else {
                found.add(dictionary.get(index));
            }
        }

        return String.join(" ", found);
    }
}
