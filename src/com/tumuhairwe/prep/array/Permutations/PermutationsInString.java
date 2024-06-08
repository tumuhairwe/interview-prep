package com.tumuhairwe.prep.array.Permutations;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * LeetCode 567 (Medium)
 * Permutation in String
 * Given 2 String s1 and s2, return true if s2 contains a permutation of s1 or false otherwise
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 * SolutionSummary
 * - Declar char_count[] of both s1 & s2 and fill with 0
 * - Build char_count[] of s1 (static char_count[])
 * - Build char_count[] of s2 (sliding_window char_count[]) using a sliding window
 *      - as you build char_count[] of s2, decrement the count of the character exiting the window
 *      - if at any point both char_count[] are equal, return true
 * ref: https://www.youtube.com/watch?v=quSfR-uwkZU
 * ref: https://leetcode.com/problems/permutation-in-string/description/
 */
public class PermutationsInString {
    public static void main(String[] args) {
        System.out.println("Should be true: " + checkInclusion("ab", "eidbaooo"));
        System.out.println("Should be false: " + checkInclusion("ab", "eidboaoo"));
    }
    public static boolean checkInclusion(String s1, String s2){
        //0. base case (if s1/primary).length() _ s2.length() ... its impossible
        if(s1.length() > s2.length()){
            return false;
        }

        // 1a build char_counts of both arrays
        int[] char_count_s1 = new int[26];
        int[] char_count_s2 = new int[26];

        // 1. fill both counts with 0
        Arrays.fill(char_count_s1, 0);
        Arrays.fill(char_count_s2, 0);

        //2. build char_count of s1
        int indexOfLetterA = 'a';
        for (int i = 0; i < s1.length(); i++) {
            int indexOfThisLetter = s1.charAt(i);
            char_count_s1[indexOfThisLetter - indexOfLetterA]++;
        }

        //3. build char_count of s2 with a sliding window ... if at any point the 2 arrays are equal, return true
        for (int i = 0; i < s2.length(); i++) {
            char_count_s2[s2.charAt(i) - indexOfLetterA]++;

            if(i > s2.length()){
                //slide the window forward by removing the char at the beginning
                int indexOfCharAtBeginningOfWindow = s2.charAt(i - s1.length());
                char_count_s2[indexOfCharAtBeginningOfWindow - indexOfLetterA]--;
            }

            if(Arrays.equals(char_count_s1, char_count_s2)){
                return true;
            }
        }

        return false;
    }
}
