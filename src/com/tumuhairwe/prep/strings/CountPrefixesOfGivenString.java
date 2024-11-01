package com.tumuhairwe.prep.strings;

/**
 * LeetCode 2255 (easyt\)
 * ou are given a string array words and a string s, where words[i] and s comprise only of lowercase English letters.
 *
 * Return the number of strings in words that are a prefix of s.
 *
 * A prefix of a string is a substring that occurs at the beginning of the string. A substring is a contiguous sequence of characters within a string.
 *
 * ref: https://leetcode.com/problems/count-prefixes-of-a-given-string/
 */
public class CountPrefixesOfGivenString {
    //TC: O(n)
    public int countPrefixes(String[] words, String s) {
        int count = 0;
        for(String w : words){
            boolean startsWith = s.startsWith(w);
            if(startsWith){
                count++;
            }
        }
        return count;
    }
}
