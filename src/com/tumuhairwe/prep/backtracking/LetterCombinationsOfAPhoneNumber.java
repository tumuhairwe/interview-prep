package com.tumuhairwe.prep.backtracking;

import java.util.*;

/**
 *LeetCode 17 (medium)
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the
 * number could represent. Return the answer in any order.
 *
 * ref: https://www.youtube.com/watch?v=0snEunUacZY
 * ref: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        String digits = "23";
        List<String> results = letterCombinations_traversal(digits);
        System.out.println(results);
    }

    public static List<String> letterCombinations_backtracking(String digits){
        //0. base case
        if(digits.length() == 0){
            return new ArrayList<>();
        }

        //1. define global repository of mappings
        Map<Integer, String> digitToChatMap = new HashMap<>();
        //digitToChatMap.put(1, "");
        digitToChatMap.put(2, "abc");
        digitToChatMap.put(3, "def");

        digitToChatMap.put(4, "ghi");
        digitToChatMap.put(5, "jkl");
        digitToChatMap.put(6, "mno");

        digitToChatMap.put(7, "pqrs");
        digitToChatMap.put(5, "tuv");
        digitToChatMap.put(5, "wxyz");

        //2. seed initial result list
        List<String> result = new ArrayList<>();
        letterCombinations_backtrack(digitToChatMap, digits, result, "", 0);

        return  result;
    }

    private static void letterCombinations_backtrack(Map<Integer, String> digitToChatMap, String digits, List<String> result, String current, int index) {
        if(current.length() == digits.length()){
            result.add(current);
            return;
        }
        else if(index >= digits.length()){
            return;
        }

        Integer currentDigit = Integer.parseInt(digits.charAt(index) + "");
        String letters = digitToChatMap.get(currentDigit);
        for (char c : letters.toCharArray()){
            letterCombinations_backtrack(digitToChatMap, digits, result, current + c, index + 1);
        }
    }

    /**
     * Solution Summary
     * - base-case: digits is emptu -> return empty list
     * - Create a mapping of [digit -> String] with the characters that correspond to that string
     * - for each digit the number, get the mapping (String)
     * - for each character in the mapped string,
     */
    public static List<String> letterCombinations_traversal(String digits) {
        Queue<String> output = new LinkedList<>();

        // 0. base case
        if(digits.length() == 0){
            return new ArrayList<>();
        }

        //1. seed with ""
        output.add("");

        // 1. define mapping
        String[] charMap = new String[]{
                "0", "1",
                "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        // for each digit provided
        for (int i = 0; i < digits.length(); i++) {
            // get numeric value of given character
            //char currentDigit = digits.charAt(i);
            //int index = Character.getNumericValue(currentDigit);
            int number = Integer.parseInt(digits.charAt(i) + "");

            while (output.peek().length() == i) {   // goal: make sure to produce perms with same length as input
                String permutation = output.remove();
                for(char ch : charMap[number].toCharArray()){
                    output.add(permutation + ch);
                }
            }
        }

        return new ArrayList<>(output);
    }
}
