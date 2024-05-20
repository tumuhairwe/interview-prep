package com.tumuhairwe.prep.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 4 (hard)
 * Find median of 2 sorted arrays
 * The overall run time complexity should be O(log (m+n)).
 *
 * Solution Summary:
 * - Create 2 PQs (minHeap & maxHeap) -- minHeap==order-ascending (i.e. smallest on top and order-descending
 * - Initialize both arrays by calling insert() for each element in arr1
 * - Initialize both arrays by calling insert() for each element in arr2
 * - return getMedian() to get the median
 * ref: https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 */
public class MedianOfTwoSortedArrays {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public static void main(String[] args) {
        MedianOfTwoSortedArrays finder = new MedianOfTwoSortedArrays();

        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        double median = finder.findMedianSortedArrays(nums1, nums2);
        System.out.println("Array 1: " + Arrays.toString(nums1));
        System.out.println("Array 2: " + Arrays.toString(nums2));
        System.out.println("Median " + median);

        nums1 = new int[]{4,5,6,8,9};
        nums2 = new int[0];
        median = finder.findMedianSortedArrays(nums1, nums2);
        System.out.println("Array 1: " + Arrays.toString(nums1));
        System.out.println("Array 2: " + Arrays.toString(nums2));
        System.out.println("Median " + median);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        Comparator<Integer> comp = Comparator.comparingInt(a -> a);
        this.maxHeap = new PriorityQueue<>(comp);
        this.minHeap = new PriorityQueue<>((a, b) -> b - a);    // same as comp.reversed()

        for(int num : nums1){
            insert(num);
        }
        for(int num : nums2){
            insert(num);
        }

        return getMedian();
    }

    public void insert(int val){
        this.minHeap.add(val);

        if(!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() > maxHeap.peek()){
            int num = minHeap.poll();
            maxHeap.add(num);
        }

        int num = 0;
        if(minHeap.size() > maxHeap.size() + 1){
            num = minHeap.poll();
            maxHeap.add(num);
        }

        if(maxHeap.size() > minHeap.size() + 1){
            num = maxHeap.poll();
            minHeap.add(num);
        }
    }
    public double getMedian(){
        if(minHeap.size() == maxHeap.size()){
            int value_from_minHeap = minHeap.peek();
            int value_from_maxHeap = maxHeap.peek();
            System.out.println("MinHeap : " + value_from_minHeap);
            System.out.println("MaxHeap : " + value_from_maxHeap);
            return (double)(minHeap.peek() + maxHeap.peek()) / 2;
        }
        else if(minHeap.size() > maxHeap.size()){
            return minHeap.peek();
        }
        else{
            return maxHeap.peek();
        }
    }
}
