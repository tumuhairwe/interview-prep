package com.tumuhairwe.prep.trie;

/**
 * LeetCode 2416 (hard)
 * You are given an array words of size n, consisting of non-empty strings
 * The score of a string is defined as the number strings words[i] such that term is a prefix of words[i].
 *
 * For example, if words = ["a", "ab", "abc", "cab"], then the score of "ab" is 2, since "ab" is a prefix of both "ab" and "abc".
 * Return an array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i].
 *
 * Note that a string is considered as a prefix of itself.
 *
 * ref: https://leetcode.com/problems/sum-of-prefix-scores-of-strings/description/
 */
public class SumOfPrefixScoresOfString {
    class TrieNode{
        TrieNode[] children;
        int count;
        TrieNode(){
            this.children = new TrieNode[26];
            this.count = 0;
        }
    }

    TrieNode root;
    public SumOfPrefixScoresOfString(){
        root = new TrieNode();
    }

    void insert(String word){
        TrieNode curr = root;
        for(char ch : word.toCharArray()){
            int idx = ch - 'a';
            if(curr.children[idx] == null){
                curr.children[idx] = new TrieNode();
            }
            // forward
            curr = curr.children[idx];

            // increment
            curr.count++;
        }
    }
    int count(String prefix){
        int sum = 0;
        TrieNode curr = root;
        for (char ch : prefix.toCharArray()){
            int idx = ch - 'a';
            sum += curr.count;
            curr = curr.children[idx];
        }
        return sum;
    }

    /**
     * Solution summary:
     * - Create a TrieNode class (with children[] and int count)
     * - implement insert() in solution class
     * - implement count() in solution class
     * - create a root TrieNode
     * - for each String in words, insert (solution.insert(word)
     * - Create ans[] --- for reach word in words[i], call getCount(words[i0);
     * - return ans
     *
     * TC: O(N⋅M) where n = words.length && m = average length of strings in words
     * SC: O(N⋅M)
     */
    public int[] sumPrefixScores(String[] words){
        root = new TrieNode();
        for (String w : words){
            insert(w);
        }

        int[] ans = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            ans[i]= count(words[i]);
        }

        return ans;
    }
}
