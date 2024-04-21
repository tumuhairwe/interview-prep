package com.tumuhairwe.prep.strings;

import java.util.Arrays;
import java.util.Stack;

/**
 * You are given an array of characters arr that consists of sequences of characters separated by space characters.
 * Each space-delimited sequence of characters defines a word.
 *
 * Implement a function reverseWords that reverses the order of the words in the array in the most efficient manner.
 *
 * Explain your solution and analyze its time and space complexities.
 *
 * Example:
 *
 * input:  arr = [ 'p', 'e', 'r', 'f', 'e', 'c', 't', '  ',
 * 'm', 'a', 'k', 'e', 's', '  ',
 * 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e' ]
 *
 * output: [ 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e', '  ',
 * 'm', 'a', 'k', 'e', 's', '  ',
 * 'p', 'e', 'r', 'f', 'e', 'c', 't' ]
 * Constraints:
 *
 * [time limit] 5000ms
 *
 * [input] array.character arr
 *
 * 0 ≤ arr.length ≤ 100
 * [output] array.character
 */
public class SentenceReversal {

    private static char WORD_SEPARATOR = ' ';

    public static void main(String[] args) {
        String sentence = "Practice Makes Perfect";
        String expected = "Perfect Makes Practice";

        String revered = new String(reversed(sentence.toCharArray()));
        System.out.println("Is Sentenced reversed? " + revered.equals(expected));
    }

    /**
     * Break chars into SPACE separated chunks
     * - Make those chunks strings
     * - Store the in LIFE data structure (Stack)
     * - Reconstruct the stack into single String
     * - Return string as char[]
     *
     * @param sentence
     * @return
     */
    private static char[] reversed(char[] sentence) {
        Stack<String> words = new Stack<>();

        // 0. populate stack with words
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentence.length; i++) {
            char c = sentence[i];
            if(c == WORD_SEPARATOR){
                words.push(sb.toString());
                sb = new StringBuilder();
            }
            else {
                sb.append(c);
            }
        }
        words.push(sb.toString());  // last time

        char[] result = new char[sentence.length];
        int filledSpots = 0;
        while (!words.isEmpty()){
            String w = words.pop();

            for (int i = 0; i < w.length(); i++) {
                result[i + filledSpots] = w.toCharArray()[i];
            }

            filledSpots += w.length();
            if(filledSpots < sentence.length -1){
                result[filledSpots] = WORD_SEPARATOR;   // add space except the last one
                filledSpots++;
            }
        }

        return result;
    }
}
