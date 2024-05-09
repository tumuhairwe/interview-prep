package com.tumuhairwe.prep.array;

import java.io.*;
import java.util.*;

/**
 * Given an array arr of distinct integers, and a non-negative number K,
 * Write a function that returns and array of all pairs in arr [x, y],  such that x - y = k
 * If no such pair exists, return an empty array
 *
 1- Create a Map of {number, index}
 2- Sort the array
 3- Use 2 pointers to traverse the array and find x & 7
 4- Return output array that have above result
 
 input:  arr = [0, -1, -2, 2, 1], k = 1
 output: [[1, 0], [0, -1], [-1, -2], [2, 1]]
 
 map.put(diff-between-k_and arr[i], index_0)
 map.put(1, -1)
 map.put(2, -2)
 map.put(3, 2)
 map.put(3, 1)
 
 -- for each element , if element _ element_next_ind = target_k -> save in output_result[]

 ref: https://www.pramp.com/challenge/XdMZJgZoAnFXqwjJwnpZ
*/
class FindPairs {

  /*
                    *
  input:  arr = [0, -1, -2, 2, 1], k = 1

  Map: {0, 0}, {-1, 1}, {-2, 2}, {2, 3}, {1,4}

  Result = [target, i] [1, 0]
  */
  public static void main(String[] args) {
    int[] arr = new int[]{ 0, -1, -2, 2, 1};
    int k = 1;
    System.out.println(Arrays.toString(findPairsWithGivenDifference(arr, k)));
  }
  static int[][] findPairsWithGivenDifference(int[] arr, int k) {
//    int[][] result = new int[arr.length][2];
    List<int[]> result = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    
    // 0. populate char frequency map
    for(int i=0; i<arr.length; i++){
      map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
    }
    
    // 1. find the matching pairs (if k==0, count must be 2 or more
    for(Map.Entry<Integer,Integer> entry : map.entrySet()){
      if(k == 0){
        if(entry.getValue() > 1){
          int[] pair = new int[]{ entry.getValue(), entry.getValue() };
          result.add(pair);
        }
      }
      else if(map.containsKey(entry.getKey() + k)){
        int theSmaller = Math.min(entry.getKey(), entry.getKey() + k);
        int theBigger = Math.max(entry.getKey(), entry.getKey() + k);
        int[] pair = new int[]{ theBigger, theSmaller };
        result.add(pair);
      }
    }
    
    return (int[][])result.toArray();
  }

}