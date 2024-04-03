package com.tumuhairwe.prep.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Reorganize words in a string so that no 2 characters are next to each othger
 *
 * ref: https://www.educative.io/courses/grokking-coding-interview-patterns-java/solution-reorganize-string
 */
public class ReorganizeWords {
    PriorityQueue<int[][]> twoDqueue = new PriorityQueue<>();

    // TC
    //1. Iterate: TC = O(n) ... bcoz we have to iterate over the whole string to get each character (N == length of string)
    //2. push: O(log c) where c == num_characters in string
    // 1 + 2 = O(n Log_c)
    // but ince upper-bound of c = 26=SIZE_OF_ALPHABET == constact
    // we can summarize that overall TC = O(n)
    // SC
    // HashMap is responsible for storing solution of charFrequency
    // The max capacity fir each data structure = 26 == constants
    // therefor the space complexity = O(1)
    public static String reOrganizeWords(String text){
        // 0. chreate a char-frequency-map
        Map<Character, Integer> charFreMap = new HashMap<>();
        for(char c : text.toCharArray()){
            int newFreq = charFreMap.getOrDefault(c, 0) + 1;
            charFreMap.put(c, newFreq);
        }

        // 1. Create maxHeap -- PQ that order Map.Entry by frequency/value
        Comparator<Map.Entry<Character, Integer>> c = Comparator.comparingInt(Map.Entry::getValue);
        PriorityQueue<Map.Entry<Character, Integer>> maxCharFreqPQ = new PriorityQueue<>(c);

        new ArrayList<>(maxCharFreqPQ);
        // 2. add all frequency-count entries
        maxCharFreqPQ.addAll(charFreMap.entrySet());

        // 3. build string based on order of frequency
        Map.Entry<Character, Integer> previous = null;
        StringBuilder sb = new StringBuilder(text.length());

        while (!maxCharFreqPQ.isEmpty() || previous != null){
            if(previous != null && maxCharFreqPQ.isEmpty()){
                return "";
            }

            Map.Entry<Character, Integer> currentEntry = maxCharFreqPQ.poll();
            Integer count = currentEntry.getValue() - 1;
            sb.append(currentEntry.getKey());

            if(previous !=null){
                maxCharFreqPQ.add(previous);
                previous = null;
            }

            if(count != 0){
                previous = new AbstractMap.SimpleEntry<>(currentEntry.getKey(), count);
                //previous = Map.Entry.copyOf(currentEntry.getKey(), count);
            }
        }

        return sb.toString();
    }

}
