package com.tumuhairwe.prep.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array of integers, arr, and an integer, k, return the K
 *  most frequent elements.
 *
 *  Solution:
 *  - Create a MinHeap (PQ of Map.Entry) of size K
 *  - Create a HashMap of to store the frequency of each element <Integer, Integer>
 *  - Iterate thru the array's elements and update each elements frequency in the HashMap
 *  - For each element in the Map, insert a Key-Value pair of (element, frequency) into the heap
 *  - If the heap exceeds size K, pop()/poll()/remove the minimum element until it reaches size K
 *  - After processing all elements in array, Heap should have the elements with the highest frequency
 *
 *  TC = Iterating = O( n )
 *       Inserting = O( log_k )
 *
 *   if k < n .. TC = O(n x log(k) )
 *   else if k = n ... TC = O(N x log(n) )
 */
public class KMostFrequentElements {
    public static List<Integer> topKFrequent(int[] arr, int k) {
        // these 2 are the same
        Comparator<Map.Entry<Integer, Integer>> c = (e1, e2) -> e1.getValue() - e2.getValue();
        Comparator<Map.Entry<Integer, Integer>> comparator = Comparator.comparingInt(Map.Entry::getValue);

        PriorityQueue<Map.Entry<Integer, Integer>> topKElements = new PriorityQueue<>(comparator);

        Comparator<Map.Entry<Integer, Integer>> comp = Comparator.comparingInt(Map.Entry::getValue);
        PriorityQueue<Map.Entry<Integer,Integer>> topKElements_pq = new PriorityQueue<>(comp);
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();

        for(int i=0; i<arr.length; i++){
            int number = arr[i];
            int existingFreq = numFrequencyMap.getOrDefault(number, 0);

            numFrequencyMap.put(number, existingFreq + 1);
        }

        for (Map.Entry<Integer, Integer> entry : numFrequencyMap.entrySet()) {
            topKElements.add(entry);
            if (topKElements.size() > k) {
                topKElements.poll();
            }
        }

        // this is same as above
        /*
        numFrequencyMap.entrySet().stream()
                .forEach(entry -> {
                    Map.Entry<Integer, Integer> e = new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue());

                    if(topKElements_pq.size() < k){
                        topKElements_pq.add(e);
                    }
                    else {
                        if(topKElements_pq.peek().getValue() > e.getValue()){
                            topKElements_pq.poll();
                            topKElements_pq.add(e);
                        }
                    }
                });
        */
        List<Integer> setOfMostFrequent = topKElements_pq.stream().map(e -> e.getValue()).collect(Collectors.toList());
        return setOfMostFrequent;
    }
}
