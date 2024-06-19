package com.tumuhairwe.prep.strings;

/**
 * LeetCode 58 (easy) Length of Last Word
 *
 * ref: https://leetcode.com/problems/length-of-last-word/description/
 */
public class LengthOfLongestWord {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
    }

    /**
     * Solution summary
     * - split the word (use regex for space-separator or use stringBuilder if regex isn't allowed)
     * - split should generate array of strings
     * - get the length of the last word in the string
     */
    public static int lengthOfLastWord(String s) {
        if(s == null || s.trim().length() == 0){
            return 0;
        }

        String[] arr = new String[100];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.toCharArray().length; i++) {
            char c = s.toCharArray()[i];
            if(c == ' ' || i == s.toCharArray().length - 1){    // is space of is last iteration
                arr[i] = sb.toString();
                sb = new StringBuilder();
            }
            else{
                sb.append(c);
            }
        }



        String[] str = s.trim().split("\\s");
        return str[str.length - 1].trim().length();
    }
}
