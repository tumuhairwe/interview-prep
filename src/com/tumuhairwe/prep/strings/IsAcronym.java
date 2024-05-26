package com.tumuhairwe.prep.strings;

import java.util.List;

/**
 * LeetCode 2823 (easy)
 * Check if a given word is an acronym
 *
 * Given an array of strings words and a string s, determine if s is an acronym of words.
 *
 * The string s is considered an acronym of words if it can be formed by concatenating the first character of each string in words in order. For example, "ab" can be formed from ["apple", "banana"], but it can't be formed from ["bear", "aardvark"].
 *
 * Return true if s is an acronym of words, and false otherwise.
 */
public class IsAcronym {
    public static void main(String[] args) {
        List words = List.of("alice","bob","charlie");

        boolean result = isAcronym(words, "abc");
        System.out.println("Is abc  acronym of ? " + result + " " + isAcronym(words, "abc") );
    }
    public static boolean isAcronym(List<String> words, String s) {
        if(s.length() != words.size()){
            return false;
        }

        for(int index=0; index<words.size(); index++){
            if(s.charAt(index) != words.get(index).charAt(0)){
                return false;
            }
        }

        return true;
    }
}

