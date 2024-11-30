package com.tumuhairwe.prep.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 187 (medium)
 *
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 *
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 *
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings)
 * that occur more than once in a DNA molecule. You may return the answer in any order.
 *
 * ref: https://leetcode.com/problems/repeated-dna-sequences/description/
 * ref: https://www.youtube.com/watch?v=FzTYfsmtOso
 */
public class RepeatedDnaSequence {

    public static void main(String[] args) {
        String s = "AAAAAAAAAA";
        List<String> result = findRepeatedDnaSequences(s);
        System.out.println(result);
    }

    /**
     * Solution summary
     * - traverse dna string from index=10 with 2 pointers (start=0, end=9)
     * - if DNA substring is contained in global Set<String> ... add to resultList
     * - add to globalSet
     * - increment both pointers
     * - return resultList
     * 
     * TC: O(n)
     * SC: O(n)
     */
    public static List<String> findRepeatedDnaSequences(String s) {
        //0. init vars
        Set<String> seenBefore = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        //1. traverse string with 2 pointers
        int DNA_LENGTH = 10;
        for(int i = 0; i <= s.length()-DNA_LENGTH; i++){
            String dna = s.substring(i, i + DNA_LENGTH);   // startIndex is inclusive, but endIndex is exclusive

            if(seenBefore.contains(dna)){
                repeated.add(dna);    // sets don't have duplicates
            }
            else {
                seenBefore.add(dna);
            }
        }

        return new ArrayList<>(repeated);
    }

    /**
     * Solution summary
     * - Use sliding window to create frequency map of each 10 character string
     * - return each DNA-string/entry that has a frequency of more than 1
     */
    public static List<String> findRepeatedDnaSequences_map_based(String s){
        //0. init vars
        Map<String, Integer> freqCount = new HashMap<>();   // key = dna_string, val = frequency

        //1. traverse string with 2 pointers
        int start = 0;
        for(int end = 10; end <= s.length(); end++){
            String dna = s.substring(start, end);

            int existingCount = freqCount.getOrDefault(dna, 0);
            freqCount.put(dna, existingCount + 1);
            start++;
        }

        List<String> dnaThatOccursMoreThanOnce = freqCount.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        return dnaThatOccursMoreThanOnce;
    }
}
