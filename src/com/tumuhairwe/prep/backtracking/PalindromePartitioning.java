package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 131 palindrome partitioning (medium)
 *
 *
 * ref: https://leetcode.com/problems/palindrome-partitioning/description/
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        List<List<String>> results = partition("aab");
        System.out.println(results);
    }
    public static List<List<String>> partition(String str){
        List<List<String>> result = new ArrayList<>();
        backtrack(str, result, new ArrayList<>(), 0);
        return result;
    }

    static void backtrack(String str, List<List<String>> superset, List<String> subset, int startIndex){
        if(startIndex == str.length()){
            superset.add(new ArrayList<>(subset));
        }
        else {
            for (int i = startIndex; i < str.length(); i++) {
                if(isPalindrome(str, startIndex, i)){
                    subset.add(str.substring(startIndex, i+1));
                    backtrack(str, superset, subset, i+1);
                    subset.remove(subset.size() - 1);
                }
            }
        }
    }

    private static boolean isPalindrome(String str, int low, int high) {
        while (low < high){
            if(str.charAt(low++) != str.charAt(high--)){
                return false;
            }
        }

        return true;
    }
}
