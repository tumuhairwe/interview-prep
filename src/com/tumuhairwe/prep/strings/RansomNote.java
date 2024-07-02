package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 383. (easy)
 *
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 */
public class RansomNote {
    public static void main(String[] args) {
        String a = "aa";
        String b = "aab";
        System.out.println(canConstruct(a, b));
    }

    /**
     * Solution summary
     * - Create a freqMap of magazine
     * - check ransomNote if any character occurs in freqMap ... if so decrement, if not, return false
     * - return true if you get to end of loop checking ransomNode & all characters exist
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            char ch = magazine.charAt(i);

            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char ch = ransomNote.charAt(i);
            if(freqMap.containsKey(ch) && freqMap.get(ch) > 0){
                freqMap.put(ch, freqMap.get(ch) - 1);
            }
            else{
                return false;
            }
        }
        return true;
    }
}
