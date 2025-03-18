package com.tumuhairwe.prep.backtracking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Word Break II
 *
 * ref: https://leetcode.com/problems/word-break-ii/description/
 * ref: https://www.youtube.com/watch?v=QgLKdluDo08
 */
public class WordBreakII {
    public static void main(String[] args) {

    }

    //SC of trie: O(m * k) where m == number of words in dict & k == avg size of words
    private class TrieNode{
        private TrieNode[] children;
        private boolean isEndWord;
        public TrieNode(){
            children = new TrieNode[26];
        }
        public void insert(String word){
            TrieNode curr = this;
            for(char ch : word.toCharArray()){
                int indexOfA = ch - 'a';
                if(children[indexOfA] == null){
                    children[indexOfA] = new TrieNode();
                }
                curr = children[indexOfA];
            }

            curr.isEndWord = true;
        }

        public boolean search(String prefix){
            TrieNode curr = this;
            for(char ch : prefix.toCharArray()){
                int indexOfA = ch - 'a';
                if(children[indexOfA] == null){
                    return false;
                }
                curr = children[indexOfA];
            }

            return curr.isEndWord;
        }
    }
    // ref: https://algo.monster/liteproblems/140

    /**
     * TC: O(k x N^2) where n == number of words in dic
     * SC: O(M * K + N)
     */
    public List<String> wordBreak_trie(String s, List<String> wordDict) {
        // put all valid words in a trie
        TrieNode t = new TrieNode();
        for (String word : wordDict){
            t.insert(word);
        }

        // do dfs
        List<List<String>> combinations = depthFirstSearch(s);
        return combinations.stream()
                .map(words -> String.join(" ", words))
                .collect(Collectors.toList());
    }
    private TrieNode rootTrieNode = new TrieNode();
    private List<List<String>> depthFirstSearch(String str){
        List<List<String>> results = new ArrayList<>();
        // if string is empty, add an empty list (base case)
        if("".equals(str)){
            results.add(new ArrayList<>());
        }

        // break the string at different points to find valid words
        for (int i = 1; i <= str.length(); i++) {

            // check if the prefix is a valid word
            if(rootTrieNode.search(str.substring(0, i))){
                // recursively retrieve the remaining string
                for(List<String> suffixCombination : depthFirstSearch(str.substring(i))){
                    // add the valid word to the beginning of the list
                    suffixCombination.add(0, str.substring(0, i));

                    // add the new combination to the results
                    results.add(suffixCombination);
                }
            }
        }
        return results;
    }

    /**
     * Solution summary
     * - Convert wordDict list to set (to do constant-time lookups)
     * - Create que and seed with index 0 (for BFS)
     * - pull (startIdx) from queOfIndices
     *      - if startIdx is at end of string, return true
     *      - traverse string from startIdx to end-of-string
     *      - if index was visited already, skip
     *      - if fragment (substring from startIdx to endIdx) is in wordDict, mark as visited and add to que
     *
     * TC: O(n^2 + m x K) where n = length of string. m == length of wordDict, and K = avg length of words in wordDict
     * sc: O(n + m x K) == q.size and visited.size are N in the worst case, m + k == space for the set of words
     */
    public boolean wordBreak_BFS(String s, List<String> wordDict) {
        //0. init vars
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] visited = new boolean[s.length() + 1];
        Queue<Integer> queOfIndices = new LinkedList<>();
        queOfIndices.add(0);

        while (!queOfIndices.isEmpty()){
            int start = queOfIndices.remove();
            if(start == s.length()){
                return true;
            }

            for (int end=start + 1; end<=s.length(); end++){
                if(visited[end]){
                    continue;
                }
                if(wordSet.contains(s.substring(start, end))){
                    queOfIndices.add(end);
                    visited[end] = true;
                }
            }
        }

        return false;
    }

    /**
     * Solution summary (DFS on que of indices)
     * - create dq and seed with 0. init visited set
     * - while !dq.isEmpty()
     *      - if visited, skip
     *      - for each substring of s (from currentIdx up to s.length() inclusive)
     *      - if substring/fragment is in dictionary (either add idx to q or if-we've reached-end-of-string, return true
     * - return true if we exit look (i.e. didn't reach end of string & Q got empty)
     */
    public boolean wordBreak_BFS_II(String s, List<String> wordDict) {
        //1. declare vars
        Queue<Integer> queIndices = new ArrayDeque<>();
        queIndices.add(0);

        //2. track visited
        Set<Integer> visited = new HashSet<>();

        //do DFS of que
        while (!queIndices.isEmpty()){
            int currIdx = queIndices.poll();

            if(visited.contains(currIdx)){
                continue;
            }

            // for each substring, if its in dictionary, add idx to que. if we've reach end of str. return true;
            for (int i = currIdx + 1; i<=s.length(); i++) {
                String fragment = s.substring(currIdx, i);
                if(wordDict.contains(fragment)){
                    // check if we've reached of str .. if so, return true;
                    if(i == s.length()){
                        return true;
                    }
                    else {
                        queIndices.add(i);
                    }
                }

                // add currId to visited set
                visited.add(currIdx);
            }
        }

        // que became empty & we didn't reach end of word
        return false;
    }

    /**
     * Solution summary
     *
     */
    public List<String> wordBreak_backtrack(String s, List<String> wordDict) {
        //0. convert wordDict to set for O(1) lookup
        Set<String> wordSet = new HashSet<>(wordDict);

        List<String> results = new ArrayList<>();

        //1. start the backtracking process
        backtrack(s, 0, wordSet, new StringBuilder(), results);
        return results;
    }

    void backtrack(String str, int startIndex, Set<String> wordSet, StringBuilder currentSentence, List<String> results){

        //2. if we've reached the end of the string, add currentSentence to results
        if(startIndex == str.length()){
            results.add(currentSentence.toString().trim());
        }

        //3. iterate over possible indices
        for (int endIndex = startIndex + 1; endIndex <= str.length(); endIndex++) {
            //4. if word is in set, recurse with backtracking
            String word = str.substring(startIndex, endIndex);
            if(wordSet.contains(word)){
                int currentLength = currentSentence.length();

                currentSentence.append(word).append(" ");

                //5. recursively call backtrack with new index
                backtrack(str, endIndex, wordSet, currentSentence, results);

                //6. reset length to it original length
                currentSentence.setLength(currentLength);
            }
        }
    }
}
