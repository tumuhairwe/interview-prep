package com.tumuhairwe.prep.graphs;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 97 (medium)
 *
 * Given string s1, s2 and s3, find whether s3 is formed by interleaving of s1 and s2
 *
 * An interleaving of 2 strings s and t is a config where s and t are divided into n and m
 * uch that:
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 *
 * ref: https://leetcode.com/problems/interleaving-string/?envType=problem-list-v2&envId=plakya4j
 */
public class InterleavingString {
    public static void main(String[] args) {
        System.out.println("Should be false: " + isInterleave("aabcc", "dbbca", "aadbbcbcac"));

        System.out.println("Should be true: " + isInterleave("", "", ""));

        System.out.println("Should be false: " + isInterleave("a", "b", "a"));
    }
    public static boolean isInterleave(String s1, String s2, String s3){
        int s1Length = s1.length();
        int s2Length = s2.length();

        //0. base case: lengths must match
        if(s1Length + s2Length != s3.length()){
            return false;
        }

        //1. create and seed queue
        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{0, 0});

        //1. create and seed 2D visited array
        boolean[][] visited = new boolean[s1Length + 1][s2Length + 1];

        while (!que.isEmpty()){
            // 2. poll index from que
            int[] coord = que.poll();
            int i = coord[0];
            int j = coord[1];

            //3. we've reached the base case
            if(i + j == s3.length()){
                return true;
            }

            //4 check str1
            if(i < s1Length && s1.charAt(i) == s3.charAt(i + j) && !visited[i + 1][j]){
                que.offer(new int[]{i + 1, j});
                visited[i + 1][j] = true;
            }

            //5 check str2
            if(j < s2Length && s1.charAt(j) == s3.charAt(i + j) && !visited[i][j + 1]){
                que.offer(new int[]{i, j + 1});
                visited[i][j + 1] = true;
            }
        }
        return false;
    }
}
