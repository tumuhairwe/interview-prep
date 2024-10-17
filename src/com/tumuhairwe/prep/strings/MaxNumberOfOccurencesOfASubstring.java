package com.tumuhairwe.prep.strings;

import java.util.*;

/**
 * LeetCode 1297 (medium)
 * Given a string s, return the max num of occurrences of any substring under the following rules
 * - The number of unique chars in the substring must be less than or equals to maxLetters
 * - The substring size must be between minSize and maxSize inclusive
 */
public class MaxNumberOfOccurencesOfASubstring {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        //0. base case
        if(s == null || s.length() == 0 || maxSize == 0){
            return 0;
        }

        //1. create freqMap of susbstrings of s
        int maxLength = 0;
        Map<String, Integer> freqMap = new HashMap<>();
        for(int i=0; i<s.length() - minSize +1; i++){
            // 2. get substring of length minSize
            String curr = s.substring(i, i+minSize);

            // 3. if substring is within bounds, add to freqMap && update maxLength
            if(getUniqueCharCount(curr) <= maxLetters){
                freqMap.put(curr, freqMap.getOrDefault(curr, 0) + 1);
                maxLength = Math.min(maxLength, freqMap.get(curr));
            }
        }

        return maxLength;
    }

    private int getUniqueCharCount(String s) {
        Set<Character> chars = new HashSet<>();
        for(char ch : s.toCharArray()){
            chars.add(ch);
        }

        return chars.size();
    }
}
