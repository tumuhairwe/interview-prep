package com.tumuhairwe.prep.arrays;

import java.util.*;

/**
 * LeetCode 451 (sort chars by frequency)
 *
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * The frequency of a character is the number of times it appears in the string.
 *
 * Return the sorted string. If there are multiple answers, return any of them.
 */
public class SortCharactersByFrequency {

    public static void main(String[] args) {
        System.out.println(sortCharacters("tree"));
        System.out.println(sortCharacters("cccaaa"));
        System.out.println(sortCharacters("Aabb"));
    }
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
}
