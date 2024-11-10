package com.tumuhairwe.prep.strings;

/**
 * LeetCode 1189 (easy)
 * Given a string tex, use the characters of text to form as many instances of the word "balloon" as possible
 * You can use each character in text at most once. Return the max number of instances that be formed
 *
 * ref: https://leetcode.com/problems/maximum-number-of-balloons/description/
 */
public class MaxNumberOfBalloons {
    public static void main(String[] args) {
        System.out.println(maxNumberOfBalloons("nlaebolko"));
        System.out.println(maxNumberOfBalloons("loonbalxballpoon"));
        System.out.println(maxNumberOfBalloons("leetcode"));
    }
    public static int maxNumberOfBalloons(String text){
        //0. create freqMap of text
        int[] freqMap_text = new int[26];
        for (char ch : text.toCharArray()){
            freqMap_text[ch - 'a']++;
        }

        //1. create freqMap of balloon
        int[] freqMap_balloon = new int[26];
        for (char ch : "balloon".toCharArray()){
            freqMap_balloon[ch - 'a']++;
        }

        //2. find min intersection
        int max = 0;
        for (char ch : "balloon".toCharArray()){
            int freq = freqMap_text[ch - 'a'] / freqMap_balloon[ch - 'a'];
            max = Math.min(max, freq);
        }
        return max;
    }
}
