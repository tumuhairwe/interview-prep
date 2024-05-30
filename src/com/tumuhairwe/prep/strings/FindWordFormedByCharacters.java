package com.tumuhairwe.prep.strings;

/**
 * LeetCode 1160 (easy)
 *
 * Given an array of Strings (words) and a string (str),
 * return the SUM OF LENGTHS of all good strings in words
 *
 * A string is good if it can be formed by characters from str (each  character
 * can only be used once)
 *
 * ref: https://www.youtube.com/watch?v=EQ5jTZdEn8Y
 * ref: https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/description/
 */
public class FindWordFormedByCharacters {
    public static void main(String[] args) {
        String[] arr = new String[]{"cat","bt","hat","tree"};
        String chars = "atach";

        System.out.println("Should be 6 " + countCharacters(arr, chars));
    }

    /**
     * Solution Summary
     * - Convert global_word to wordIntArray
     * - For each local_word,
     *      - convert into wordIntArray
     *      - ensure char-frequency in local_wordIntArray is <= char_frequency in global_wordIntArr
     *      - If all chars in local_word exist global_word's wordIntArray word is good
     *      - if word is bad, break
     *      - for each good word, add word.length() to totalCount
     */
    public static int countCharacters(String[] words, String str){
        // 1. convert str into int[] where value == num-occurrences of each character
        int[] arrayOfChars = convertWordIntoIntArray(str);

        // 2 for each word, convert to int[] and find intersection with arrayOfChars[]
        int totalCount = 0;
        for (String word : words){
            boolean isGoodWord = true;
            int[] wordIntArray = convertWordIntoIntArray(word);

            // if a character exists in wordInt[] but not in arrayOfChars ... mark isGoodWord as false
            for (int i = 0; i < 26; i++) {
                if(wordIntArray[i] > arrayOfChars[i]){
                    isGoodWord = false;
                    break;
                }
            }

            // if isGoodWord, add length of word to result
            if(isGoodWord){
                totalCount += word.length();
            }
        }

        return totalCount;
    }

    static int[] convertWordIntoIntArray(String s){
        int indexOfLetterA = 'a';
        int[] arrayOfChars = new int[26];
        for (int i = 0; i < s.toCharArray().length; i++) {
            int indexOfLetter = s.charAt(i) - indexOfLetterA;
            arrayOfChars[indexOfLetter]++;
        }

        return arrayOfChars;
    }
}
