package com.tumuhairwe.prep.strings;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * LeetCode 2287 (easy)
 * You are given two 0-indexed strings s and target. You can take some letters from s and rearrange them to form new strings.
 *
 * Return the maximum number of copies of target that can be formed by taking letters from s and rearranging them.
 */
public class RearrangeCharactersToMakeTarget {
    public static void main(String[] args) {
        System.out.println(rearrangeCharacters("ilovecodingonleetcode", "code"));
        System.out.println(rearrangeCharacters("abcba", "abc"));
        System.out.println(rearrangeCharacters("abbaccaddaeea", "aaaaa"));
    }
    public static int rearrangeCharacters(String s, String target) {
        //0. create freqCount of s
        int[] freqCount_s = new int[26];
        for (char ch : s.toCharArray()){
            freqCount_s[ch - 'a']++;
        }

        //1. create freqCount of s
        int[] freqCount_target = new int[26];
        for (char ch : target.toCharArray()){
            freqCount_target[ch - 'a']++;
        }

        //Deque<Integer> deque = new ArrayDeque<>();
        //Queue<Integer> queue = new ArrayDeque<>();

        //2. divide frequency of  s/target -> track min
        int result = Integer.MAX_VALUE;
        for(char ch : target.toCharArray()){
            int div = freqCount_s[ch - 'a'] / freqCount_target[ch - 'a'];
            result = Math.min(result, div);
        }

        return result;
    }
}
