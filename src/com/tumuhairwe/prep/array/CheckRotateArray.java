package com.tumuhairwe.prep.array;

import java.util.Arrays;

// Rotate array i.e. the right-most elements will appear
// at the left-most position in the array

// Split array by middle
// swap lastIndex -i -> with -> arr[i]


// Time Complexity: O(n)
public class CheckRotateArray {
    public static int[] rotate(int [] arr){
        int mid = arr.length/2;
        int lastIndex =  arr.length - 1;
        for (int i=0; i< lastIndex; i++){
            if(i < mid){
                int temp = arr[lastIndex-i];
                arr[lastIndex-i] = arr[i];
                arr[i] = temp;
            }

        }
        return arr;
    }
    public static void main(String[] args) {
        int [] arr = {1, 2, 3, 4, 5};
        int [] result = rotate(arr);
        System.out.println("Result");
        System.out.println(Arrays.toString(result));
    }
}
