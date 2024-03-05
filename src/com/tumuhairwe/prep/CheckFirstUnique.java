package com.tumuhairwe.prep;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// First non-repeating integer
// Algo: use Map<Integer, Integer> -> Integer , countOfOccurrences
// a) loop thru array & initialize count to Zero
// b) loop thru array and increment to existingCount+1
// c) return entry.key with countOfOccurrences <= 1
public class CheckFirstUnique {
    public static int findFirstUnique(int[] arr)
    {
        Map<Integer, Integer> countOccurrences = new HashMap<>();
        for(int i=0; i< arr.length; i++){
            countOccurrences.put(arr[i], 0);
        }
        for(int i=0; i< arr.length; i++){
            countOccurrences.put(arr[i], countOccurrences.get(arr[i]) + 1);
        }
        for(int i=0; i< arr.length; i++){
            if(countOccurrences.get(arr[i]) <= 1){
                return arr[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {9, 2, 3, 2, 6, 6};
        int result = findFirstUnique(arr);
        System.out.println("Result");
        System.out.println(result);
    }
}
