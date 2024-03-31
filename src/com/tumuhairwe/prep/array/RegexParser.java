package com.tumuhairwe.prep.array;

import java.util.Arrays;
import java.util.List;

/**
 * Basic Regex Parser
 * Implement a regular expression function isMatch that supports the '.' and '*' symbols.
 *
 * The function receives two strings - text and pattern - and should return true if the text matches the pattern as a regular expression.
 * For simplicity, assume that the actual symbols '.' and '*' do not appear in the text string and are used as special symbols only in the pattern string.
 *
 * In case you aren’t familiar with regular expressions, the function determines if the text and pattern are the equal,
 * where the '.' is treated as a single a character wildcard (see third example), and '*' is matched for a zero or more
 * sequence of the previous letter (see fourth and fifth examples).
 */
public class RegexParser {

    private static Character[] chars = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'h', 'j', 'k'};
    private static List<Character> allowedChars = Arrays.asList(chars);
    static char ASTERISK = '*';
    static char DOT = '.';

    private static boolean isRecursiveMatch(String text, String pattern) {
        char[] textArray = text.toCharArray();

        for(int i=0; i<pattern.length() && i<text.length(); i++){
            char patternChar = pattern.toCharArray()[i];
            char currentChar = textArray[i];

            if(patternChar != ASTERISK && patternChar != DOT) {
                if (patternChar != currentChar) {
                    return false;    // char isn't in expected-index && is NOT allowedCharacter
                }
                else return isRecursiveMatch(text, pattern.substring(i+1));
            }

            return isRecursiveMatch(text, pattern);
        }

        return false;
    }
    private static boolean isMatch(String text, String pattern) {
        boolean matches = false;
        char[] textArray = text.toCharArray();
        int lastValidCharacterIndex = 0;

        for(int i=0; i<pattern.length() && i<text.length(); i++){
            char patternChar = pattern.toCharArray()[i];
            char currentChar = textArray[i];

            if(patternChar != ASTERISK && patternChar != DOT) {
                if (patternChar != textArray[i]) {
                    return false;    // char isn't in expected-index && is NOT allowedCharacter
                } else {
                    // char is allowed e.g. text = "ab", pattern = "ab*", 0=1 or i=1
                    lastValidCharacterIndex = i;
                    matches = true;
                }
            }
            else if(patternChar == ASTERISK){
                if(textArray[lastValidCharacterIndex] == currentChar) {
                    // char is allowed e.g. text = "ab" and pattern = "ab*", i=2, lastValidCharacterIndex=1
                    System.out.println("Char is allowed boz index=" + i + " and lastValidCharacterIndex "+lastValidCharacterIndex+" currentChar " + textArray[i]);
                    matches = true;
                    continue;
                }
                else {
                    // char is NOT allowed e.g. text = "abc" and pattern = "ab*", i=2, lastValidCharacterIndex=1
                    System.out.println("Char is NOT allowed boz index=" + i + " and previous as " + currentChar);
                }
            }
            else if(patternChar == DOT){
                if(!allowedChars.contains(currentChar)){
                    System.out.println("Disallowed char at index " + i);
                    break;
                }
            }
        }

        return matches;
    }

    public static void main(String[] args) {
        // input:  text = "ab", pattern = "ab"
        boolean isMatch = false;    //isMatch("ab", "ab*b");
        boolean isMatchRecursive = isRecursiveMatch("ab", "ab*b");
        System.out.println("input:  text = \"ab\", pattern = \"ab\"  -> iterative=" + isMatch + " recursive=" + isMatchRecursive);

//        // ab*b
//        boolean isMatch = isMatch("ab", "ab");
//        boolean isMatchRecursive = isRecursiveMatch("ab", "ab");
//        System.out.println("input:  text = \"ab\", pattern = \"ab\"  -> iterative=" + isMatch + " recursive=" + isMatchRecursive);

        //input:  text = "ab", pattern = "ab*b
        isMatch = isMatch("ab", "ab*");
        System.out.println("input:  text = \"ab\", pattern = \"ab*\"  -> " + isMatch);

        //input:  text = "ab", pattern = "ab*b
        isMatch = isMatch("ab", "ab*b");
        System.out.println("input:  text = \"ab\", pattern = \"ab*b\"  -> " + isMatch);

        //input:  text = "abbb", pattern = "ab*"
        isMatch = isMatch("abbb", "ab*");
        System.out.println("input:  text = \"abbb\", pattern = \"ab*b\"  -> " + isMatch);
    }


    // input:  text = "ab", pattern = "ab*b"  a b* b == a '[b] ' b
    // input:  text = "ab", pattern = "ab*b"  ab   a b*
    // input:  text = "a", pattern = "ab*"  == true
    // input:  text = "a", pattern = "ab*b"  == false
    // output: true
    // when i = 0

    // A
    // if char[i] != '*' && != '.'
    // char[] word[i] == char[] patter[i]

    // B
    // '*'
    // pattern[i]== word[i-1] ----

    // C
    // '.'
    // patter[i] == ANY_ALPHANUMERIC_CHARACTER

    // if we reach end of pattern
    // word[i+1] != pattern[i] if pattern[i] == '*'

    // input:  text = "acd", pattern = "ab*c."
    // output: true

    //input:  text = "acd", pattern = "ab*c."
}
