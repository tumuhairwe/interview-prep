package com.tumuhairwe.prep;

/**
 * Given a non-negative integer x,
 * return the square root of X rounded down to the nearest integer
 * The returned integer should be non-negative as well.
 *
 * You must not use any built-in exponent function
 *
 * LeetCopde 69 (easy)
 */
public class SquareRoot {
    public static void main(String[] args) {
        System.out.println("1: Result is " + binarySearch(1));
        System.out.println("4: Result is " + binarySearch(4));
        System.out.println("6: Result is " + binarySearch(6));
        System.out.println("8: Result is " + binarySearch(8));
    }
    public static int binarySearch(int square){
        int left = 0;
        int right = square;
        int returnValue = 0;

        // 0. handle base case
        if(square == 1){
            return square;
        }

        // 1. iterative binary search where needle == mid-squared
        while(left < right){

            int mid = left + ((right - left)/2);
            int leftSquared = mid * mid;
            if(leftSquared == square){
                return mid;
            }
            else if(leftSquared > square){
                right = mid - 1;
            }
            else{
                left = mid + 1;
                returnValue = mid;
            }
        }

        return returnValue;
    }
}
