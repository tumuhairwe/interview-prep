package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * sort the elements such that the negative elements appear at the left
 * and positives on the right .... order doesn't matter
 */
public class CheckReArrange {
    public static void main(String[] args) {
        int[] arr = arr = new int[]{10, -1, 20, 4, 5, -9, -6};
        int[] result = reArrange(arr);
        System.out.println("The rearranged form is " + Arrays.toString(result));
    }
    public static int[] reArrange_method1(int[] arr){
        Arrays.sort(arr);
        return arr;
    }
    public static int[] reArrange(int[] arr){
        int mid = arr.length/2;

        for (int i = 0; i < mid; i++) {
            if (arr[i] < 0 ){   // f arr[i} is negative, swap with Nth index in array
                int temp = arr[mid + i];
                arr[mid + i] = arr[i];
                arr[i] = temp;
            }
        }

        return arr;
    }
}
