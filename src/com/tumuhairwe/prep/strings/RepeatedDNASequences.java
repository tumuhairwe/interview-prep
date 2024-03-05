package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S, that represents a DNA sequence
 * ... and a number K
 * ... return all the contiguous substrings of length K
 * ... that occur more than 1 in the string
 *
 * ref: https://www.youtube.com/watch?v=RoRRpF3eCH4
 * ref: https://www.educative.io/courses/grokking-coding-interview-patterns-java/repeated-dna-sequences
 */
public class RepeatedDNASequences {
    public static void main(String[] args) {
        String input = "ACGAATTCCG";
        //int windowStart = 0;
        int k = 2;  // length of string
        int windowEnd = k - 1;
        char[] chars = input.toCharArray();

        Map<Character, Integer> occurrences = new HashMap<>();
        for (int windowStart = 0; windowStart < chars.length; windowStart++) {
            //String window = input.substring(windowStart, windowEnd);
            Character ch = Character.valueOf(input.toCharArray()[windowStart]);
            int count = occurrences.getOrDefault(ch, 0);
            occurrences.put(ch, count+1);
        }

        System.out.println("AC=>" + "AC".hashCode());
        System.out.println("CA=>" + "CA".hashCode());
    }
}
