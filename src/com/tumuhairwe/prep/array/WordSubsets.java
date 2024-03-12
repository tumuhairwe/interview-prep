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
 * Order of returned words does not matter
 * ref: https://leetcode.com/problems/word-subsets
 */
public class WordSubsets {
    public static void main(String[] args) {
        String[] A = new String[]{"amazon", "apple", "facebook", "google", "leetcode"};
        String[] B = new String[]{"e", "o"};

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
            int[] char_frequencies = get_char_frequency(current_word);

            for (int j = 0; j < 26; j++) {  // complexity = O(M x N) => M = B.length and N = 26 i.e. size of alphabet which ins constant
                max_b_frequencies[j] = Math.max(max_b_frequencies[j], char_frequencies[j]);
            }
        }

        // 2) iterate of A &  compare max_a_frequencies with max_b_frequencies
        for (int i = 0; i < A.length; i++) {
            String currentWord = A[i];
            int[] max_a_frequences = get_char_frequency(currentWord);

            boolean isValid = true;
            for (int j = 0; j < 26; j++) {
                if(max_b_frequencies[j] > max_a_frequences[j]){
                    isValid = false;
                    break;
                }
            }

            if(isValid){
                result.add(currentWord);
            }
        }

        return result;
    }

    private static int[] get_char_frequency(String current_word) {
        int[] result = new int[26];
        for (char c : current_word.toCharArray()){
            result[c - 'a']++;  //  c - 'a' => int value of char 1 < val < 26
        }
        return result;
    }
}
