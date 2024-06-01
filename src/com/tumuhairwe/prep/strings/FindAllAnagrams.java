package com.tumuhairwe.prep.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * definition: 2 strings are anagrams if they have the exact same count of each character
 * \Leetcode 438 (medium)
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 *
 * Solution Summary
 */
public class FindAllAnagrams {
    public static void main(String[] args) {
        String s = "abab";
        String p = "ab";
        List<Integer> re = findAnagrams(s, p);
        System.out.println(re);
    }
    public static List<Integer> findAnagrams(String s, String p) {
        // 0. base case (p_string.length() should be <= s_string.length())
        if(p.length() > s.length()){
            return new ArrayList<>();
        }

        // 1. create charFrequency Map of both s and p (key=char, val=Int_frequency)
        Map<Character, Integer> pFrequencyMap = new HashMap<>();
        Map<Character, Integer> sFrequencyMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        //1.1 create populate pFrequencyMap
        for(char pChar : p.toCharArray()){
            pFrequencyMap.put(pChar, pFrequencyMap.getOrDefault(pChar, 0) + 1);
        }

        if(sFrequencyMap.equals(pFrequencyMap)){
            result.add(0);
        }

        // 3. since s-freq-map is already partially complete ... start from p.length() until s.length() to finish
        int begin = 0;
        for(int end=0; end<s.length(); end++){
            char sChar_at_rp = s.charAt(end);
            sFrequencyMap.put(sChar_at_rp, sFrequencyMap.getOrDefault(sChar_at_rp, 0) + 1);

            // shrink the window by removing char at left begin
            if(end >= p.length() - 1){
                if(sFrequencyMap.equals(pFrequencyMap)){
                    result.add(begin);
                }

                char sChar_at_lp = s.charAt(begin);
                if(sFrequencyMap.containsKey(sChar_at_lp)){
                    sFrequencyMap.put(sChar_at_lp, sFrequencyMap.getOrDefault(sChar_at_lp, 0) - 1);

                    if(sFrequencyMap.get(sChar_at_lp) == 0){
                        sFrequencyMap.remove(sChar_at_lp);
                    }
                }

                begin++;
            }
        }

        return result;
    }
}
