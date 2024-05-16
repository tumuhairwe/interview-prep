package com.tumuhairwe.prep.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given 2 string s and t, return true if s is an anagram of t and false otherwise
 */
public class ValidAnagram {
    public static void main(String[] args) {
        String st1 = "anagram";
        String st2 = "nagaram";
        System.out.println("Approach 1 " + st1 + " and " + st2 + " -> " + isAnagram2(st1, st2));
        System.out.println("Approach 2 " + st1 + " and " + st2 + " -> " + isAnagram2(st1, st2));
        System.out.println("Approach 3 " + st1 + " and " + st2 + " -> " + isAnagram3(st1, st2));
    }

    public static boolean isPalindrom(String s, String t) {

        // 0. base case
        if(s.length() != t.length()){
            return false;
        }

        // 1.
        int j = t.length() -1;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) != t.charAt(j -i)){
                return false;
            }
            j--;
        }

        return true;
    }

    /**
     * Since anagrams return identical strings when sorted,
     * - sort the input string and compare the output
     * - Strings can be sorted by splitting into char arrays and per
     * @return
     */
    static boolean isAnagram2(String st1, String st2){
        return sortedString(st1).equals(sortedString(st2));
    }

    private static String sortedString(String st2) {
        char[] c = st2.toCharArray();
        Arrays.sort(c);
        return Arrays.toString(c);
    }

    /**
     * Approach 2: Character frequency comparison
     * - Compute the frequency of each character in both strings
     * - if any character's frequency differs between the 2 strings, they're not anagrams
     * @return
     */
    // TC = O (n) == n is the length of string i.e. constant space is used to store each char the frequency-map
    // SC = 0 (n) == time to update the character count
    static boolean isAnagram(String st1, String st2){
        if(st1.length() != st2.length()){
            return false;
        }
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char c : st1.toCharArray()){
            int count = freqMap.getOrDefault(c, 0);
            freqMap.put(c, count + 1);
        }

        for(char c : st1.toCharArray()){
            if(!freqMap.containsKey(c) || freqMap.get(c) == 0 ){
                return false;
            }
            else {
                int count = freqMap.get(c);
                freqMap.put(c, count - 1);
            }
        }
//        long count = freqMap.entrySet().stream()
//                .filter(e -> e.getValue() > 0)
//                .count();
//        return count == 0;
        return true;
    }

    /**
     * Approach 3 Create ahar-frequency-map of each word and compare them
     * If both maps are equal (i.e. exactly same keys & value), they're anagrams
     */
    static boolean isAnagram3(String s1, String s2 ){
        // is equals
        Map<Character, Integer> freqMap1 = new HashMap<>();
        for(char c : s1.toCharArray()){
            int count = freqMap1.getOrDefault(c, 0);
            freqMap1.put(c, count + 1);
        }

        Map<Character, Integer> freqMap3 = new HashMap<>();
        for(char c : s2.toCharArray()){
            int count = freqMap3.getOrDefault(c, 0);
            freqMap3.put(c, count + 1);
        }
        return freqMap1.equals(freqMap3);
    }
}
