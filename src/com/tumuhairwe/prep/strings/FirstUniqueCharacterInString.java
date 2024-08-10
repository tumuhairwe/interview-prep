package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode 387 (easy)
 *
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1
 *
 * ref: https://leetcode.com/problems/first-unique-character-in-a-string/description/
 */
public class FirstUniqueCharacterInString {

    /**
     * Solution summary
     * - create freqMap of string
     * - iterate string and find the 1st char where frequency == 1
     * - return index
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if(freqMap.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return - 1;
    }
}
