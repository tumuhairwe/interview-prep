package com.tumuhairwe.prep.heap;

import java.util.*;

/**
 * LeetCode 692
 * Given an array of strings & an integer k, return the most frequent strings
 *
 * Similar to LeetCode 347 (FrequentWords)
 *
 * Return the answer sorted by the frequency from the highest to the lowest.
 * Sort the words with same frequency by their lexicographical order
 */
public class KMostFrequentWords {

    /**
     * Solution summary
     * - Create freqMap
     * - implement comparator that sorts by frequency (from map) && falls back to String.compareTo() when frequency is equal
     * - Create pq that uses comparator from above
     * - iterate thru words[] & add each to pq .... evict/poll() from pq when pq.size() > k
     * - at the end when pq.size() == k ... ITERATIVELY collect pq entries in to list
     * - Reverse-sort the resultList
     * - return the resultList
     *
     * - TC: O(n log_k) : n == size words.length, k = k
     * - SC: O(n)
     */
    public List<String> topKFrequent(String[] words, int k) {
        //0. create freqMap of words
        Map<String, Integer> freqMap = new HashMap<>();
        for(String w : words){
            freqMap.put(w, freqMap.getOrDefault(w, 0) + 1);
        }

        //1. create pq and sort by custom comparator
        Comparator<String> comp = (String s1, String s2 ) -> {
            int f1 = freqMap.get(s1);
            int f2 = freqMap.get(s2);

            return (f1 == f2) ? s1.compareTo(s2) : f1- f2;
        };
        PriorityQueue<String> pq = new PriorityQueue<>(comp);
        for(int i=0; i< words.length; i++){
            pq.offer(words[i]);
            if(pq.size() > k){
                pq.poll();
            }
        }

        //2. collect to List and sort in reverse
        List<String> resultList = new ArrayList<>();
        // don't use pq.stream().collect() bcoz it order is undetermined (as opposed to pq.poll()
        while (!pq.isEmpty()){
            resultList.add(pq.poll());
        }

        Collections.reverse(resultList);
        return resultList;
    }
}
