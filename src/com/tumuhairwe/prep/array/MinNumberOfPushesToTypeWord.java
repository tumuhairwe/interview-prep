package com.tumuhairwe.prep.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 3016 (medium)
 * Given a word containing lowerCase letters
 *
 * return the minimumNumber pushes needed to type word after remapping the keys.
 * ref: https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii/description/
 * ref: https://www.youtube.com/watch?v=gvaYi6X6SQw
 */
public class MinNumberOfPushesToTypeWord {

    /**
     * Solution summary
     * - create a charCount[] from the word string
     * - convert charCount[] into Integer[] ... sort in descending order
     * - add to result += (frequency * 1 + (number_fo_distinctChar_in_word / number_of_lettered_digits)) i.e. excludes 1    .... "+1" bcoz it might be negative
     * - return result
     *
     * TC: O(n log n) because of reverse sorting. O(n) to iterate the array
     * SC: O(1) for sorting the charCounts[] in alphabet sized [
     */
    public static int minimumPushes(String word){
        //0. create charCount[] of word
        int[] charCount = new int[26];
        char asciiValueOfA = 'a';
        for (char asciiValueOfChar : word.toCharArray()){
            int indexOfLetter = asciiValueOfChar - asciiValueOfA;
            charCount[indexOfLetter]++;
        }

        //1. convert int[] -> Integer[] so you can sort in reverse order
//        Integer[] boxedArr = new Integer[charCount.length];
//        for (int i = 0; i < charCount.length; i++) {
//            boxedArr[i] = charCount[i];
//        }
        Integer[] boxedArr = Arrays.stream(charCount).boxed().toArray(Integer[]::new);
        Arrays.sort(boxedArr, Comparator.reverseOrder());

        //3. calculate result
        int result = 0;
        int numDistinctChars = 0;
        int TOTAL_NUMBER_LETTERED_DIGITS = 8;
        for (int frequency : boxedArr){
            result += frequency * (1 + (numDistinctChars / TOTAL_NUMBER_LETTERED_DIGITS));
            numDistinctChars++;
        }

        return result;
    }
}
