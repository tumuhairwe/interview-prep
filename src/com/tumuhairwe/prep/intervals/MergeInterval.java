package com.tumuhairwe.prep.intervals;

import java.util.*;

/**
 * LeetCode 56. Merge Intervals
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

        // base case
        if(intervals.length == 0){
            return new int[][]{};
        }

        // add first (sorted) interval to result list (assumed they're sorted && 1st is lowest')
        results.add(new int[]{ intervals[0][0], intervals[0][1] });
        for(int i=1; i<intervals.length; i++){
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];
            int prevEnd = results.get(i)[1];

            if(currStart <= prevEnd){
                // add currStart results
                int indexOfLastOne = results.size() - 1;
                results.get(indexOfLastOne)[1] = Math.max(currEnd, prevEnd);  // merge the 2 i.e. prolong the interval
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

    public static int[][] mergeIntervals(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        // 0. edge case (when intervals are empty)
        if(intervals.length == 0){
            return result.toArray(new int[][]{});
        }

        // 1. sort
//        Comparator<int[]> comp = (array1, array2) -> {
//            return array1[0] - array2[0] != 0 ? array1[0] - array2[0] : array1[1] - array2[1];
//        };

        // these are the same
        Comparator<int[]> c1 = (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]);
        Comparator<int[]> c2 = Comparator.comparingInt(arr -> arr[0]);
        Arrays.sort(intervals, c1);

        // same
        // 2. add first pair to the result list
        //result.add(new int[]{intervals[0][0], intervals[0][1]});
        result.add(intervals[0]);

        // process 1 by 1
        for(int[] arr : intervals){
            int[] lastTrackedRange = null;
            int currentStart = arr[0];
            int currentEnd = arr[1];

            // a) compare the new range to the last one we tracked
            // if new range has a gap before the next range starts ... and to result
            if(lastTrackedRange == null || arr[0] > lastTrackedRange[1]){
                lastTrackedRange = arr;
                result.add(lastTrackedRange);
            }
            else if(arr[1] > lastTrackedRange[1]){
                // b) otherwise ... if its end time is longer than the last
                // extend the last range's end time to encompass the new interval
                lastTrackedRange[1] = arr[1];
            }
        }

        return (int[][]) result.toArray();
    }

    static void printArray(List<int[]> temp){
        temp.forEach(ints -> {
            System.out.println(Arrays.toString(ints));
        });
    }
}
