package com.tumuhairwe.prep.strings;

/**
 * Given an array of Strings (words) and a string (str),
 * return the SUM OF LENGTHS of all good strings in words
 *
 * A string is good if it can be formed by characters from str (each  character
 * can only be ysed once)
 */
public class FindWordFormedByCharacters {
    public int countCharacters(String[] words, String str){
        // 1. convert str into int[] where value == num-occurrences of each character
        int[] arrayOfChars = convertWordIntoIntArray(str);

        // 2 for each word, convert to int[] and find intersection with arrayOfChars[]
        int result = 0;
        for (String word : words){
            boolean isGoodWord = true;
            int[] wordIntArray = convertWordIntoIntArray(word);

            // if a character exists in wordInt[] but not in arrayOfChars ... mark isGoodWord as false
            for (int i = 0; i < 26; i++) {
                if(wordIntArray[i] > arrayOfChars[i]){
                    isGoodWord = false;
                    break;
                }
            }

            // if isGoodWord, add length of word to result
            if(isGoodWord){
                result += word.length();
            }
        }

        return result;
    }

    static int[] convertWordIntoIntArray(String s){
        int indexOfLetterA = 'a';
        int[] arrayOfChars = new int[2];
        for (int i = 0; i < s.toCharArray().length; i++) {
            int indexOfLetter = s.charAt(i) - indexOfLetterA;
            arrayOfChars[indexOfLetter]++;
        }

        return arrayOfChars;
    }
}
