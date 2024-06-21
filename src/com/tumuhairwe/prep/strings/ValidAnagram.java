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

    // Palindrome: word that reads the same backwards and forwards = racecar

    /**
     * Solution Summary
     * - use 2 pointers to traverse the array from both ends
     * -
     * TC: O (n)
     */
    public static boolean isPalindrome(String s, String t) {
        // 0. base case
        if(s.length() != t.length()){
            return false;
        }

        // 1.
        int end = t.length() -1;
        for(int start=0; start<s.length(); start++){
            if(s.charAt(start) != t.charAt(end -start)){
                return false;
            }
            end--;
        }

        return true;
    }

    /**
     * Since anagrams return identical strings when sorted,
     * - sort the input string and compare the output
     * - Strings can be sorted by splitting into char arrays and per
     * TC: O( log n) --- because of sorting
     * SC: O(n) -- because of temp sortable array
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

        for(char c : st2.toCharArray()){
            if(!freqMap.containsKey(c) || freqMap.get(c) == 0 ){
                return false;
            }
            else {
                int count = freqMap.get(c);
                freqMap.put(c, count - 1);
            }
        }
        // WARNING: will run into TimeLimitExceeded
        return true;
    }

    /**
     * Solution Summary
     * Use an in[] to track the frequency of each letter in each string
     * - increment or decrement accordingly
     * - At the end, all frequencies should be zero, if not, they're not anagrams
     */
    public boolean isAnagram_freqCounter(String s, String t){
        //0.  base case
        if(t.length() != s.length()){
            return  false;
        }

        //1. build counter by decrementing each letter's frequency (and incrementing)
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        //2 at the end, all letters should be exactly zero
        for (int i = 0; i < count.length; i++) {
            if(count[i] != 0){
                return false;
            }
        }

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
