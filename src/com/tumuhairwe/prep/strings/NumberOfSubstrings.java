package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2083(medium)
 * Substring that begin and end with the same character
 */
public class NumberOfSubstrings {
    /**
     * Solution summary
     *
     * - create array based frequency counter of all chars in String as you iterate thru the entire string
     * - for each character, add its count to the globalCount/result
     *
     * SC: O(n)
     * TC: O(1) because freqCount = 26 at max
     */
    public long numberOfSubstrings(String s) {
        int result = 0;
        Map<Character, Integer> freqMap = new HashMap<>();

        char[] chars = s.toCharArray();
        for(char c : chars){
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);

            //2. pair current letter with all occurrences of it that came before (to form a valid substring)
            result += freqMap.get(c);
        }
        return result;
    }
}
