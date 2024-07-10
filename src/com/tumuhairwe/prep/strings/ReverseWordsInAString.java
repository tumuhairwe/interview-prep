package com.tumuhairwe.prep.strings;

import java.util.Arrays;

/**
 * LeetCode 186 (medium)
 *
 * Given a character array s, reverse the order of the words.
 * A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.
 * Your code must solve the problem in-place, i.e. without allocating extra space.
 *
 * ref: https://leetcode.com/problems/reverse-words-in-a-string-ii/
 *
 */
public class ReverseWordsInAString {

    public static void main(String[] args) {
        char[] ch = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        System.out.println("BEFORE: " + Arrays.toString(ch));
        reverseWords(ch);
        System.out.println("AFTER: " + Arrays.toString(ch));
    }
    /**
     * Solution summary (reverse word by word, then reverse entire string)
     * - init 2 pointers and repeat until p2 <= s.length
     * - increment fast pointer until it encounters a space
     * - swap character between fast and slow pointers
     * - increment fast pointer (to account for space
     * - repeat until fast == str.length
     * - finally swap entire sentence
     */
    public static void reverseWords(char[] s){
        int slow = 0;
        int fast = 0;

        while (fast < s.length){
            slow = fast;

            // increment p2 until space
            while (fast < s.length && s[fast] != ' '){
                fast++;
            }

            //reverse a single word
            swap(s, slow, fast -1);

            //increment p2 (skip space)
            fast++;
        }

        // swap the entire sentence
        swap(s, 0, s.length - 1);
    }

    static void swap(char[] s, int start, int end){
        while (start <= end){
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;

            start++;
            end--;
        }
    }
}
