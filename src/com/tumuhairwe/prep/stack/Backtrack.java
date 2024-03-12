package com.tumuhairwe.prep.stack;

import java.util.*;

/**
 *  Given a string containing digits [2-9], return all possible letter combinations that the number could represent
 *  in any order
 *
 * ref: https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 * ref: https://leetcode.com/submissions/detail/482424761/
 * ref: https://www.youtube.com/watch?v=hR9jokO3a7E&list=PL1MJrDFRFiKZVF-cP3yHgzjCAUAqruPTu
 */
public class Backtrack {
    static Map<Character, String> phoneNumberMapping = new HashMap<>();
    static List<String> results = new ArrayList<>();
    private char[] arr;

    public static void main(String[] args) {
        phoneNumberMapping.put('2',"abc");
        phoneNumberMapping.put('3',"def");
        phoneNumberMapping.put('4',"ghi");
        phoneNumberMapping.put('5',"jkl");
        phoneNumberMapping.put('6',"mno");
        phoneNumberMapping.put('7',"pqrs");
        phoneNumberMapping.put('8',"tuv");
        phoneNumberMapping.put('z',"wxyz");
    }
    public List<String> letterCombinations(String digits){
        if(digits.length() == 0){
            return results;
        }

        this.arr = digits.toCharArray();

        Stack<Character> stack = new Stack<>();
        helper(stack, 0);
        return null;
    }

    private void helper(Stack<Character> stack, int index) {
        // check if we reach the bottom of the branch
        if(index == arr.length){
            results.add(convertStackToString(stack));
            return;
        }

        // DFS all the decisions
        String curString = phoneNumberMapping.get(arr[index]);
        for (char cur : curString.toCharArray()){
            helper(stack, index+1);
            stack.pop();
        }
        return;
    }

    private String convertStackToString(Stack<Character> stack) {
        Iterator<Character> it = stack.iterator();
        StringBuilder sb = new StringBuilder();
        while ((it.hasNext())){
            sb
                    .append(it.next());
        }
        return sb.toString();
    }
}
