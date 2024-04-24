package com.tumuhairwe.prep.stack;

import java.util.*;

/**
 *  Given a string containing digits [2-9], return all possible letter combinations that the number could represent
 *  in any order
 *
 * Could be a backtracking thing
 *
 * ref: https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 * ref: https://leetcode.com/submissions/detail/482424761/
 * ref: https://www.youtube.com/watch?v=hR9jokO3a7E&list=PL1MJrDFRFiKZVF-cP3yHgzjCAUAqruPTu
 */
public class Backtrack {
    static Map<Character, String> phoneNumberMapping = new HashMap<>();
    static List<String> results = new ArrayList<>();

    public static void main(String[] args) {
        phoneNumberMapping.put('2', "abc");
        phoneNumberMapping.put('3', "def");
        phoneNumberMapping.put('4', "ghi");
        phoneNumberMapping.put('5', "jkl");
        phoneNumberMapping.put('6', "mno");
        phoneNumberMapping.put('7', "pqrs");
        phoneNumberMapping.put('8', "tuv");
        phoneNumberMapping.put('z', "wxyz");

        /// generate a list of all possible combinations of letter
        List<String> result = new Backtrack().letterCombinations("4102189282");
        // maps numbers to characters
        // goal -> as you loop thru the numbers you can access the letters that they reprsent



    }
    public List<String> letterCombinations(String digits){
        LinkedList<String> outputArray = new LinkedList<>();  // will act as queue

        if(digits.length() == 0){
            return outputArray;
        }

        String[] charMap = new String[]{
                "0",
                "1",
                "abc",  // 2
                "def",  // 3
                "ghi",   // 4
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };
        for (int i=0; i<digits.length(); i++){
            int index = Character.getNumericValue(digits.charAt(i));
            while ((outputArray.peek().length() == i)){
                String permutation = outputArray.remove();

                for (char c : charMap[index].toCharArray()){
                    outputArray.add(permutation + c);
                }
            }
        }
        //this.arr = digits.toCharArray();

        //Stack<Character> stack = new Stack<>();
        //helper(stack, 0);
        return outputArray;
    }

//    private void helper(Stack<Character> stack, int index) {
//        // check if we reach the bottom of the branch
//        char[] charMap = new char[26];  // maps numbers to characer
//
//        if(index == arr.length){
//            results.add(convertStackToString(stack));
//            return;
//        }
//
//        // DFS all the decisions
//        String curString = phoneNumberMapping.get(arr[index]);
//        for (char cur : curString.toCharArray()){
//            helper(stack, index+1);
//            stack.pop();
//        }
//        return;
//    }

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
