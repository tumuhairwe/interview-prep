package com.tumuhairwe.prep;

import java.util.Arrays;

// Transform array into
//  in such a way that each index i will contain the product of all elements present in the array ...
//  EXCEPT the element stored in the index

// Time complexity: O(n)
// Space complexity: O(n)
// auxiliary space used O(1)
public class ProductArray {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        int[] result = findProduct(arr);
        System.out.println("Result");
        System.out.println(Arrays.toString(result));
    }

    public static int[] findProduct(int arr[]) {
        int [] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int product = 1;
            for (int j = 0; j < arr.length; j++) {
                if(i != j){
                    product = product * arr[j];
                }
            }
            result[i] = product;
        }
        // write your code here
        return result;
    }
}
