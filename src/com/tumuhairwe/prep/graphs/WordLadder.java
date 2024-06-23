package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 127 (hard) Word Ladder
 *
 * ref: https://leetcode.com/problems/word-ladder/description/?envType=problem-list-v2&envId=plakya4j
 * ref: https://leetcode.com/problems/word-ladder/solutions/4453863/easy-beginner-heavily-commented-6-stepapproach/?envType=problem-list-v2&envId=plakya4j
 */
public class WordLadder {

    /**
     * Solution summary
     * - base case: if endWord doesn't exist in wordList -> its impossible to go to destination i.e. exit
     * - create Set<String> of words (to avoid TimeLimitExceeded exception if there are too many duplicates in wordList .. look up will be expensive)
     * - Create and seed BFS queue with Entry(key=beginWord, value=stepCount 1)
     * - while !que.isEmpty()
     *      - poll() entry from que (call it currentWord)
     *      - if currentWord == endWord, we've reached destination -> return entry.step
     *      - generate permutation for each word by changing one letter at a time ... if permutation is in the wordList,
     *          -> add to queue and remove from geneBank (to prevent infinite loops)
     *      - while-loop will continue until end/destination is reached since each permutation will need to be checked against the bank.
     * - if we exit the loop (i.e. queue becomes empty before destination is reached ... return -1 --- impossible to reach destination)
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //0. edge case
        if(!wordList.contains(endWord)){
            return 0;   // there's no way to get to endWord
        }

        //1. store wordList in a set for efficient look up  --> also avoid TimeLimitExceeded Exceptions when there are lots of duplicate words in wordList
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
                        words.remove(newWord);  // to prevent re-visiting and creating infinite loops
                        que.add(new AbstractMap.SimpleEntry<>(newWord, step+1));
                    }
                }
            }
        }

        // if we've emptied the queue without finding endWord -> exit with 0
        return 0;
    }
}
