package com.tumuhairwe.prep.strings;

import java.util.*;

/**
 * LeetCode 3
 * Given a string S, that represents a DNA sequence
 * ... and a number K
 * ... return all the contiguous substrings of length K
 * ... that occur more than 1 in the string
 *
 * ref: https://www.youtube.com/watch?v=RoRRpF3eCH4
 * ref: https://www.educative.io/courses/grokking-coding-interview-patterns-java/repeated-dna-sequences
 */
public class LongestSubstring {

    /**
     * Keep 2 pointers to encompass the biggest valid solution that we can
     */
    public void slidingWindow(){
        int firstPoointer , secondPointer = 0;
        // pointer should include a word that does not invalidate our conditions
    }
    public int lengthOfLongestSubstring(String s){
        Map<Character, Integer> freqMap =new HashMap<>();

        //0. initialize values
        int maxLength = 0;
        List<Character> listOfEncounteredChars = new ArrayList<>();
        for (int left = 0; left < s.length(); left++) {
            char c = s.charAt(left);

            // 1. if char is NOT already encountered -> add to set and calculate max
            if(listOfEncounteredChars.contains(c)){
                // uses List to rack index of char + char itself
                int index = listOfEncounteredChars.indexOf(c);
                listOfEncounteredChars.remove(index);

                // clear all char uup until that point
                if(index > 0){
                    listOfEncounteredChars.subList(0, index).clear();
                }
            }

            //3. add char and update maxLength
            listOfEncounteredChars.add(c);
            maxLength = Math.max(maxLength, listOfEncounteredChars.size());
        }

        return maxLength;
    }
}
