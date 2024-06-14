package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 22 (medium)
 *
 * Given n pair of parenthesis, write a function to generate all combinations of well-formed parenthesis
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
    static public List<String> generateParenthesis(int n){
        List<String> results = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        backtrack(results, sb, 0, 0, n);

        return  results;
    }

    static void backtrack(List<String> results, StringBuilder sb, int leftCount, int rightCount, int n){
        // 0. base case
        if(sb.length() == (2 * n)){
            results.add(sb.toString());
            return;
        }

        // if we can add a left (i.e. leftCount == rightCount
        if(leftCount < n){
            // add char
            sb.append("(");
            backtrack(results, sb, leftCount + 1, rightCount, n);

            // backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
        // if we can add a right (i.e. left
        if (rightCount < leftCount){
            sb.append(")");
            backtrack(results, sb, leftCount, rightCount + 1, n);

            //backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}