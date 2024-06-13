package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * LeetCode 387 (easy)
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 *
 * ref: https://leetcode.com/problems/first-unique-character-in-a-string/description/
 */
public class FirstUniqueChar {

    public int firstUniqChar_using_charCounts(String s){
        //0. create char_counts array
        int[] char_counts = new int[26];
        int indexOfLetterA = 'a';

        for (char c : s.toCharArray()){
            int indexOfThisLetter = c- indexOfLetterA;
            char_counts[indexOfThisLetter]++;
        }

        //1. find first occuring char with count == 1;
        for (int index = 0; index < s.toCharArray().length; index++) {
            int indexOfThisLetter = s.charAt(index) - indexOfLetterA;
            if(char_counts[indexOfThisLetter] == 1){
                return index;
            }
        }

        return -1;
    }

    public int firstUniqChar_using_freqMap(String s){
        //0. create frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (Character c : s.toCharArray()){
            int existingCount = freqMap.getOrDefault(c, 0);
            freqMap.put(c, existingCount + 1);
        }

        //1. filter unique chars
        Optional<Character> ch = freqMap.entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> e.getKey())
                .findFirst();

        for (int index = 0; index < s.toCharArray().length; index++) {
            if(freqMap.get(s.charAt(index)) == 1){
                return index;
            }
        }

        return -1;
    }
}
