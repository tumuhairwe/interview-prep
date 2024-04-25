package com.tumuhairwe.prep;

import java.util.Arrays;

// Time complexity = O(n + n) => O(n)
public class CheckSecondMax {
    // method-1: Use Arrays.sort() to sort
    public static int findSecondMaximum(int[] arr) {
        //Arrays.sort(arr, Collections.reverseOrder());
        Arrays.sort(arr);
        return arr[arr.length - 2];  // = 2nd largest
    }
    public static int findSecondMax(int[] arr){
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        // 0. traverse array, find max
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }

        // traverse array,
        //  a) find number less than MAX ...
        //  b) greater than 2nd max
        //  c) but != previously calculated 2nd-max
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > secondMax && arr[i] < max){
                secondMax = arr[i];
            }
        }

        // return secondMax
        return secondMax;
    }

    public static void main(String[] args) {
        int [] arr = {9,2,3,6};
        //int s = findSecondMaximum(arr);
        int s = findSecondMax(arr);

        System.out.println("The 2nd mx number is " + s);
    }
}
