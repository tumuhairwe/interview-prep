package com.tumuhairwe.prep.intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 1899. (medium)
 * Merge Triplets to Form Target Triplet
 *
 *  A triplet is an array of three integers. You are given a 2D integer array triplets, where triplets[i] = [ai, bi, ci] describes the ith triplet. You are also given an integer array target = [x, y, z] that describes the triplet you want to obtain.
 *
 * To obtain target, you may apply the following operation on triplets any number of times (possibly zero):
 *
 * Choose two indices (0-indexed) i and j (i != j) and update triplets[j] to become [max(ai, aj), max(bi, bj), max(ci, cj)].
 * For example, if triplets[i] = [2, 5, 3] and triplets[j] = [1, 7, 5], triplets[j] will be updated to [max(2, 1), max(5, 7), max(3, 5)] = [2, 7, 5].
 * Return true if it is possible to obtain the target triplet [x, y, z] as an element of triplets, or false otherwise.
 *
 * ref: https://leetcode.com/problems/merge-triplets-to-form-target-triplet/description/?envType=problem-list-v2&envId=plakya4j
 */
public class MergeTripletsFromTarget {

    /**
     * Solution summary (gaol isn't to merge ... but to check if target could possibly merged)
     * - init 3 vars to determine if each of the 3 can be found
     * - iterate the array
     *      - if target[0] marches triplet[i][0] -> x == true
     *      - if target[0] marches triplet[i][1] -> y == true
     *      - if target[0] marches triplet[i][2] -> z = true
     * - return if ALL 3 are found
     * NB: This implementation also works if its possible to generate a merged triplet from more than 2 adjacent triplets
     */
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        // base case
        if(triplets.length == 0 && target.length > 0){
            return false;
        }

        boolean xIsFound = false;
        boolean yIsFound = false;
        boolean zIsFound = false;

        for (int i = 0; i < triplets.length; i++) {
            // does [0] == target[0]
            if(triplets[i][0] == target[0] && triplets[i][1] <= target[1] && triplets[i][2] <= target[2]){
                xIsFound = true;
            }

            // does [1] == target[1]
            if(triplets[i][0] <= target[0] && triplets[i][1] == target[1] && triplets[i][2] <= target[2]){
                yIsFound = true;
            }

            // does [2] == target[2]
            if(triplets[i][0] <= target[0] && triplets[i][1] <= target[1] && target[2] == triplets[i][2]){
                zIsFound = true;
            }
        }

        return xIsFound && yIsFound && zIsFound;
    }
    /**
     * Solution summary
     * - Sort triplets array
     * - create triplets class with merge() and equals() implementation (transform target[] -> triplets class)
     * - seed previous with 1st entry in triplets ....
     * - iterate triplets[] compare target against previous.merged(current) --- return true if they match
     * - if you exit, iteration .. return false (i.e. there was no merged combination that marches target
     */
    public boolean mergeTriplets_impl2(int[][] triplets, int[] target) {
        // base case
        if(triplets.length == 0 && target.length > 0){
            return false;
        }

        //0. sort triplets
        Comparator<int[]> comp = (int[] a, int[] b) -> {
            if(a[0] != b[0]){
                return Integer.compare(a[0], b[0]);
            }
            else if(a[1] != b[1]){
                return Integer.compare(a[1], b[1]);
            }
            else {
                return Integer.compare(a[2], b[2]);
            }
        };
        Arrays.sort(triplets, comp);

        //1. sort target
        Arrays.sort(target);

        Triplet t = new Triplet(target[0], target[1], target[2]);
        Triplet prev = new Triplet(triplets[0][0], triplets[0][1], triplets[0][2]);

        for (int i = 1; i < triplets.length; i++) {
            Triplet current = new Triplet(triplets[i][0], triplets[i][1], triplets[i][2]);
            Triplet merged = prev.merge(current);

            if(merged.equals(t)){
                return true;
            }

            prev = current;
        }

        return false;
    }

    class Triplet{
        private int start;
        private int mid;
        private int end;
        public Triplet(int x, int y, int z){
            start = x;
            mid = y;
            end = x;
        }
        public Triplet merge(Triplet t){
            int x = Math.max(start, t.start);
            int y = Math.max(start, t.mid);
            int z = Math.max(start, t.end);
            return new Triplet(x, y, z);
        }
        public boolean equals(Triplet t){
            return start == t.start && mid == t.mid && end == t.end;
        }
    }
}
