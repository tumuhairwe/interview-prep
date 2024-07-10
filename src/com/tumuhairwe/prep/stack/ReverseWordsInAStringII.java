package com.tumuhairwe.prep.stack;

import java.util.Stack;

public class ReverseWordsInAStringII {
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();
        for(String str : s.split("\\s")){
            if(str == null || str.length() == 0){
                continue;
            }

            stack.push(str);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        return sb.toString().trim();
    }
}
