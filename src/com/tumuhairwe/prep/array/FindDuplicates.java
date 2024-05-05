package com.tumuhairwe.prep.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Find The Duplicates
 * Given two sorted arrays arr1 and arr2 of passport numbers, implement a function findDuplicates that returns an array of all passport numbers that are both in arr1 and arr2. Note that the output array should be sorted in an ascending order.
 *
 * Let N and M be the lengths of arr1 and arr2, respectively. Solve for two cases and analyze the time & space complexities of your solutions: M ≈ N - the array lengths are approximately the same M ≫ N - arr2 is much bigger than arr1.
 *
 * Example:
 *
 * input:  arr1 = [1, 2, 3, 5, 6, 7], arr2 = [3, 6, 7, 8, 20]
 *
 * output: [3, 6, 7] # since only these three values are both in arr1 and arr2
 * Constraints:
 *
 * [time limit] 5000ms
 *
 * [input] array.integer arr1
 *
 * 1 ≤ arr1.length ≤ 100
 * [input] array.integer arr2
 *
 * 1 ≤ arr2.length ≤ 100
 * [output] array.integer
 *
 * LeetCode 2956
 * ref: https://leetcode.com/problems/find-common-elements-between-two-arrays/description/
 */
public class FindDuplicates {

    public static void main(String[] args) {

//        int[] arr1 = new int[]{1, 2, 3, 5, 6, 7};
//        int[] arr2 = new int[]{3, 6, 7, 8, 20};
//        System.out.println("Given " + Arrays.toString(arr1) + " and " + Arrays.toString(arr2));
//        System.out.println("1. The duplicates are " + Arrays.toString(findDuplicates(arr1, arr2)));
//
//        arr1 = new int[]{3,4,2,3};
//        arr2 = new int[]{1, 5};
//        System.out.println("Given " + Arrays.toString(arr1) + " and " + Arrays.toString(arr2));
//        System.out.println("The duplicates are " + Arrays.toString(findDuplicates(arr1, arr2)));
    }
    static int[] findDuplicates(int[] arr1, int[] arr2) {
        int pointer_a = 0;
        int pointer_b = 0;
        Set<Integer> duplicates = new HashSet<>();

        while(pointer_a < arr1.length && pointer_b < arr2.length){

            int a1 = arr1[pointer_a];
            int a2 = arr2[pointer_b];

            if(a1 == a2){
                duplicates.add(a1);

                pointer_a++;
                pointer_b++;
            }
            else if(a1 < a2){
                pointer_a++;
            }
            else {
                pointer_b++;
            }
        }

        int[] output_arr = new int[duplicates.size()];
        int ind = 0;
        for(Integer ele : duplicates){
            output_arr[ind] = ele;
            ind++;
        }
        return output_arr;
    }
}
