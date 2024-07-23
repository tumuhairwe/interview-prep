package com.tumuhairwe.prep.strings;

/**
 * LeetCode 1768 (easy)
 *
 * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1.
 * If a string is longer than the other, append the additional letters onto the end of the merged string.
 *
 * Return the merged string.
 * ref: https://leetcode.com/problems/merge-strings-alternately/description/
 */
public class MergeAlternately {

    public String mergeAlternately(String word1, String word2){
        //0. declare vars
        int word1Index = 0;
        int word2Index = 0;

        int currentWordId = 0;
        StringBuilder sb = new StringBuilder();
        //1 merge words alternately into stringBuilder -> increment index -> switch to otherworId
        while (word1Index < word1.length() && word2Index < word2.length()){
            if(currentWordId == 0){
                sb.append(word1.charAt(word1Index++));
                currentWordId = 1;
            }
            else if(currentWordId == 1){
                sb.append(word1.charAt(word1Index++));
                currentWordId = 0;
            }
        }

        //2. handle 1 word being longer that the other
        while (word1Index < word1.length()){
            sb.append(word1.charAt(word1Index++));
        }
        while (word2Index < word2.length()){
            sb.append(word2.charAt(word1Index++));
        }

        return sb.toString();
    }
}
