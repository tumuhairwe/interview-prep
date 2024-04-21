package com.tumuhairwe.prep.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string S, find the length of the longest substring without repeating characters
 * LeetCode 3 (medium)
 *
 *  Note: This is sliding window problem
 *  Summary:
 *   - Both pointers start at the beginning but move at different paces
 *   - Pointer 2 (slow) only moves when pointer-1 encounters a character that's already been seen
 *   - Need to keep track to of Map<Character, Integer> that counts/increments the occurrence of each character
 *   and if occurenceCount exceeds threshold e.g. 1 ... move pointer 2
 * ref: https://www.youtube.com/watch?v=3IETreEybaA&t=3s
 * ref: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("Length of " + s + " =" + lengthOfLongestSubstring(s));
    }

    // use set to track character & index of character
    private static int lengthOfLongestSubstring(String str) {
        List<Character> substring = new ArrayList<>();
        int maxLength = Integer.MIN_VALUE;
        for (int right = 0; right < str.length(); right++) {
            Character c = str.charAt(right);

            if(substring.contains(c)){
                // get index of char
                int index = substring.indexOf(c);
                substring.remove(index);

                // clear all chars up until that point
                if(index > 0){
                    substring.subList(0, index).clear();
                }
            }
            substring.add(c);
            maxLength = Math.max(maxLength, substring.size());
        }

        return maxLength;
    }
}
