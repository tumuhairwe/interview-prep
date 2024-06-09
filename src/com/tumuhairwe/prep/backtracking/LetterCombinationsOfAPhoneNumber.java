package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        List<String> results = letterCombinations(digits);
        System.out.println(results);
    }
    public static List<String> letterCombinations(String digits) {
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
            ;        }

        return new ArrayList<>(output);
    }
}
