package com.tumuhairwe.prep.strings;

import java.util.Map;

/**
 * Given a string, determine if a permutation of the string could form a palindrome
 *
 * Palindrom: word that reads exactly the same forwards and backwards
 * e.g. RACECAR -- has middle character (e)
 * RACCAR -- has no middle character
 *
 * Permutation = re-arrangement of letters in a string
 *
 *
 * Strategy: keep a Map<Character, Integer> map of the string such that a palindrome must have the exact same count of matches
 * iu.e. all characters in the palindrome should have the same count of accurrences
 */
public class PalindromePermutation {
    // number of ascii values = 128
    // number of alphabet values = 26

    // character's int-value == key, number-of-occurrences = value
    static int[] char_counts = new int[128];
    static Map<Character, Integer> charCount;

    public static void main(String[] args) {
        System.out.println("racecar = " + canPermutePalindrome("racecar"));
        System.out.println("raccar = " + canPermutePalindrome("raccar"));
        System.out.println("lion = " + canPermutePalindrome("lion"));
    }

    public static boolean canPermutePalindrome(String s){
        for (int i = 0; i < s.length(); i++) {
            char_counts[s.charAt(i)]++; // will populate the number of times each character occurs
        }

        int count = 0;
        for (int i = 0; i < 128; i++) {
            count += char_counts[i] % 2 ;
        }

        return count <= 1;  // there are either0 characters without a match || exactly 1 character without a match (middle character)
    }
}
