package com.tumuhairwe.prep.strings;

import java.util.*;

/**
 * Given an array of numbers
 * ... and a number K
 * ... find the maximum number that can fit in a window of size
 * K
 * Window is moving  let to right
 */
public class SlidingWindowMaximum {
    // time complexity = I)k * (n-k))
    LinkedList<Character> d = new LinkedList<>();
    ArrayDeque<Character> a = new ArrayDeque<>();
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        int k=3;
        int[] input = {1, 3, -1, -3, 5, 3, 6, 7};
        int windowStart = 0;

        //pq.

        long c = Arrays.asList(input)
                .stream().count();
        System.out.println(input.length);

        System.out.println(Arrays.asList(input));
        //System.out.println(List.of(input).size());
    }
}
