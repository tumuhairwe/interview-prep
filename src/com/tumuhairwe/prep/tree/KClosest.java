package com.tumuhairwe.prep.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Find k Closest elements to target
 *
 * Summary
 * - Compute ABSOLUTE distance between each element (in nums array) and target
 * - Store the distances + corresponding element in pairs (i./e. Map<Integer, Integer> == elemenb & diff
 * - Sort the Map by value (i.e. diff)
 * - return top K elements
 */
public class KClosest {

    public static void main(String[] args) {
        int[] nums = new int[]{25, 41, 81, 85, 103, 117, 319};
        int k = 3;
        int target = 99;

        System.out.println("Given target = " + target + " and array=" + Arrays.toString(nums));
        List<Integer> result = findClosestElements_naive(nums, k, target);
        System.out.println("Naive 1. The closest " + k + " to target are " + result);

        result = findClosestElements_optimized(nums, k, target);
        System.out.println("Optimized 1. The closest " + k + " to target are " + result);

        nums = new int[]{5, 10, 17, 19, 35, 36, 43};
        k= 4;
        target = 66;
        System.out.println("");
        System.out.println("Given target = " + target + " and array=" + Arrays.toString(nums));
        // optimized
        result = findClosestElements_naive(nums, k, target);
        System.out.println("Naive 2. The closest " + k + " to target are " + result);

        // optimized
        result = findClosestElements_optimized(nums, k, target);
        System.out.println("Optimized 2. The closest " + k + " to target are " + result);

        // findding single element
        nums = new int[]{1, 2, 2, 3, 3, 4, 4};
        int res = findSingleElementInSortedArray(nums);
        System.out.println("Single non duplicate result in "+Arrays.toString(nums)+" is" + res);

        nums = new int[]{1, 1, 2, 2, 3, 4, 4, 5, 5};
        res = findSingleElementInSortedArray(nums);
        System.out.println("Single non duplicate result in "+Arrays.toString(nums)+" is" + res);

        nums = new int[]{1, 1, 2, 3, 3};
        res = findSingleElementInSortedArray(nums);
        System.out.println("Single non duplicate result in "+Arrays.toString(nums)+" is" + res);
        }

    // We traverse the whole array to compute the distance between each and K -> O (n) tim
    // we have to sort all entries -> O(n log_n) time complexity
    // storing k results == O(n) space complexity

    // overall TC = O(n log_n) == bcoz we need to travese the whole array && soft entry
    // overall SC = O(n) == need to store N entries & K entries after extracting i.e. O(n + k) -> O(n)
    public static List<Integer> findClosestElements_naive(int[] nums, int k, int target) {
        Map<Integer, Integer> dist = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int diff = Math.abs(nums[i] - target);
            dist.put(nums[i], diff);    // need to store the element too because can't reverse engineer it since diff is
            // absolute e.g. real diff coule be -3, -4 4. -> 3, 4, 4
            // we need to figure out which was the real value of each diff
        }

        // Key = (actual num from nums) --  Value == diff from target
        // sort Map by Entry.value (i.e. diff)
        // get Key (i.e. the actual num to return)
        // limit to top K
        List<Integer> result = dist.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .limit(3)
                .collect(Collectors.toList());
        return result;
    }

    // TC = binary-search O(log_n) + adjusting the window-size O(k) where k = window size
    // TC = O(logN + K)
    // SC = O(1) taken by the variables
    public static List<Integer> findClosestElements_optimized(int[] nums, int k, int target){
        // a) if length of nums == k => return all the elements
        if(nums.length == k){
            //return Stream.of(nums).collect(Collectors.toList());
            return Arrays.stream(nums)
                    .boxed()
                    .collect(Collectors.toList());
        }

        // b) it target is <= 1st element, the 1st K elements are the closest
        if(target <= nums[0]){
            return Arrays.stream(nums)
                    .boxed()    // convert to Integer
                    .limit(k)   // limit to K.limit(k)
                    .collect(Collectors.toList());
        }

        // c) if target is >= last element, then the last K elements are the closest
        if(target >= nums[nums.length-1]){
            int startIndex = nums.length - 1 - k;
            int endIndex = nums.length - 1;
            int[] lastKElements = Arrays.copyOfRange(nums, startIndex, endIndex);
            return Arrays.stream(lastKElements)
                    .boxed()
                    .limit(k)
                    .collect(Collectors.toList());
        }

        // d) otherwise, search for K elements in the whole array
        // d i) Use binary search to find the index of the 1st closest integer to target in nums
        // d ii) Use 2 pointers windowLeft and windowRight to maintain a sliding window and move the pointers
        //      conditionally (either to the left or the right) to expand the window until its sie gets equal to K
        //      The K elements in that window are the closest integers to target
        int firstClosest = binarySearch(nums, target);
        int windowLeft = firstClosest -1;
        int windowRight = windowLeft + 1;

        while ((windowRight - windowLeft - 1) < k){
            if(windowLeft == -1){
                windowRight++;  // expand window rightwards
                continue;
            }
            int leftDiff = Math.abs(nums[windowLeft] - target);
            int rightDiff = Math.abs(nums[windowRight] - target);

            if (windowRight == nums.length || leftDiff <= rightDiff){
                windowLeft--;   // expand window leftwards
            }
            else {
                windowRight++;
            }
        }

        List<Integer> closestElements = new ArrayList<>();
        // method A
        int[] closestElements_arr = Arrays.copyOfRange(nums, windowLeft+1, windowRight);
        closestElements = Arrays.stream(closestElements_arr)
                .boxed()
                .collect(Collectors.toList());

        // method B
        // collect elements in window into a list and return
//        for (int i = windowLeft; i < windowRight; i++) {
//            closestElements.add(nums[i]);
//        }
        return closestElements;
    }

    private static int binarySearch(int[] nums, int target){
        // a) initialize left & right pointer
        int left = 0;
        int right = nums.length -1;

        // find the 1st closest elements to target
        while (left < right){
            // compute mid
            //int mid = (left + right)/2;
            int mid = left + (right - left) /2;

            // if val = mid, return it
            if(nums[mid] == target){
                return mid;
            }

            // if val < target, move left_pointer forward
            if(nums[mid] < target){
                left = mid + 1;
            }

            // if val is > mid, move right pointer backwards
            else{
                right = mid - 1;
            }
        }

        // if target is not found, return index where it should be inserted
        return left;
    }

    // In an array where all elements appear twice, find the 1 element that appears once
    // TC = O (log_n)
    // SC = O(1)
    // SC = O(1)
    private static int findSingleElementInSortedArray(int[] nums){
        // initialize the pointers
        int left = 0;
        int right = nums.length - 1;

        while (left != right){  // will exit right away in array of 2
            // compute mid
            //int mid = left + (left + right) /2;
            int mid = left + (right - left) /2;

            // if value of mid is odd, decrement
            if(nums[mid] % 2 == 1){
                mid--;    // make even
            }

            if(nums[mid] != nums[mid + 1]){
                left = mid + 2;
            }
            else {
                right = mid;
            }
        }

        return nums[left];
    }
}
