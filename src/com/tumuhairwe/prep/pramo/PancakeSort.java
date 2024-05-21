package com.tumuhairwe.prep.pramo;

import java.io.*;
import java.util.*;

class PancakeSort {

    /*
      0. check to make 0 < k <arr.length
    * 1. iterate the array from K -> 0
      2. swap 0 with k, 1, k-1, 2, k-2 --. until middel
    */
    static int[] flip(int[] arr, int k) {
        // your code goes here
        // [1,2,3,4,5], k =2
        // [2,1,3,4,5]
        int mid = k / 2;
        for(int i= k - 1; i >= 0; i--){

            if(i == mid){
                break;
            }

            // [1, 2, 3, 4, 5, 6, 7, 8]... k = 5,

            // k = 2
            // [2,1, ...]

            // Iteration1 = Swaping 0 index with index 1 = [2,1, ...]
            // Iteration2 = Swap 1 and 0 [1,2, ...]
            System.out.println("I=" + i);
            System.out.println("arr[i]: " + arr[i]);

            int complement = k-i - 1;     // ind=2 val = 3  => complement = 0
            int temp = arr[i];      // temp=4
            arr[i] = arr[complement]; // ind[1] = 1 -> arr[4] = 0
            arr[complement] = temp;            // arr[0] = 4 1 => arr[1]

            //System.out.println("complement:" + complement);

        }

        return arr;
    }

    static int[] pancakeSort(int[] arr) {
        // your code goes here
    /*
      Input = [10, 1, 5, 4, 3, 2]
      Output = [1, 2, 3, 4, 5, 10]

      Sort using this helper function flip()

      Given: [1, 5, 4, 3, 2]
      when i = k-1; = 4
      MaxNum = 5; , index = 1 ( arr[1] )
     flip -> [5, 1, 4, 3, 2]    == flip(arr, index+1)
     flip -> [2, 3, 4, 1, 5]    == flip(arr, i+1) = flip(arr, 5)


     [2, 3, 4, 1, 5]
     i = 3
     find maximum upto index i,  MaxNum = 4, index= 2
     flip(arr, maxIndex+1) = flip(arr, 3) = [4, 3, 2, 1, 5]
     flip(arr, i+1) = flip(arr, 4) = [1,2,3,4,5]


    */
        int beginIndex = 0;
        int endIndex = beginIndex + 1;
        int n = arr.length;
        for(int i=n-1; i >= 0; i--){
            // Find maximumIndex in array unto index i
            int max = arr[i];
            int maxIndex = i;
            for(int j=0; j<=i; j++){
                if(arr[j] > max){
                    max = arr[j];
                    maxIndex = j;
                }
            }
            System.out.println("maxIndex:" + maxIndex);
            System.out.println("i:" + i);
            //  find maximum upto index i,  MaxNum = 4, index= 2
            //  flip(arr, maxIndex+1) = flip(arr, 3) = [4, 3, 2, 1, 5]
            //  flip(arr, i+1) = flip(arr, 4) = [1,2,3,4,5]
            // First flip
            flip(arr, maxIndex + 1);

            break;
            // Second flip
            //flip(arr, i + 1);
        }
        return arr;
    }

    public static void main(String[] args) {
        //int[] arr = new int[]{9, 0, 11, 25, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] arr = new int[]{1, 5, 3, 4};

//    System.out.println(Arrays.toString(arr));
        int[] result = flip(arr, 2);
        //int[] result = pancakeSort(arr);

        System.out.println(Arrays.toString(result));
    }

}
