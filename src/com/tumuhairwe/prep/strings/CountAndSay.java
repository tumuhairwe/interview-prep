package com.tumuhairwe.prep.strings;

/**
 * LeetCode 38. (medium)
 *
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 *
 * countAndSay(1) = "1"
 * countAndSay(n) is the run-length encoding of countAndSay(n - 1).
 * Run-length encoding (RLE) is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "3322251" we replace "33" with "23", replace "222" with "32", replace "5" with "15" and replace "1" with "11". Thus the compressed string becomes "23321511".
 *
 * Given a positive integer n, return the nth element of the count-and-say sequence.
 *
 * ref: https://leetcode.com/problems/count-and-say/description/
 */
public class CountAndSay {

    public static void main(String[] args) {
        int i = 1;
        System.out.println("should be 1 " + countAndSay(1));

        int x = 4;
        System.out.println("should be 1211 " + countAndSay(4));
    }
    public static String countAndSay(int n){
        if(n == 1){
            return "1";
        }
        
        String prev = countAndSay(n - 1);
        String result = "";
        int frequency = 1;
        for (int i = 0; i < prev.length(); i++) {
            if(i == prev.length() - 1 || prev.charAt(i) != prev.charAt(i + 1)){
                result += frequency;
                result += prev.charAt(i);
                frequency = 1;
            }
            else {
                frequency++;
            }
        }

        return result;
    }
}
