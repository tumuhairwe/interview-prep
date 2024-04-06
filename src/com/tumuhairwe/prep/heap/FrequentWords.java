package com.tumuhairwe.prep.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a string array, words, and an integer k, return the* k
 *  most frequent strings.
 *
 *  LeetCode 347 (medium)
 *  ref: https://leetcode.com/problems/top-k-frequent-elements/description/
 *
 * Sort the frequencies from highest to lowest and then return the top k
 *  frequent words. Words with the same frequency should be sorted by their lexicographical order.
 */
public class FrequentWords {

    // TC = in O(n + klogn)
    // SC= O(n) space
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freqMap = new HashMap<>();
        for(int i=0; i<words.length; i++){
            int existingCount = freqMap.getOrDefault(words[i], 0);
            freqMap.put(words[i], existingCount+1);
        }

        //Comparator<Map.Entry<String, Integer>> comp = (Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) -> e1.getValue() - e2.getValue();
        Comparator<Map.Entry<String, Integer>> comp = Comparator.comparingInt(Map.Entry::getValue);

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue(comp);
        freqMap.entrySet()
                .stream()
                .forEach(entry -> {
                    if(pq.size() <= k){
                        pq.add(entry);
                    }
                    else{
                        if(pq.peek().getValue() > entry.getValue()){
                            pq.poll();
                            pq.add(entry);
                        }
                    }
                });

        //List<Long> results = null;   //pq.stream().map(e -> e.getKey()).collect(Collectors.toList());
        //int[] arr = results.stream().mapToInt(e -> e).toArray();
        return pq.stream().map(e -> e.getKey()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int k = 4;
        String[] arr = new String[]{ "the", "day", "is", "sunny", "the", "the","the", "sunny", "is", "is"};
        List<String> results = topKFrequent(arr, k);
        System.out.println("The most " + k + "frequent words: " + results);
    }
}
