package com.tumuhairwe.prep.strings;

/**
 * LeetCode 2083(medium)
 * Substring that begin and end with the same character
 */
public class NumberOfSubstrings {
    /**
     * Solution summary
     *
     * - create array based frequency counter of all chars in String as you interate thru the entire string
     * - for each characters, add its count to the globalCount/result
     *
     * SC: O(n)
     * TC: O(1) because freqCount = 26 at max
     */
    public long numberOfSubstrings(String s) {
        int result = 0;
        long[] freqCount = new long[26];

        char[] chars = s.toCharArray();
        for(char c : chars){
            freqCount[c - 'a']++;

            // Current letter can be paired with all the occurrences of it that
            // comes before, including itself, to form a valid
            result += freqCount[c - 'a'];
        }
        return result;
    }
}
