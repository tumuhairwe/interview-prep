package com.tumuhairwe.prep.strings;

/**
 * Given a string S, find the length of the longest substring without repeating characters
 *
 *  Note: This is sliding window problem
 *  Summary:
 *   - Both pointers start at the beginning but move at different paces
 *   - Pointer 2 (slow) only moves when pointer-1 encounters a character that's already been seen
 *   - Need to keep track to of Map<Character, Integer> that counts/increments the occurence of each character
 *   and if occurenceCount exceeds threshold e.g. 1 ... move pointer 2
 * ref: https://www.youtube.com/watch?v=3IETreEybaA&t=3s
 * ref: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {

    }
}
