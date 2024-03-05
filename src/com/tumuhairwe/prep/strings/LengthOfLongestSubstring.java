package com.tumuhairwe.prep.strings;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {

    // Space O(m)  ... where m = size of character set
    public static void main(String[] args) {
        String input = "ACGAATTCCG";
        //String input = "ABCABCBB";
        int windowStart = 0;    // i
        int windowEnd = 0;      //j

        int max = 0;
        int maxBeginningIndex = 0;

        Set<Character> setOfUniqueChars = new HashSet<>();
        while (windowEnd < input.length()){
            Character c = Character.valueOf(input.charAt(windowEnd));

            if (!setOfUniqueChars.contains(c)){
                setOfUniqueChars.add(c);
                windowEnd++;

                max = Math.max(setOfUniqueChars.size(), max);
            }
            else {
//                if(setOfUniqueChars.size() == max){
//                    maxBeginningIndex = windowStart;
//                }
                Character ch = input.charAt(windowStart);
                setOfUniqueChars.remove(ch);
                windowStart++;

                //maxBeginningIndex = windowStart;
            }
        }

        System.out.println("Given input: " + input);
        System.out.println("Max: " + max);
        System.out.println("Start: " + windowStart + " End: " + windowEnd);
        System.out.println("Longest string = " + input.substring(maxBeginningIndex));
    }
}
