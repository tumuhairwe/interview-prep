package com.tumuhairwe.prep.strings;

import java.util.List;

/**
 * LeetCode 345 (easy)
 *
 * Given a string s, reverse only all the vowels in the string and return it.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 */
public class ReverseVowels {
    static List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) {
        System.out.println(reverseVowels("leetcode"));
        System.out.println(reverseVowels("holle"));
    }

    /**
     * Solution Summary
     * - Iterate string with 2 pointers
     * - inner-loop 1: while char at p1 is not a vowel, increment p1 index
     * - inner-loop 2: while char at p1 is not a vowel, decrement p2 index
     * - if p1 < p2 - swap the chars
     * - increment p1 && decrement p2;
     */
    public static String reverseVowels(String s){
        int p1 = 0;
        int p2 = s.length() - 1;
        char[] original = s.toCharArray();

        while (p1 < p2){
            while (p1 < p2 && !vowels.contains(Character.toLowerCase(s.charAt(p1)))){
                p1++;
            }
            while (p2 >= 0 && !vowels.contains(Character.toLowerCase(s.charAt(p2)))){
                p2--;
            }

            if(p1 < p2){
                swap(original, p1, p2);
            }

            p1++;
            p2--;
        }
        return new String(original);
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i]= chars[j];
        chars[j] = temp;
    }


}
