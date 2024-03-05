package com.tumuhairwe.prep.strings;

import java.util.*;

/**
 * Given a string S, that represents a DNA sequence
 * ... and a number K
 * ... return all the contiguous substrings of length K
 * ... that occur more than 1 in the string
 *
 * ref: https://www.youtube.com/watch?v=RoRRpF3eCH4
 * ref: https://www.educative.io/courses/grokking-coding-interview-patterns-java/repeated-dna-sequences
 */
public class LongestSubstring {

    public void bruteForce(String input, int k){
        // find all the substring from input && check whether t
        // hey have length K
        // whether they have duplicate characters
        Set<Character> set = new HashSet<>();
        // time-complexity = O(n^3);
        // space complexity = O(n)
        for (int i = 0; i < input.toCharArray().length; i++) {
            for (int j = 0; j < 9; j++) {

            }
            Character c = Character.valueOf(input.charAt(i));
            if(!set.contains(c)){
                set.add(c);
            }
            else continue;
        }
    }

    /**
     * Keep 2 pointers to encompass the biggest valid solution that we can
     */
    public void slidingWindow(){
        int firstPoointer , secondPointer = 0;
        // pointer should include a word that does not invalidate our conditions
    }
    public static void main(String[] args) {
        LinkedList<Character> d = new LinkedList<>();
        ArrayDeque<Character> a = new ArrayDeque<>();
        a.addFirst('A');
        a.addLast('Z');

        a.pollFirst();
        a.pollFirst();
        a.remove();
        a.size();

        a.pop();

        String input = "ACGAATTCCG";
        Set<Character> chars = new HashSet<>();

        int resultLength = 0;

        int windowStart = 0;
        int windowEnd = 0;
        // charLength = chars.length();

        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            Character c = Character.valueOf(arr[i]);

            while (chars.contains(c)){
                chars.remove(c);
                windowStart++;
            }
            chars.add(c);
            resultLength = windowStart - i +1;
        }
    }
}
