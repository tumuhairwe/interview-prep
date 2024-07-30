package com.tumuhairwe.prep.strings;

import java.util.Stack;

/**
 * LeetCode 316 (medium)
 * Given a string s, remove duplicate letters so that
 *  - every letter appears only once
 *  - Make sure your result is the smallest lexicographically among all possible results
 *
 * ref: https://leetcode.com/problems/remove-duplicate-letters/?envType=daily-question&envId=2023-09-26
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        String st = "bcabc";
        System.out.println("Before: " + st + " after: " +removeDuplicates(st) + " should be abc");

        st = "cbacdcbc";
        System.out.println("Before: " + st + " after:" +removeDuplicates(st) + " should be acdb");
    }

    /**
     * Solution summary
     * Set up
     * - Create int[] that stores the last index of any character in the string
     * - Create boolean[] to track whether we've seen the character or not
     * - create stack to track characters that are lexicographically greater than
     *
     * 1. traverse the string and for each char
     *  - if already seen ... continue
     *  - else if !stack.isEmpty() && currentChar is smaller than the top of the stack,
     *      pop the top of the stack and mark it as unseen (goal: the monotonic stack's top should always be greater than previous)
     *  - after the popping is done, the stack's top is less than our current char ... push current char to top of stack and mark it as seen
     *  2. At last, reconstruct the stack into a String (using stringBuilder) and return in reverse order
     */
    public static String removeDuplicates(String s){
        //0. base case
        if(s.length() <= 1){
            return s;
        }

        //1. create [] to track where character was last seen
        int[] lastSeenIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int indexValueOfChar = s.charAt(i) - 'a';
            lastSeenIndex[indexValueOfChar] = i;
        }

        //3 Iterate thru char[] && track whether or character has been.
        boolean[] seen = new boolean[26];
        Stack<Integer> indexStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            //3. get index value of char
            int indexValueOfChar = s.charAt(i) - 'a';

            //skip if already seen
            if(seen[indexValueOfChar]){
                continue;
            }

            //4. pop & unsee a char that occurs earlier but is greater than top-of-the-stack
            // remember goal s to maintain a lexicographically smaller string
            while (!indexStack.isEmpty()
                    && i < lastSeenIndex[indexStack.peek()]
                    && indexStack.peek() > indexValueOfChar){
                seen[indexStack.pop()] = false;
            }

            //5. add to stack
            indexStack.push(indexValueOfChar);

            //6. mark as seen
            seen[indexValueOfChar] = true;
        }

        // reconstruct the string on the stack
        StringBuilder sb = new StringBuilder();
        while (!indexStack.isEmpty()){
            char ch = (char)(indexStack.pop() + 'a');
            sb.append(ch);
        }

        return sb.reverse().toString();
    }
}
