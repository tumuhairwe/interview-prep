package com.tumuhairwe.prep.strings;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * LeetCode 3136 (easy)
 * A word is considered valid if:
 *
 * It contains a minimum of 3 characters.
 * It contains only digits (0-9), and English letters (uppercase and lowercase).
 * It includes at least one vowel.
 * It includes at least one consonant.
 * You are given a string word.
 *
 * Return true if word is valid, otherwise, return false.
 *
 * Notes:
 *
 * 'a', 'e', 'i', 'o', 'u', and their uppercases are vowels.
 * A consonant is an English letter that is not a vowel.
 */
public class IsValidWord {

    public static void main(String[] args) {
        System.out.println("Should be false "+ isValid("234Adas"));
        System.out.println("Should be false "+ isValid("b3"));
        System.out.println("Should be false "+ isValid("a3$e"));
    }
    static boolean isValid(String word){
        //0 base cse (criteria a)
        if(word.length() < 3){
            return false;
        }

        //1. create list of allowable chars
        // 1a) create list of upper case + lower case vowels
        Set<Character> lowerCaseVowels = Set.of('a','e','i','o', 'u');
        Set<Character> upperCaseVowels = lowerCaseVowels.stream().map(c -> Character.toUpperCase(c)).collect(Collectors.toSet());
        Set<Character> vowels = Stream.concat(lowerCaseVowels.stream(), upperCaseVowels.stream()).collect(Collectors.toSet());

        // 1b) create list of upper case + lower case consonants
        Set<Character> lowerCaseConsonants = Set.of('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z');
        Set<Character> upperCaseConsonants = lowerCaseConsonants.stream().map(c -> Character.toUpperCase(c)).collect(Collectors.toSet());
        Set<Character> consonants = Stream.concat(lowerCaseConsonants.stream(), upperCaseConsonants.stream()).collect(Collectors.toSet());

        //2. create 2 flags (criteria b & c)
        boolean isSeenConsonant = false;
        boolean isSeenVowel = false;

        //3. traverse all chars in array and
        for(Character c : word.toCharArray()){
            if(vowels.contains(c)){
                isSeenVowel = true;
            }
            else if(consonants.contains(c)){
                isSeenConsonant = true;
            }
            else if(!Character.isDigit(c)){
                return false;   // is non-alphanumeric
            }
        }

        // return if both criteria b & c are fulfilled
        return isSeenVowel && isSeenConsonant;
    }
}
