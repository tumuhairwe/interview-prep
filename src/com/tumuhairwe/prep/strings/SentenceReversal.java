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
 *
 * ref: https://leetcode.com/problems/reverse-words-in-a-string/description/
 */
public class SentenceReversal {

    private static char WORD_SEPARATOR = ' ';

    public static void main(String[] args) {
        String sentence = "Practice Makes Perfect";
        String expected = "Perfect Makes Practice";

        String reversed = new String(reversed(sentence.toCharArray()));
        System.out.println("Is Sentenced reversed? " + reversed.equals(expected));

        sentence = "  hello world  ";
        expected = "world hello";

        reversed = new String(reversed(sentence.toCharArray()));
        System.out.println("Is Sentenced reversed? " + reversed.equals(expected));
    }

    /**
     * Solution Summary:
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
        // 0. populate stack with words
        Stack<String> words = new Stack<>();

        // 0.1 method 1 -> use regex to split sentence into words/tokens -> add to stack
        String[] tokens = String.valueOf(sentence).split("\\s");
        Arrays.stream(tokens)
                .filter(token -> token != null && token.trim().length() > 0)
                .forEach(word -> {
                    words.push(word);
                });

        // 0.2 method 2 -> iterate over charArray & manually construct word using StringBuilder
        //              -> when encounter a space = add sb,toString() tpo stack
//        for (int i = 0; i < sentence.length; i++) {
//            char c = sentence[i];
//            if(c == WORD_SEPARATOR){
//                words.push(sb.toString());
//                sb = new StringBuilder();
//            }
//            else {
//                sb.append(c);
//            }
//        }
        //words.push(sb.toString());  // last time

        // 1. loop over stack -> pop each word
        //      -> initialize filledSpots int
        //      -> reverse each word by copying char-by-char into result array
        //      -> update filledSpots += word.length();
        //      -> At the end of each word, append a SPACE to result[] & increment filledSpots
        // return result[]
        char[] result = new char[sentence.length];
        int filledSpots = 0;
        while (!words.isEmpty()){
            String word = words.pop();

            for (int i = 0; i < word.length(); i++) {
                result[i + filledSpots] = word.toCharArray()[i];
            }

            filledSpots += word.length();
            if(filledSpots < sentence.length -1){
                result[filledSpots] = WORD_SEPARATOR;   // add space except the last one
                filledSpots++;
            }
        }
        return new StringBuilder().append(result).toString().trim().toCharArray();
    }

    static char[] reverseImpl2(char[] sentence){
        // 0. construct the string
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : sentence){
            stringBuilder.append(ch);
        }

        // 1. regex-split based on space
        String[] arr = stringBuilder.toString().split("\\w");

        // 2. reverse String[] from above
        StringBuilder result = new StringBuilder();
        for (int i = arr.length; i >= 0; i--) {
            result.append(arr[i]);
            result.append(WORD_SEPARATOR);
        }

        // 3. trim off extra space & convert to char[]
        return result.toString()
                .trim()   // trim off last space
                .toCharArray();
    }
}
