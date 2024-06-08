package com.tumuhairwe.prep.array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 1046 (easy)
 * Given an array of integers where stones[i] is the weight of the i-th stone
 * We are playing a game with the stones ... on each turn, we choose the heaviest 2 stones and smash them together.
 *
 * Suppose the heaviest 2 stones ave the weights x and y with x <= y. THe result of the smash is
 * - if x == y ? both stones are destroyed
 * - if x != y ? the stone of weight x is destroyed and the stone of weight y has a new weight y - x
 * ref: https://leetcode.com/problems/last-stone-weight/description/
 */
public class LastStoneWeight {
    public static void main(String[] args) {
        int[] stones = new int[]{2,7,4,1,8,1};
        int lastRemainingWeight = lastStoneWeight(stones);
        System.out.println(lastRemainingWeight);
    }

    // putting things into a heap -> O(n) -- n == the number of elements/array
    // getting things from the heap -> O(log_n) operation
    // getting max/min of N #of things from PQ -> O(n log_n) maxHeap.poll()
    public static int lastStoneWeight(int[] stones) {
        Comparator<Integer> comp = Comparator.comparingInt(a -> a);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(comp.reversed());

        // 0. add stones to pq
        for (int num : stones){
            // if using default comparator .. multiple num by -1 to get Heaviest at top of pq & get absolute value to return
            //maxHeap.add(-num);  // == num * -1
            maxHeap.add(num);  // == num * -1
        }

        // 1. iterate over 2 stones at a time
        while (maxHeap.size() > 1){
            int stoneX = maxHeap.remove();;
            int stoneY = maxHeap.remove();;

            if(stoneX != stoneY){
                maxHeap.add(stoneX - stoneY);
            }
        }

//        if(maxHeap.size() == 0){
//            return 0;
//        }
//        else {
//            return maxHeap.remove();    // return Math.abs(maxHeap.remove() ) when using default comparator
//        }
        return maxHeap.size() == 0 ? 0 : maxHeap.remove();
    }
}
