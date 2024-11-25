package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 22 (medium)
 * ref: https://leetcode.com/problems/generate-parentheses/description/
 *
 * Given n pair of parenthesis, write a function to generate all combinations of well-formed parenthesis
 * ref: https://leetcode.com/problems/generate-parentheses/solutions/10100/easy-to-understand-java-backtracking-solution/
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        List<String> expected = List.of("((()))","(()())","(())()","()(())","()()()");
        List<String> actual = generateParenthesis(3);
        System.out.println(actual.equals(expected));

        expected = List.of("()");
        generateParenthesis(1);
        actual = generateParenthesis(1);
        System.out.println(actual.equals(expected));
    }

    /**
     * Solution summary
     * - create return list for backtracking to append to when fragment.length == max*2
     * - call backtracking();
     * - return results
     */
    static public List<String> generateParenthesis(int n){
        List<String> results = new ArrayList<>();
        backtrack(results, "", 0, 0, n);
        return  results;
    }

    /**
     * - if fragment.length == max * 2 -> add it to result
     * - if open < max, a) append open_parenthesis, increment openCount by 1 and call backtracking
     * - if close < open a) append close_parenthesis, increment closeCount by 1 and call backtracking
     */
    static void backtrack(List<String> result, String fragment, int open, int close, int max){
        if(fragment.length() == max * 2){
            result.add(fragment);
            return;
        }

        if(open < max){
            backtrack(result, fragment + "(", open + 1, close , max);
        }
        if(close < open){
            backtrack(result, fragment + ")", open, close + 1, max);
        }
    }
}