package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 127 (hard) Word Ladder
 *
 * ref: https://leetcode.com/problems/word-ladder/description/?envType=problem-list-v2&envId=plakya4j
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //0. edge case
        if(!wordList.contains(endWord)){
            return 0;   // there's no way to get to endWord
        }

        //1. store wordList in a set for efficient look up
        Set<String> words = new HashSet<>(wordList);
        words.remove(beginWord);

        //2. create and see queue for BFS traversal that will store Entry<String, Integer> of word + count_of_steps
        Queue<Map.Entry<String, Integer>> que = new LinkedList<>();
        que.offer(new AbstractMap.SimpleEntry<>(beginWord, 1));

        while (!que.isEmpty()){
            //2.0 dequeue currentWord from que
            Map.Entry<String, Integer> currentWord = que.poll();
            String word = currentWord.getKey();
            Integer step = currentWord.getValue();

            //2.1 if we've reached th end, return
            if(word.equals(endWord)){
                return step;
            }

            //2.2 generate all possible transformations of word, if 1 is in wordList, add it to queue
            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <='z'; ch++){
                    char[] oldWord = word.toCharArray();
                    oldWord[i] = ch;
                    String newWord = new String(oldWord);

                    //2.3 check if it exists in wordList
                    if(words.contains(newWord)){
                        words.remove(newWord);
                        que.add(new AbstractMap.SimpleEntry<>(newWord, step+1));
                    }
                }
            }
        }

        // if we've emptied the queue without finding endWord -> exit with 0
        return 0;
    }
}
