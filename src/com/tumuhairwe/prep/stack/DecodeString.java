package com.tumuhairwe.prep.stack;

import java.util.Stack;

/**
 * LeetCode 394 (medium)
 * Given an encoded string, return its decoded value.
 * The encoded rule is: k[enc_str], where the enc_str inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be positive integer.
 * You may assume that the input is always valid; there are no extra white spaces, square brackets are well-formed, etc,
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 *
 * For example there will not be input like 3a or 2[4].
 * The length of the ouput will never exceed 2^5
 * ref: https://leetcode.com/problems/decode-string/description/?envType=company&envId=apple&favoriteSlug=apple-three-months
 *
 * "Understanding Time Complexity: A guide for beginners"
 * ref: https://leetcode.com/discuss/post/5750835/understanding-time-complexity-a-guide-fo-aqci/
 * AlgoMonster flowchart: https://algo.monster/flowchart
 */
public class DecodeString {
    public String decodeString(String str) {
        //0. declare vars
        Stack<Character> stack = new Stack<>();
        for(char ch : str.toCharArray()){
            // reach non-digit chars
            if(ch == ']'){
                stack.push(ch);
            }
            else{
                // step 1: if you encounter ], reconstruct the string
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isLetter(stack.peek())){
                    sb.insert(0, stack.pop());
                }

                // step 2: get # of times it should repeat (from the stack)
                String strToRepeat = sb.toString();   // this is the string contained in [ ]
                stack.pop();    //discard the [

                // get number of times to repeat
                sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isDefined(stack.peek())){
                    sb.insert(0, stack.pop());
                }
                int numberOfTimes = Integer.parseInt(sb.toString());

                //step 3:
                //  - repeat the string within [] "count" number of times
                //  - push iot back into the stack;
                while (numberOfTimes-- > 0){
                    for (char c : strToRepeat.toCharArray()){
                        stack.push(c);
                    }
                }
            }
        }

        //step 4: reconstruct the string by fetching the chars from the stack
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()){
            ans.insert(0, stack.pop());
        }

        return ans.toString();
    }
}
