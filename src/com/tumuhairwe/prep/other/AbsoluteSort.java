package com.tumuhairwe.prep.window;

import java.util.Arrays;

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
