package com.tumuhairwe.prep.array;

/**
 * Given an array k, remove an entry E without using extra space (i.e. in place)
 *
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
 *
 * ref: https://www.youtube.com/watch?v=Pcd1ii9P9ZI
 * ref: https://leetcode.com/problems/remove-element/description/
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] arr = new int[] { 3, 2, 3, 6, 3, 10, 9, 3 };
        System.out.println(remove(arr, 3));

        arr = new int[] { 2, 11, 2, 2, 1 };
        System.out.println(remove(arr, 2));
    }

    /**
     * Solution summary
     * - declare nxtElement index = 0;
     * - iterate the array ... for each element != val, replace it with nextElement then increment it
     * - At the end, nextElement will be the number of all non-val values in that array
     */
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
