package com.tumuhairwe.prep.binary;

/**
 * LeetCode 875 (medium)
 * Koko loves to eat bananas. There are n piles of bananas, the i-th pile has piles[i] bananas. The guards have gone and
 * weill come back in H hours.
 * Koko can decide her bananas-per-hour eating speed of k. Each hour she chooses some pile of bananas and eats K bananas from that pile
 * If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour
 *
 * Koko like to eat slowly but wants to finish eating all the bananas before the guards return.
 * Return the minimum integer K, such that she can e3at all the bananas within H hours
 */
public class KokoEatingBananas {

    //TC: O(n log_m): where m = max number of single of bananas in single pile
    //SC: O(1)
    public int minEatingSpeed(int[] piles, int hours) {
        //0. binary search: init left && right boundaries
        int left = 1;
        int right = 1;

        // 0.1 find fastest value of pile (max(piles))
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        //1. do binary search
        while(left < right){
            int middle = (left + right)  /2;
            int hoursSpent = 0;

            // Iterate over the piles and calculate hourSpent.
            // We increase the hourSpent by ceil(pile / middle)
            for (int pile : piles) {
                hoursSpent += Math.ceil((double) pile / middle);
            }

            // check if middle is a workable speed and cut the search-space in half
            if(hoursSpent <= hours){
                right = middle;
            }
            else{
                left = middle + 1;
            }
        }

        return right;
    }
}
