package com.tumuhairwe.prep.other;

import java.util.Arrays;

/**
 * Pancake Sort
 * Given an array of integers arr:
 *
 * Write a function flip(arr, k) that reverses the order of the first k elements in the array arr.
 * Write a function pancakeSort(arr) that sorts and returns the input array.
 * You are allowed to use only the function flip you wrote in the first step in order to make changes in the array.
 * Example:
 *
 * input:  arr = [1, 5, 4, 3, 2]
 *
 * output: [1, 2, 3, 4, 5] # to clarify, this is pancakeSort's output
 * Analyze the time and space complexities of your solution.
 *
 * Note: it’s called pancake sort because it resembles sorting pancakes on a plate with a spatula,
 * where you can only use the spatula to flip some of the top pancakes in the plate.
 * To read more about the problem, see the Pancake Sorting Wikipedia page.
 *
 * Constraints:
 *
 * [time limit] 5000ms
 *
 * [input] array.integer arr
 *
 * [input] integer k
 *
 * 0 ≤ k
 * [output] array.integer
 *
 * ref: https://medium.com/problem-solving-coding/absolute-value-array-sort-174df7272ee
 * ref: https://www.pramp.com/challenge/4E4NW7NjbnHQEx1AxoXE
 */
public class AbsoluteSort {

    static Integer[] absSort(Integer[] arr) {
        // your code goes here
        //  [2, -7, -2, -2, 0]
        int startIndex = 0;
        int endIndex = arr.length - 1;

        Arrays.sort(arr,  (a, b) -> {

            int left = Math.abs(arr[startIndex]); // -7 => 7
            int right = Math.abs(arr[endIndex]);  // -2 => 2

            if(left > right){
                return  -1;
            }
            else if(left == right){
                int val_a = arr[startIndex]; // 2
                int val_b = arr[endIndex];   // -2

                if(val_a > val_b){
                    return -1;
                }
            }
            return 1;
        });

        return arr;
    }


    static int[] absSort2(int[] arr) {
        // your code goes here
        //  [2, -7, -2, -2, 0]
        Integer[] boxedArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        Arrays.sort(boxedArr,  (a,b) -> {

            int left = Math.abs(arr[a]); // -7 => 7
            int right = Math.abs(arr[b]);  // -2 => 2

            if(left > right){
                return  -1;
            }

            else if(left == right){
                int val_a = arr[a]; // 2
                int val_b = arr[b];   // -2

                if(val_a > val_b){
                    return -1;
                }
            }
            return 1;
        });

        for( int i=0; i<boxedArr.length;i++){
            arr[i]=boxedArr[i];
        }
        return arr;

    }
    public static void main(String[] args) {

    }
}
