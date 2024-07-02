package com.tumuhairwe.prep.strings;

import java.util.Stack;

/**
 * LeetCode 120 (medium)
 */
public class ReversePolishNotation {
    public static void main(String[] args) {
        String[] tokens = {"2","1","+","3","*"};
        System.out.println("Should be 9: " + evalRPN(tokens));

        tokens = new String[]{"4","13","5","/","+"};
        System.out.println("Should be 6: " + evalRPN(tokens));

        tokens= new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println("Should be 22: "  + evalRPN(tokens));
    }

    /**
     * Solution summary (Use stack)
     * - Create and seed stack with initial val (zzero-th index)
     * - iterate thru string from 1-th index ...
     *      - if the char is a number, stack.psuh() and continue
     *      - if stack is a character, pop 2 most recent entries from stack
     *
     *      - if stack is a + or *, perform operation in order (a + b) or(a * b)
     *      - if stack is a / or i, perform operation post-order (b - a) or (b / a)
     *
     *      - push result of operation on to stack
     * - At end, stack should have only 1 entry (return it)
     */
    private static int evalRPN(String[] tokes) {
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.parseInt(tokes[0]));

        for (int i = 1; i < tokes.length; i++) {
            String s = tokes[i];
            int x = 0;

            try{
                x = Integer.parseInt(s);
                stack.push(x);
                continue;
            }catch (Exception e){}

            int result = 0;
            int a = stack.pop();
            int b = stack.pop();
            if(s.charAt(0) == '+'){
                result = a + b;
            }
            else if(s.charAt(0) == '*'){
                result = a * b;
            }
            else if(s.charAt(0) == '/'){
                result = b/a;
            }
            else if (s.charAt(0) == '-'){
                result= b - a;
            }

            stack.push(result);
        }

        return stack.pop();
    }
}
