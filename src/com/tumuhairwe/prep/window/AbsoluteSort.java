package com.tumuhairwe.prep.window;

import java.util.Arrays;

public class Solution {

    static int[] absSort(Integer[] arr) {
        int[] aaa = new int[10];
        arr = aaa;
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

    public static void main(String[] args) {

    }
}
