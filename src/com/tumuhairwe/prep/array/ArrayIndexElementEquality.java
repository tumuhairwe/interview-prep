package com.tumuhairwe.prep.array;

/**
 * Given a sorted array of distinct integers, write a function indexEqualsValueSearch() that returns
 * the the lowest index i for which arr[i] == i. Return -1 if there is no such index.
 * Return -1 if there's no such index
 *
 * ref: https://www.pramp.com/challenge/jKoA5GAVy9Sr9jGBjz04
 *
 * Analyze the time and space complexities of your solution and explain its correctness.
 *
 * Examples:
 *
 * input: arr = [-8,0,2,5]
 * output: 2 # since arr[2] == 2
 *
 * input: arr = [-1,0,3,6]
 * output: -1 # since no index in arr satisfies arr[i] == i.
 * Constraints:
 *
 * [time limit] 5000ms
 *
 * [input] array.integer arr
 *
 * 1 ≤ arr.length ≤ 100
 * [output] integer
 *
 *
 *
 arr = [-8, 0, 2, 5]

 first occurrence arr[i] == i

 Outline
 - Iterate thru the array
 - check if arr[i] == i
 - keep track of the lowest (if above condition is tru)
 - return the first occuurence
 - implemented as binary-search == log_n (TC)

 [2 3 4 5 9]
 0 1 2 3 4

 [-1 1]
 0 1

 [0 1 2]
 0 1 2
 arr[0] == 0
 arr[1] == 1
 ...

 [3]
 0
 */
public class ArrayIndexElementEquality {
    public static void main(String[] args) {
        int[] arr = new int[]{-8,0,2,5};
        int result = indexEqualsValueSearch(arr);
        System.out.println("Should be 2 " + result);

        arr = new int[]{-5,0,3,4,10,18,27};
        result = indexEqualsValueSearch(arr);
        System.out.println("Should be -1 " + result);
    }
    static int indexEqualsValueSearch(int[] arr) {
        if(arr.length == 0){
            return -1;
        }
        else if(arr.length == 1){
            if(arr[0] == 0){
                return arr[0];
            }
            else return -1;
        }

        return search(arr, 0, arr.length - 1);
//        return binarySearch(arr);
    }

    // target == mid,  arr[mid] == mid
    static int search(int[] arr, int fromIndex, int toIndex){

        if(fromIndex == toIndex){   // we've reached the end
            return arr[fromIndex] == fromIndex ? fromIndex : -1;
        }
        if(arr[fromIndex] == fromIndex){
            return fromIndex;
        }
        else if(arr[toIndex] == toIndex){
            return toIndex;
        }
        //else return -1;

        int mid = fromIndex + (toIndex - fromIndex) / 2;
        if(arr[mid] == mid){
            return mid;
        }
        else if(arr[mid] > mid){
            return search(arr, mid+1, toIndex);
        }
        else return search(arr, fromIndex, mid + 1);

        // i=1 => (search(arr, 0, 5),
        // i=2 => (search(arr, 0, 3),
        // i=2 => (search(arr, 0, 2)
        // i=2 => (search(arr, 0, 1),

    }

    static int binarySearchIterative(int[] nums){
        if(nums.length == 0){
            return  -1;
        }

        int left = 0;
        int right = nums.length -1;
        while (left <= right){
            int midpoint = left + (right - left) /2;

            if(nums[midpoint] == midpoint){
                return midpoint;
            }
            else if(nums[midpoint] > midpoint){
                right = midpoint -1;
            }
            else {
                left = midpoint + 1;
            }
        }

        return  -1;
    }
}
