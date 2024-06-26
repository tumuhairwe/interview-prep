package com.tumuhairwe.prep.array;

import java.util.Stack;

/**
 * LeetCode 224 (hard)
 *
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it
 * and return the result of the evaluation
 *
 * ref: https://www.youtube.com/watch?v=081AqOuasw0
 * ref: https://leetcode.com/problems/basic-calculator/description/
 */
public class BasicCalculator {

    static final int PLUS_SIGN = 1;
    static final int MINUS_SIGN = -1;

    public static void main(String[] args) {
        System.out.println("Should be 2 ->" + calculate("1 + 1"));
        System.out.println("Should be 3 -> " + calculate(" 2-1 + 2 "));
        System.out.println("Should be 23 -> " + calculate("(1+(4+5+2)-3)+(6+8)"));
    }
    public static int calculate(String s){
        //0. initialize vars
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        int operand = PLUS_SIGN;

        //1. iterate string left to right
        for (int i = 0; i < s.length(); i++) {
            //1a if its a number, read the number
            if(Character.isDigit(s.charAt(i))){
                int number = 0;

                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    //goal of operand is to apply the operand to the number
                    // e.g. xyz - 999 => xyz + -1 * 999 => ABC - 999
                    number = number * 10 + (Integer.parseInt(s.charAt(i) + ""));
                    i++;
                }
                sum = number * operand;    // operand is either -1 or 1

                // by this time, i is pointing to the digit after the number (last line of while loop)
                i--;    // move it back because it exited the while loop 1 index over
            }
            // case 2: + (if its a operand update it)
            else if(s.charAt(i) == '+'){
                operand = PLUS_SIGN;
            }
            // case 3: - (if its a operand update it)
            else if(s.charAt(i) == '-'){
                operand = MINUS_SIGN;
            }
            // case 4: if its a closing parenthesis
            else if(s.charAt(i) == '('){
                // save sum and operand to stack (order matters)
                stack.push(sum);
                stack.push(operand);

                // reset to defaults
                sum = 0;
                operand = PLUS_SIGN;
            }
            // case 5: if its a closing parenthesis, evaluate (top-of-stack is operand):
            else if(s.charAt(i) == ')'){
                sum = stack.pop() * sum;    // (pop SIGN from the stack) && (update currentSum)
                // add currentSum to previous sum (previous sum was on stack)
                sum += stack.pop();
            }
        }

        return sum;
    }
}
