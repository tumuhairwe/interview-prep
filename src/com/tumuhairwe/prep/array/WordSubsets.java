package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given 2 arrays A and B ... where A=array_of_words and b=array_of_characters
 * Find the elements in A that contain every letter in B (including multiplicity)
 *
 * e.g. A = ["amazon", "apple", "facebook", "google", "leetcode"]
 * and  B = ["e", "o"]
 * output = ["facebook", "google", "leetcode"]
 *
 * i.e. find the entries in A where every letter B occurs in A
 * (including multiplicity = "wrr" is a subset of "warrior" but is not a subset of "world"  i.e.
 * all the letters in B must in A.
 *
 * Summary:
 *  - Compile char_frequency array of each word in B[]
 *  - Compile char_frequency array of each word in A
 *  - For each word in A, check if its char_frequency is atLeast B's merged/combined char_frequency
 * Order of returned words does not matter
 * ref: https://leetcode.com/problems/word-subsets
 * ref: https://www.youtube.com/watch?v=tvioNeOXRUg
 */
public class WordSubsets {
    public static void main(String[] args) {
        String[] A = new String[]{"amazon", "apple", "facebook", "google", "leetcode"};
        String[] B = new String[]{"e", "o"};

        System.out.println("A is -> ");
        char a = 'A';
        System.out.println('A');
        System.out.println("B is -> ");
        System.out.println('B');
        List<String> result = generateWordSubset(A, B);
        System.out.println(Arrays.toString(result.toArray()));
    }
    public static List<String> generateWordSubset(String[] A, String[] B){
        List<String> result = new ArrayList<>();

        // strategy:
        // For each word in A
        // 1. generate char_frequency array
        // 2. For each character in char_frequency, if the A[frequency] is > B[frequency]
        // consider it a valid words

        int[] max_b_frequencies = new int[26];  // numOfChars in alphabet i.e. constant
        // 1) iterate over B & populate max_b_frequencies[]
        for (int i = 0; i < B.length; i++) {
            String current_word = B[i];
            int[] charFreq_of_currentWord = get_char_frequency(current_word);

            for (int j = 0; j < 26; j++) {  // complexity = O(M + N) => M = B.length and N = 26 i.e. size of alphabet which ins constant
                max_b_frequencies[j] = Math.max(max_b_frequencies[j], charFreq_of_currentWord[j]);
            }
        }

        // 2) iterate of A &  compare max_a_frequencies with max_b_frequencies
        for (int i = 0; i < A.length; i++) {
            String currentWord = A[i];
            // a) build char_frequency array of word in A
            int[] max_a_frequencyCount = get_char_frequency(currentWord);

            // b) examine currentWord to make sure each letter's frequency/count is <= the index in GLOBAL_CHAR_FREQUENCY
            boolean isValid = true;
            for (int j = 0; j < 26; j++) {
                // B has a character that A does not have (.e. if max_b[char] == 1 and max_a[a] == 0
                if(max_b_frequencies[j] > max_a_frequencyCount[j]){
                    isValid = false;
                    break;
                }
            }

            if(isValid){
                result.add(currentWord);    // currentWord has every char present in A (with the <= frequency)
            }
        }

        return result;
    }

    /**
     * Generated max character frequencies of a given word so that e.g. ABCCC = [1, 1, 3]
     * @param current_word e.g. ABACUS
     * @return = [2,1,1, ...indexOfU=1 .... indexOfS=1]
     */
    private static int[] get_char_frequency(String current_word) {
        int[] result = new int[26];
        final int asciiValueOfA = 'a';
        for (char asciiValueOfCharacter : current_word.toCharArray()){

            int indexOfLetter = asciiValueOfCharacter - asciiValueOfA;
            result[indexOfLetter]++;  //  c - 'a' => int value of char 1 < val < 26

            // indexOfLetter = a = 0
            // indexOfLetter = b = 1
            // indexOfLetter = 2 = 3
        }
        return result;
    }
}
