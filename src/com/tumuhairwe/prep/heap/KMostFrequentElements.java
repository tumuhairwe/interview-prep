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
 *  - Iterate through the array's elements and update each elements frequency in the HashMap
 *  - For each element in the Map, insert a Key-Value pair of (element, frequency) into the heap
 *  - If the heap exceeds size K, pop()/poll()/remove the minimum element until it reaches size K
 *  - After processing all elements in array, Heap should have the elements with the highest frequency
 *  - Transform results of heap into int[] and return
 *
 *  TC = Iterating = O( n )
 *       Inserting = O( log_k )
 *
 *   if k < n .. TC = O(n x log(k) )
 *   else if k = n ... TC = O(N x log(n) )
 *
 *   ref: https://leetcode.com/problems/top-k-frequent-elements/description/
 *   ref: https://www.youtube.com/watch?v=YPTqKIgVk-k
 *
 */
public class KMostFrequentElements {
    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,2,2,3};
        int k = 2;
        int[] result = topKFrequent(arr, k);
        System.out.println(Arrays.toString(result));
    }

    public static int[] topKFrequent(int[] arr, int k) {
        //0. create frequency map
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();

        for (int number : arr){
            int existingFreq = numFrequencyMap.getOrDefault(number, 0);
            numFrequencyMap.put(number, existingFreq + 1);
        }

        // these 2 are the same
        //Comparator<Map.Entry<Integer, Integer>> c = (e1, e2) -> e1.getValue() - e2.getValue();
        //Comparator<Map.Entry<Integer, Integer>> comparator = Comparator.comparingInt(Map.Entry::getValue);

        //2. put entries of frequency map in pq (sorted by value/frequency) ... when size exceeds K ... poll() off the top most entry
        Comparator<Map.Entry<Integer, Integer>> comp = Comparator.comparingInt(Map.Entry::getValue);
        PriorityQueue<Map.Entry<Integer,Integer>> topKElements_pq = new PriorityQueue<>(comp);

        for (Map.Entry<Integer, Integer> entry : numFrequencyMap.entrySet()) {
            topKElements_pq.add(entry);
            if (topKElements_pq.size() > k) {
                topKElements_pq.poll(); // TC: log_n (when popped k times == O(K log_n)
            }
        }

        // 3. convert remaining entries of pq into array (if pq is not empty)
//        int i = k;
//        int[] result = new int[k];
//        while(!topKElements_pq.isEmpty()){
//            result[--i] = topKElements_pq.poll().getKey();
//        }
        List<Integer> results = topKElements_pq.stream().map(e -> e.getKey()).collect(Collectors.toList());
        int[] result = results.stream().mapToInt(e -> e).toArray();
        return result;
    }
}
