package com.tumuhairwe.prep.array;

/**
 * LeetCode 605 (easy)
 *
 *  You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
 *
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty,
 * and an integer n, return true if n new flowers can be planted in the flowerbed without violating the
 * no-adjacent-flowers rule and false otherwise.
 *
 * ref: https://www.youtube.com/watch?v=ZGxqqjljpUI
 * ref: https://leetcode.com/problems/can-place-flowers/description/
 */
public class CanPlaceFlowers {
    public static void main(String[] args) {
        int[] flowers = new int[]{1,0,0,0,1};
        System.out.println("Should be true: " + canPlaceFlower(flowers, 1));

        flowers = new int[]{1,0,0,0,1};
        System.out.println("Should be false: " + canPlaceFlower(flowers, 2));
    }

    /**
     * Solution summary
     * - if n == 0, no need to check flowerbed (return true)
     * - iterate thru the array and check if its an empty spot AND
     *      - neighbor to the left is empty || i == 0
     *      - neighbor to the right is empty || i == last_index
     * - decrement numSpaces (n)
     * - if numSpaces == 0 -> return true
     * - fill the spot (set value to 1)
     */
    static boolean canPlaceFlower(int[] flowerbed, int n){
        //0. base case
        if(n == 0){
            return true;
        }

        //1.
        for (int i = 0; i < flowerbed.length; i++) {
            boolean isEmptySpot = flowerbed[i] == 0;

            if(isEmptySpot &&
                    (i == 0 || flowerbed[i-1] == 0) &&  // neighbor to the left is empty
                    (i == flowerbed.length - 1 || flowerbed[i+1] == 0))    // neighbor to right is empty
            {
                n--;    // decrement
                if(n == 0){
                    return true;
                }

                flowerbed[i] = 1;   // fill the spot
            }
        }

        return false;
    }
}
