package com.tumuhairwe.prep.strings;

import java.util.*;

/**
 * Given a string S, that represents a DNA sequence
 * ... and a number K
 * ... return all the contiguous substrings of length K
 * ... that occur more than 1 in the string
 *
 * Solution:
 * - Iterate over all k-length substring (windowStart = 0, windowEnd = k)
 * - Store the hash of the current substring str.substring(windowStart, windowEnd)
 * - Check the Map<Hash, String> and if the hash has been calculated already, add to result-set
 * - Walk the whole string and when all substrings have been evaluated, return result-set
 *
 *  Time Complexity = O(n) -- where n == length of string
 *  Space complexity = O(n) where n ==n number of substrings that can be derived from string
 *  
 * ref: https://www.youtube.com/watch?v=RoRRpF3eCH4
 * ref: https://www.educative.io/courses/grokking-coding-interview-patterns-java/repeated-dna-sequences
 */
public class RepeatedDNASequences {
    public static void main(String[] args) {
        //String input = "ACGAATTCCG";
        String str = "AGCTGAAAGCTTAGCTG";
        Set<String> results = getSubstringsOccurringMoreThanK(5, str);
        System.out.println("There are " + results.size() + " string that occur more than once in " + str);
        System.out.println(results);
    }

    static Set<String> getSubstringsOccurringMoreThanK(int length, String dnaString){
        int windowEnd = length;
        Map<Integer, Integer> mapOfHashes = new HashMap<>();
        Set<String> stringsOccurringMoreThanOnce = new HashSet<>();

        for (int windowStart=0; windowEnd<dnaString.length()+1; windowEnd++){
            String substring = dnaString.substring(windowStart, windowEnd);

            int numOccurrences = mapOfHashes.getOrDefault(substring.hashCode(), 0) + 1;
            mapOfHashes.put(substring.hashCode(), numOccurrences);
            System.out.println("String=" + substring + " hasCode=" + substring.hashCode() + " numOccurrences=" + numOccurrences);

            if(numOccurrences > 1){
                stringsOccurringMoreThanOnce.add(substring);
            }
            windowStart++;
        }

        return stringsOccurringMoreThanOnce;
    }
}
