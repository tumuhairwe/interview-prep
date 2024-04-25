package com.tumuhairwe.prep;

import java.util.Arrays;

/// arrange the elements of a sorted array in such a way that
// 1st position = largest number
// 2nd position = smallest
// 3rd position = 2nd largest
public class CheckMaxMin {

    // Space complexity = O(1) for storing extra space-time complexity = O(n) for iterating over entire array
    public static void main(String[] args) {
        final int[] arr = {1, 2, 3, 4, 5};
        int [] result = minMax(arr);
        System.out.println("The rearranged form is " + Arrays.toString(result));
    }
    public static int[] minMax(int[] arr){
        int[] result = new int[arr.length]; // 0. create empty array

        int pointerSmall = 0;       // 1. create 2 pointers that will move independently
        int pointerLarge = arr.length -1;
        boolean switchPointer = true;   // 2. define condition for switching pointers

        for (int i = 0; i < arr.length; i++) {  // 3. every iteration,
            if(switchPointer)
                result[i] = arr[pointerLarge--];    // 1=store LARGEST into index of results. Then decrement pointer
            else
                result[i] = arr[pointerSmall++];    // 1=store SMALLEST into index of results. Then increment pointer

            switchPointer = !switchPointer; // invert condition for switch pointer
        }

        return result;
    }
}
