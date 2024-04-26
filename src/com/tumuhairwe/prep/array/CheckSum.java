package com.tumuhairwe.prep.array;

import java.util.Arrays;

// Find Two Numbers that Add up to "n"
public class CheckSum {
    // solution 1: brute force O(n^2)
    public static int[] findSum_bruteForce(int[] arr, int n) {
        int[] result = new int[2];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(arr[j] + arr[i] == n){
                    result = new int[]{arr[j], arr[i]};
                }
            }
        }
        return result;   // return the elements in the array whose sum is equal to the value passed as parameter
    }

    public static void main(String[] args) {
        int[] arr = {1, 21, 3, 14, 5, 60, 7, 6};
        int value = 27;
        int[] result = findSum(arr, value);

        System.out.println("Result");
        System.out.println(Arrays.toString(result));
    }

    // Sort the array first
    //Helper Function to sort given Array (Quick Sort)
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is <= to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    //Return 2 elements of arr that sum to the given value
    public static int[] findSum(int[] arr, int n) {
        //Helper sort function that uses the Quicksort Algorithm
        sort(arr, 0, arr.length - 1);   //Sort the array in Ascending Order

        int Pointer1 = 0;    //Pointer 1 -> At Start
        int Pointer2 = arr.length - 1;   //Pointer 2 -> At End

        int[] result = new int[2];
        int sum = 0;

        while (Pointer1 != Pointer2) {

            sum = arr[Pointer1] + arr[Pointer2];  //Calulate Sum of Pointer 1 and 2

            if (sum < n)
                Pointer1++;  //if sum is less than given value => Move Pointer 1 to Right
            else if (sum > n)
                Pointer2--;
            else {
                result[0] = arr[Pointer1];
                result[1] = arr[Pointer2];
                return result; // containing 2 number
            }
        }
        return arr;
    }
}
