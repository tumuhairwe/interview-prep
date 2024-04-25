package com.tumuhairwe.prep.array;

//  Time complexity = O(n)
public class CheckMinimum {
    public static void main(String[] args) {
        int[] arr = {9, 2, 3, 6};
        int min = findMinimum(arr);
        System.out.println("The minimum is " + min);
    }

    // Using the 1st element as the minimum
    // iterate over all of them ... if less than $minimum, the replacement as the new minimum
    // by the end, the number stored in the minimum will be the smallest
    public static int findMinimum(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }
}
