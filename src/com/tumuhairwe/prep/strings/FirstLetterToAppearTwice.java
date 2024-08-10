package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode 2351 (easy)
 *
 * ref: https://leetcode.com/problems/first-letter-to-appear-twice/description/
 */
public class FirstLetterToAppearTwice {

    /**
     * Solution summary
     * - Create freqMap of str
     * - while creating freqMap, if char's frequency >= 2 ... return its index
     *
     * SC: O(1) because freqMap.size == 26
     * TC: O(n) == where n == length of string
     */
    public char repeatedCharacter(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i), 0) + 1);

            if(freqMap.get(s.charAt(i)) >= 2){
                return s.charAt(i);
            }
        }
        return ' ';
    }
}
