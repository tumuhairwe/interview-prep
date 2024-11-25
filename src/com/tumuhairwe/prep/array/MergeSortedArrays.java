package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 88 (easy)
 *
 *  Given 2 sorted arrays nums1 and nums2, and 2 integers
 *      - m (which is the number of elements in nums1
 *      - n (which is the length of nums2)
 *  and given that nums1.length = n + m
 *  Sort them in place i.e. combine the elements from nums2 into nums1 in place (nums1 is the larger and con hold both)
 */
public class MergeSortedArrays {

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2,3,0,0,0}; int m = 3;
        int[] arr2 = new int[]{2,5,6}; int n = 3;

        System.out.println("Before : " + Arrays.toString(arr1));
        merge_3_pointers(arr1, m, arr2, n);
        System.out.println("After : " + Arrays.toString(arr1));
    }

    /**
     * Solution Summary
     * - add elements from nums1 into nums1 (starting from nums1[m + n] = nums2[n++]
     * - sort
     *
     * TC: n log(n) - because of sorting
     * SPC: O (n_
     */
    public static void merge_by_sort(int[] nums1, int m, int[] nums2, int n){
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }

        Arrays.sort(nums1);
    }

    /**
     * Solution Summary
     * - Use 3 pointers (starting from end)
     * - find the bigger of the 2 (nums1[r1] & nums2[r2] -> assign to num1[p3]
     * - i.e. goal is to find the largest, 2 nd largest, ... and put them at the end of the output array ... while decrementing
     * - do this as long as writer > 0
     * TC: n (m + n) - m = space of nums1, n = space of nums2
     * SPC: O (1) -- done in place
     */
    public static void merge_3_pointers(int[] nums1, int m, int[] nums2, int n){
        int p1 = m - 1;
        int p2 = n - 1;
        //int writer = m + n - 1;

        for (int writer = (m + n) - 1; writer > 0 ; writer--) {
            if(p1 >= 0 && p2 >= 0){
                nums1[writer] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
            }
            else if(p1 > 0){
                nums1[writer] = nums1[p1--];
            }
            else {
                nums1[writer] = nums2[p2--];
            }
        }

        return;
    }
}
