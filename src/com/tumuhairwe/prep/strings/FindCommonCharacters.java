package com.tumuhairwe.prep.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode 1002 (easy)
 *
 * Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order
 * ref: https://www.youtube.com/watch?v=QEESBA2Q_88
 */
public class FindCommonCharacters {

    public List<String> commonChars(String[] words){
        // base case
        if(words.length == 0){
            return List.of();
        }

        //0. init minFrequency array and fill with MAX_VALUE
        int[] minFrequency = new int[26];
        Arrays.fill(minFrequency, Integer.MAX_VALUE);

        //1. calculate th frequency of every word (into same char_freq array)
        for (String w: words){
            int[] currFreq = new int[26];
            for (char c : w.toCharArray()){
                currFreq[c - 'a']++;
            }

            // for each letter, get the min of the intersection between minFreq and currFreq -> assign to minFreq
            for (int i = 0; i < minFrequency.length; i++) {
                minFrequency[i] = Math.min(minFrequency[i], currFreq[i]);
            }
        }

        // convert minFreq into String (repeating the frequencies/occurrences for each char
        List<String> commonChars = new ArrayList<>();
        for (int idx = 0; idx < minFrequency.length; idx++) {
            if(minFrequency[idx] > 0){
                char ch = (char) (idx + 'a');
                List<String> charAsString = Collections.nCopies(minFrequency[idx], ch + "");
                commonChars.addAll(charAsString);
            }
        }

        return commonChars;
    }
}
