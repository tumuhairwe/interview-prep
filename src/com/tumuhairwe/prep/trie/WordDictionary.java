package com.tumuhairwe.prep.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 211 (medium)
 *
 * Design Add and Search words Data structure
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 */
public class WordDictionary {

    /**
     * Solution summary
     * - Create a backing map keyed by integer/length-of-string, value=List_of_string with that length
     * - onAdd() : add the word to by index=length-of-word to the map (value=List<String>
     * - onSearch() : if map doesn't have any word with that  lenghth, return false
     *              : get all Strings of that length from the map and call isMatch() --
     *                  should return true if (char != WILDCARD && word1.char(charIndex) != str.char(wordIndex)
     */
    private Map<Integer, List<String>> dictionary;
    public WordDictionary(){
        dictionary = new HashMap<>();
    }

    public void addWord(String word){
        int length = word.length();
        if(!dictionary.containsKey(length)){
            dictionary.put(length, new ArrayList<>());
        }

        dictionary.get(length).add(word);
    }

    public boolean search(String word){
        int length = word.length();
        if(!dictionary.containsKey(length)){
            return false;
        }

        for (String str : dictionary.get(length)){
            if(isMatch(word, str)){
                return true;
            }
        }

        return false;
    }

    private boolean isMatch(String word, String str) {
        for (int i = 0; i < word.length(); i++) {
            if(str.charAt(i) != '.' && str.charAt(i) != word.charAt(i)){
                return false;
            }
        }

        return true;
    }
}
