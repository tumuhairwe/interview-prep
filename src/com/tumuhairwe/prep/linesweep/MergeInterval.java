package com.tumuhairwe.prep.linesweep;

import java.util.*;

/**
 * LeetCode 56. Merge Intervals (Medium)
 * Given an array of intervals where interval[i] = [start, end]
 * Merge all overlapping intervals and return an array of non-overlapping intervals that cover
 * all the intervals
 *
 * ref: https://www.youtube.com/watch?v=44H3cEC2fFM
 * ref: https://leetcode.com/problems/merge-intervals/description/
 */
public class MergeInterval {

    private static final int[][] inputIntervals = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
    static List<Integer[]> listOfArrays = Arrays.asList(
            new Integer[]{1, 3},
            new Integer[]{8, 10},
            new Integer[]{2, 6},
            new Integer[]{5, 18}
    );
    // sorting and then iterating ==> O(n_log(n) )
    public static void main(String[] args) {
        System.out.println("BEFORE sorting ... ");
        for(Integer[] array : listOfArrays){
            System.out.println(Arrays.toString(array));
        }

        //sortArray(listOfArrays);
        sortArray2(listOfArrays);

        System.out.println("AFTER sorting ... ");
        for(Integer[] array : listOfArrays){
            System.out.println(Arrays.toString(array));
        }
    }

    public int[][] merge(int[][] intervals) {
        List<int[]> results = new ArrayList<>();

        // 0. base case
        if(intervals.length == 0){
            return new int[][]{};
        }

        // 1. sort the intervals
        Comparator<int[]> c1 = Comparator.comparingInt(arr -> arr[0]);
        Arrays.sort(intervals, c1);

        // 2. add first (sorted) interval to result list (assumed they're sorted && 1st is lowest')
        results.add(intervals[0]);
        for(int i=1; i<intervals.length; i++){
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];
            int prevEnd = results.get(results.size() - 1)[1];

            if(currStart <= prevEnd){
                // add currStart results
                int indexOfLastOne = results.size() - 1;
                int latestCombinedEnd = Math.max(currEnd, prevEnd);
                results.get(indexOfLastOne)[1] = latestCombinedEnd;  // merge the 2 i.e. prolong the interval
            }
            else {
                results.add(new int[]{currStart, currEnd});  // new range completely
            }
        }

        // convert result list into array[][]
        return results.toArray(new int[][]{});
    }

    private static void sortArray(List<Integer[]> ranges) {
        Comparator<Integer[]> comp = (Integer[] array1, Integer[] array2) -> {
            int comparison = Integer.compare(array1[0], array2[0]); //

            // if the 1st elements are equal, compare the 2nd and so son
            if(comparison == 0){    // should return -1 if its less, 0 if equal,  and 1 if greater than
                for (int i = 0; i < array1.length; i++) {
                    comparison = Integer.compare(array1[i], array2[i]);
                    if(comparison != 0){
                        break;
                    }
                }
            }
            return comparison;
        };
        ranges.sort(comp);
    }

    private static void sortArray2(List<Integer[]> ranges){
        Comparator<Integer[]> comp = (Integer[] a, Integer[] b) -> {
            return a[0] - b[0] != 0 ? a[0] - b[0] : a[1] - b[1];
        };

        Collections.sort(ranges, comp);
    }

    /**
     * Solution summary
     * sort each interval,
     * - overlapping intervals should be adjacent,
     * - iterate and build solution; also graph method, less efficient, more complicated
     */
    public static int[][] mergeIntervals(int[][] intervals) {
        // 0. edge case (when intervals are empty)
        if(intervals.length == 0){
            return new int[][]{{0,0}};
        }

        List<int[]> output = new ArrayList<>();

        // 1. sort the intervals
        // these are the same
        Comparator<int[]> c1 = (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]);
        Comparator<int[]> c2 = Comparator.comparingInt(arr -> arr[0]);
        Arrays.sort(intervals, c1);

        // same
        // 2. add first pair to the output list
        //output.add(new int[]{intervals[0][0], intervals[0][1]});
        output.add(intervals[0]);

        // process 1 by 1 -> get current interval .. if overlaps ... merged wi
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int currentStart = current[0];
            int currentEnd = current[1];

            int[] mostRecent = output.get(output.size() - 1);
            if(currentStart <= mostRecent[1]){ // lastEnding
                // merge
                int latestCombinedEnd = Math.max(mostRecent[1], currentEnd);
                output.get(output.size() - 1)[1] = latestCombinedEnd;   // set most-recent-end by prolonging ti latestCombinedEnd
            }
            else {
                output.add(current);
            }
        }
        int[][] res = new int[output.size()][2];
        return output.toArray(res);
    }
}
