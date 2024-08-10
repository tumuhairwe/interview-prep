package com.tumuhairwe.prep.arrays;

import java.util.*;

/**
 * LeetCode 451 (sort chars by frequency)
 *
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * The frequency of a character is the number of times it appears in the string.
 *
 * Return the sorted string. If there are multiple answers, return any of them.
 *
 * ref: https://leetcode.com/problems/sort-characters-by-frequency/description/
 */
public class SortCharactersByFrequency {

    public static void main(String[] args) {
        System.out.println(sortCharacters("tree"));
        System.out.println(sortCharacters("cccaaa"));
        System.out.println(sortCharacters("Aabb"));
    }

    /**
     * Solution summary
     * - Create frequency mao of all char in string
     * - Collect all keys/chars into List and sort that list by frequency (based on frequency from map)
     * - Iterate over sorted List of chars ... for each char, add that-many/frequency may copies to StringBuilder
     * - return stringBuilder.toString()
     *
     * TC: sorting === n log n, iterating over sorted chars _freq_ number of time each == n * m
     * SC: O(n) == to store the chars of the stringBuilder. and O(k) for the hashMap/freqMap
     */
    public static String sortCharacters(String str){
        //0. create freqMap
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            freqMap.put(str.charAt(i), freqMap.getOrDefault(str.charAt(i), 0)+1);
        }

        //1. Collect entries into a List so you can sort
        List<Character> chars = new ArrayList<>(freqMap.keySet());
//        Comparator<Character> comp = (Character a, Character b) -> {
//            return  Integer.compare(freqMap.get(a), freqMap.get(b));
//        };
        Comparator<Character> comp = Comparator.comparingInt(freqMap::get);
        Collections.sort(chars, comp.reversed());

        //3. create and return sb
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.size(); i++) {
            char ch = chars.get(i);
            int frequency = freqMap.get(ch);
            for (int j = 0; j < frequency; j++) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    public String frequencySort(String s){
        //0. base case
        if(s == null || s.isEmpty()){
            return "";
        }

        //1. covert string to char[]
        char[] chars = s.toCharArray();

        //2. sort chars
        Arrays.sort(chars);

        //3. convert into list of Strings
        List<String> strings = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(chars[0]);    // seed with 1st char since duplicate-detection starts from 2nd char

        for (int i = 1; i < chars.length; i++) {
            if(chars[i] != chars[i - 1]){
                strings.add(sb.toString());
                sb = new StringBuilder();   // reset builder
            }

            sb.append(chars[i]);
        }
        //4 append last one
        strings.add(sb.toString());

        //5. sort by frequency
        Comparator<String> orderByFreq = Comparator.comparing(str -> str.length());
        Collections.sort(strings, orderByFreq);

        //6. convert to string to return
        sb = new StringBuilder();
        for (String str : strings){
            sb.append(str);
        }

        // return string
        return sb.toString();
    }
}
