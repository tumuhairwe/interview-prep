package com.tumuhairwe.prep.twopointers;

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
 *
 * ref: https://www.youtube.com/watch?v=3IETreEybaA&t=3s
 * ref: https://www.youtube.com/watch?v=wiGpQwVHdE0
 * ref: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("Length of " + s + " =" + lengthOfLongestSubstring(s));
    }

    // use set to track character & index of character
    public static int lengthOfLongestSubstring(String s) {
        //0. create list
        List<Character> list = new ArrayList<>();

        //1. iterate thru str -> add char to list -> re-calc maxLength
        int maxLength = 0;
        for(int i=0; i<s.length(); i++){
            Character ch = s.charAt(i);

            //2. if already included -> find & remove -> clear substring up to that char
            if(list.contains(ch)){
                int index = list.indexOf(ch);
                list.remove(index);
                if(i > 0){
                    list.subList(0, index).clear();
                }
            }

            //3. add to list
            list.add(ch);

            //4. update maxLength
            maxLength = Math.max(maxLength, list.size());
        }

        return maxLength;
    }
}
