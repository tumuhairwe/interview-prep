package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 18 (Easy)
 * Find the words that can be formed by an array of characters
 * ... return the sum of the length of the "good" words in array words.
 * A word is "good" if it can be formed by characters from chars (each character can only be used once)
 *
 * ref: https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
 * ref: https://www.youtube.com/watch?v=M2HFao-zgk8
 */
public class CountCharacters {
    public static void main(String[] args) {
        // example 1
        String[] words = {"cat", "bt", "hat", "tree"};
        String word = "atach";
        int count = countCharacters(words, word);
        System.out.println("For word" + word + " lengthOfGoodWords = " + count);

        // example 2
        words = new String[]{"hello","world","leetcode"};
        word = "welldonehoneyr";
        count = countCharacters(words, word);
        System.out.println("For word" + word + " lengthOfGoodWords = " + count);
    }

    public static int countCharacters(String[] words, String current_word){
        int goodWordsLengthSum = 0;
        int[] global_char_counts  = new int[26];
        // is constant space because only 26 chars (in Alphabet for which we care going to keep track of the count)

        // 0. Build char counts array
        final int asciiValueOfA = 'a';
        for (char asciiValueOfCharacter : current_word.toCharArray()){
            int indexOfLetter = asciiValueOfCharacter - asciiValueOfA;
            global_char_counts[indexOfLetter]++;  //  c - 'a' => int value of char 1 < val < 26
        }

        // 1.
        for(String word : words){
            int[] local_char_counts = Arrays.copyOf(global_char_counts, global_char_counts.length);
            int valid_char_count = 0;

            for(char asciiValueOfCharacter : word.toCharArray()){
                int indexOfLetter = asciiValueOfCharacter - asciiValueOfA;
                if(local_char_counts[indexOfLetter] > 0){
                    valid_char_count++;     // increment valid-char-count -- will check if its size == word.length()
                    local_char_counts[indexOfLetter]--;
                }

                if(valid_char_count == word.length()){  // all chars in word were in global global_char_counts
                    goodWordsLengthSum += word.length();    // increment return var by word.length
                }
            }
        }
        return goodWordsLengthSum;
    }
}
