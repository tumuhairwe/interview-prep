package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 734 (easy)
 * Given 2 sentences s1 and s2 represented as a string[]...
 * and given a string of pairs sPairs (where sPair[i] = [x, y] indicates that the 2 words
 * x and y are similar,
 * return true if s1 and s2 are similar or false otherwise
 *
 * ref: transitive NOT allowed
 * ref: ref: https://leetcode.com/problems/sentence-similarity/solutions/109633/java-super-clean-code-similarity-i-and-ii/
 */
public class SentenceSimilarity {

    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        //0. check that lengths match
        if(sentence1.length != sentence2.length){
            return false;
        }

        //1. create adjList
        Map<String, List<String>> dictionary = new HashMap<>();
        for(List<String> pair : similarPairs){
            String word1 = pair.get(0);
            String word2 = pair.get(1);

            dictionary.putIfAbsent(word1, new ArrayList<>());
            dictionary.putIfAbsent(word2, new ArrayList<>());

            dictionary.get(word1).add(word2);
            dictionary.get(word2).add(word1);
        }

        for (int i = 0; i < sentence1.length; i++) {
            String word1 = sentence1[i];
            String word2 = sentence2[i];

            // is same word -> continue
            if(word1.equals(word2)){
                continue;
            }

            // word1 is missing entirely -> false
            if(!dictionary.containsKey(word1)){
                return false;
            }

            // do NOT form a pair (according to dictionary) -> false
            if(!dictionary.get(word1).contains(word2)){
                return false;
            }
        }
        return true;
    }
}
