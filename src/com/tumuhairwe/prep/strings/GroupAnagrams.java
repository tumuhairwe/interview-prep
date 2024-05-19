package com.tumuhairwe.prep.strings;

import java.util.*;

/**
 * Group Anagrams (LeetCode 49) medium
 * Given an array of string, group the anagrams together. You can return them in any order.
 *
 * An anagram is a word or phrase formed by re-arrangin the letter of a different word or phrase, typically using all the orignnal
 * letters exactly once.
 *
 * ref: https://leetcode.com/problems/group-anagrams/
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0049-group-anagrams.java
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] arr = new String[]{"eat","tea","tan","ate","nat","bat"};
        List<List<String>> result = groupAnagrams(arr);
        System.out.println(result);
    }

    /**
     * Solution summary:
     * - Create a key for reach word in strs array
     *  (key = numeric representation of each word (based on
     *  TC: O(m  x n)
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        // base case
        if(strs.length == 0){
            return new ArrayList<>();
        }

        // key = numeric representation of word
        Map<String, List<String>> result = new HashMap<>();

        // 1. for each string, create List of anagrams
        for(String s : strs){

            // 2 - construct count array
            int[] count = new int[26];

            int indexOfLetterA = 'a';
            for (char asciiValueOfLetter : s.toCharArray()){
                int indexOfLetter = asciiValueOfLetter - indexOfLetterA;
                count[indexOfLetter]++;
            }

            // 3 convert count-array to string
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count.length; i++) {
                sb.append("#");
                sb.append(count[i]);
            }
            String key = sb.toString();

            // 4. use key to add to Map of results
            if(!result.containsKey(key)){
                result.put(key, new ArrayList<>());
            }
            result.get(key).add(s);
        }

        // 5 return values of outputMap
        return new ArrayList<>(result.values());
    }

    // TC: O (n log n) -> because of sorting

    /**
     * Solution Summary
     * - Convert each string to sorted-char-array
     * - Convert sorted-char-array to converted-string
     * - Use converted-string as key in Map and add original string to output List
     * - Return values of outputMap
     */
    public List<List<String>> groupAnagrams_nLogN(String[] strs) {
        // base case
        if(strs.length == 0){
            return new ArrayList<>();
        }

        Map<String, List<String>> outputMap = new HashMap<>();

        // 1. for each string
        for(int i=0; i<strs.length; i++){

            // 2 convert to char array -> sort -> convert to string
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String key = Arrays.toString(chars);

            // 3. put String in Map as Key, value == list of matching words with same key
            if(!outputMap.containsKey(key)){
                outputMap.put(key, new ArrayList<>());
            }

            outputMap.get(key).add(strs[i]);
        }

        return new ArrayList<>(outputMap.values());
    }

    // TC: n log_n
    static boolean isAnagram(String s1, String s2){
        char[] s1_arr = s1.toCharArray();
        Arrays.sort(s1_arr);
        String s1_str = Arrays.toString(s1_arr);

        char[] s2_arr = s2.toCharArray();
        Arrays.sort(s2_arr);
        String s2_str = Arrays.toString(s2_arr);

        return s1_str.equals(s2_str);
    }
}
