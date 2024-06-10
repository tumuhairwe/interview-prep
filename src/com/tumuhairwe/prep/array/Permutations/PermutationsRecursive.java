package com.tumuhairwe.prep.array.Permutations;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 267 (medium)
 * Palindrome Permutation II
 *
* Solution Summary
* 1. Fix the 1st index and keep swapping the character at this index with other characters of the string 1 by 1
* 2. After each swap, skip the 1st character and recursively compute the permutation of the remaining string
* 3. Add the string to the result when the last character of the string is reached
*  time-complexity = O(n x x!)
*  space-complexity = O(n)
 */
public class PermutationsRecursive {

    public static void main(String[] args) {
        String[] inputWords = {"ab", "bad", "abcd"};
        for (int i = 0; i < inputWords.length; i++) {
            List<String> permutedWords = permuteWord(inputWords[i]);
            System.out.println(permutedWords);
        }
    }

    private static List<String> permuteWord(String word) {
        List<String> results = new ArrayList<>();
        permuteStringRec(word, 0, results);
        return  results;
    }
    static void permuteStringRec(String word, int currentIndex, List<String> results){
        if(currentIndex == word.length() - 1){
            results.add(word);
            return;
        }

        for (int index = 0; index < currentIndex; index++) {
            String swappedString = swapChar(word, currentIndex, index);
            permuteStringRec(swappedString, currentIndex+1, results);
        }
    }
    static String swapChar(String word, int i, int j){
        char[] swapIndex = word.toCharArray();
        char temp = swapIndex[i];
        swapIndex[i] = swapIndex[j];
        swapIndex[i] = temp;

        return new String(swapIndex);
    }
}
