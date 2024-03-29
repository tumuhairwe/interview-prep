package com.tumuhairwe.prep;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 17};
        int target = 10;

        //  will return -1 when not found
        System.out.println("Iterative = " + binarySearch(array, target));
        System.out.println("Iterative + Neighbor = " + binarySearchOfClosest(array, target));

        //  will return closest value when not found
        int[] arr = new int[] {34, 55, 77, 89, 96, 101};
        int from = 0;
        int to = arr.length - 1;  //5
        int result = binarySearch(arr, from, to, 97);

        System.out.println("Recursive = " + result);
    }

    /**
     * Binary search that returns the INDEX (not the value) ... if not found, return -1
     *
     * iterative binary search using 2 pointers
     * a) if array is empty ... return -1
     * b) set left = 0, and right = length -1
     * c) set midpoint and move iteratively towards it by setting
     *    right = midpoint -1;
     *    left = midpoint + 1
     * d) repeat until found
     *
     * e) will return -1 when not found
     *
     * @param nums
     * @param target
     * @return
     */
    static int binarySearch(int[] nums, int target){
        if(nums.length == 0){
            return  -1;
        }

        int left = 0;
        int right = nums.length -1;
        while (left <= right){
            int midpoint = left + (right - left) /2;

            if(nums[midpoint] == target){
                return midpoint;
            }
            else if(nums[midpoint] > target){
                right = midpoint -1;
            }
            else {
                left = midpoint + 1;
            }
        }

        return  -1;
    }

    /**
     * Binary search that returns the INDEX (not the value) ... if not found, return -1
     *
     * iterative binary search using 2 pointers
     * a) if array is empty ... return -1
     * b) set left = 0, and right = length -1
     * c) set midpoint and move iteratively towards it by setting
     *    right = midpoint -1;
     *    left = midpoint + 1
     * d) repeat until found
     *
     * e) will nearest closest neighbor if target is not found
     *
     * @param nums
     * @param target
     * @return
     */
    static int binarySearchOfClosest(int[] nums, int target){
        if(nums.length == 0){
            return  -1;
        }

        int rightDiff = 0, rightClosestNeighbor = 0;
        int leftDiff = 0, leftClosestNeighbor = 0;

        int leftIndex = 0;
        int rightIndex = nums.length -1;
        while (leftIndex <= rightIndex){
            int midpoint = leftIndex + (rightIndex - leftIndex) /2;

            if(nums[midpoint] == target){
                return midpoint;
            }
            else if(nums[midpoint] > target){
                rightIndex = midpoint -1;

                leftDiff = nums[midpoint] - target;
                leftClosestNeighbor = nums[midpoint];
            }
            else {
                leftIndex = midpoint + 1;
                rightDiff = target - nums[midpoint];
                rightClosestNeighbor = nums[midpoint];
            }
        }

        // target was not found: return closest of the 2
        return leftDiff < rightDiff ? leftClosestNeighbor : rightClosestNeighbor;
    }

    /**
     * Binary search that returns the VALUE (not the index) ... if not found ... return closest value
     *
     * @param arr
     * @param startIndex
     * @param endIndex
     * @param needle
     * @return
     */
    static int binarySearch(int[] arr, int startIndex, int endIndex, int needle){
        if(arr.length == 0){
            return -1;
        }
        else if(endIndex > arr.length - 1){
            throw new IllegalArgumentException("Invalid arguments");
        }


        int middle = startIndex + (endIndex - startIndex) /2;
        if(arr[startIndex] == needle ){
            return arr[startIndex];
        }
        else if(arr[endIndex] == needle ){
            return arr[endIndex];
        }
        else if(arr[middle] == needle){
            return arr[middle];
        }

        if(needle >= arr[startIndex] && needle <= arr[endIndex]){  // needle is not between  startIndex and endIndex --> recurse
            //int middle = startIndex + (endIndex - startIndex) /2;
            if(needle <= arr[middle]){
                return binarySearch(arr, startIndex, middle, needle);
            }
            else{
                return binarySearch(arr, middle+1, endIndex, needle); // arr, 0, 3, 97
            }
        }
        // needle is not in array ... find closest (either closest in extreme left or extreme right
        else if(arr[startIndex] != needle && arr[endIndex] != needle){   // should be moved into its own method
            int rightDiff, leftDiff = 0;

            if(needle < arr[endIndex]){
                rightDiff = arr[endIndex -1];
                leftDiff = arr[endIndex];
            }
            else {
                rightDiff = arr[startIndex -1];
                leftDiff = arr[startIndex];
            }

            if (rightDiff < leftDiff) {
                return arr[startIndex];
            } else {
                return arr[endIndex];   // either right is greater of they're equidistant
            }
        }

        return -1;
    }
}
