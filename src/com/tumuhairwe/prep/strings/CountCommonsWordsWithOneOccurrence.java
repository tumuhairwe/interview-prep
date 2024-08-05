package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2085
 * Count common words with 1 occurence
 */
public class CountCommonsWordsWithOneOccurrence {

    /**
     * Solution summary
     * - Create freqMap of both arrays
     * - find intersection of keys (where freq = 1 in both)
     * - increment count
     * - return count
     */
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> freqMap1 = new HashMap<>();
        for (int i = 0; i < words1.length; i++) {
            freqMap1.put(words1[i], freqMap1.getOrDefault(words1[i], 0)+1);
        }

        //1. create freqMap2
        Map<String, Integer> freqMap2 = new HashMap<>();
        for (int i = 0; i < words2.length; i++) {
            freqMap2.put(words2[i], freqMap2.getOrDefault(words2[i], 0)+1);
        }

        //3. find intersection
        int count = 0;
        for (Map.Entry<String, Integer> entry : freqMap1.entrySet()){
            if(entry.getValue() == 1 && freqMap2.getOrDefault(entry.getKey(), 0) == 1){
                count++;
            }
        }
        return count;
    }
}
