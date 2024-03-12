package com.tumuhairwe.prep.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * Find the longest substring without repeating characters
 *
 * ref: https://www.youtube.com/watch?v=3IETreEybaA&t=3s
 */
public class LengthOfLongestSubstring {

    // Space O(m)  ... where m = size of character set
    public static void main(String[] args) {
        String input = "ACGAATTCCG";
        int length = longest(input);
        System.out.println("Longest Substring " + length);
    }

    private static String getLongestSubstring(String input){
        int windowStart = 0;    // i
        int windowEnd = 0;      // j
        int max = 0;

        Set<Character> setOfUniqueChars = new HashSet<>();
        while (windowEnd < input.length()){
            Character c = Character.valueOf(input.charAt(windowEnd));

            if (!setOfUniqueChars.contains(c)){
                setOfUniqueChars.add(c);
                windowEnd++;

                max = Math.max(setOfUniqueChars.size(), max);
            }
            else {
                Character ch = input.charAt(windowStart);
                setOfUniqueChars.remove(ch);
                windowStart++;
            }
        }

        System.out.println("Given input: " + input);
        System.out.println("Max: " + max);
        System.out.println("Start: " + windowStart + " End: " + windowEnd);
        System.out.println("Longest string = " + input.substring(windowStart, windowEnd));
        return input.substring(windowStart, windowEnd);
    }

    private static int longest(String s){
        int a_pointer= 0;
        int b_pointer= 0;
        int max = 0;

        Set<Character> hash_set = new HashSet<>();
        while (b_pointer < s.length()){
            if(!hash_set.contains(s.charAt(b_pointer))){
                hash_set.add(s.charAt(b_pointer));
                b_pointer++;

                max = Math.max(hash_set.size() , max);
            }
            else {
                hash_set.remove(s.charAt(a_pointer));
                a_pointer++;
            }
        }

        System.out.println("Given input: " + s);
        System.out.println("Max: " + max);
        System.out.println("Start: " + a_pointer + " End: " + b_pointer);
        System.out.println("Longest string = " + s.substring(a_pointer, b_pointer));
        return max;
    }
}
