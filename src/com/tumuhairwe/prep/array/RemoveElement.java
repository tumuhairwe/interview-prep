package com.tumuhairwe.prep.array;

/**
 * Given an array k, remove an entry E without using extra space (i.e. in place)
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] arr = new int[] { 3, 2, 3, 6, 3, 10, 9, 3 };
        System.out.println(remove(arr, 3));

        arr = new int[] { 2, 11, 2, 2, 1 };
        System.out.println(remove(arr, 2));
    }
    public static int remove(int[] arr, int key){
        int nextElement =0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != key){
                arr[nextElement++] = arr[i];
            }
        }

        return nextElement;
    }
}
