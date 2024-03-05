package com.tumuhairwe.prep.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// ref: https://leetcode.com/problems/merge-intervals/description/
public class Merge {

    private static final int[][] inputIntervals = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
    static List<int[]> listOfArrays = Arrays.asList(
            new int[]{1, 3},
            new int[]{8, 10},
            new int[]{2, 6},
            new int[]{5, 18}
    );
    //private static int[][] outIntervals = null;

    // sorting and then iterating ==> O(n_log(n) )
    public static void main(String[] args) {
        System.out.println("BEFORE sorting ... ");
        for(int[] array : listOfArrays){
            System.out.println(Arrays.toString(array));
        }

        //sortArray(listOfArrays);
        sortArray2(listOfArrays);

        System.out.println("AFTER sorting ... ");
        for(int[] array : listOfArrays){
            System.out.println(Arrays.toString(array));
        }

        // implementation
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
                results.get(results.size() - 1)[1] = Math.max(currEnd, prevEnd);  // merge the 2 i.e. prolong the interval
            }
            else {
                results.add(new int[]{currStart, currEnd});  // new range completely
            }
        }

        // convert result list into array[][]
        return results.toArray(new int[][]{});
    }

    private static void sortArray(List<int[]> ranges) {
        ranges.sort((array1, array2) -> {
            int comparison = Integer.compare(array1[0], array2[0]); //

            // if the 1st elements are equal, compare the 2nd and so son
            if(comparison == 0){    // should return -1 if its less, 0 if equal,  and 1 if greater thanb
                for (int i = 0; i < array1.length; i++) {
                    comparison = Integer.compare(array1[i], array2[i]);
                    if(comparison != 0){
                        break;
                    }
                }
            }
            return comparison;
        });
    }

    private static void sortArray2(List<int[]> ranges){
        Collections.sort(ranges, (a, b) -> {
            return a[0] - b[0] != 0 ? a[0] - b[0] : a[1] - b[1];
        });
    }

    public static int[][] mergeIntervals(int[][] intervals) {
        // Replace this placeholder return statement with your code
        List<int[]> result = new ArrayList<>();

        // 0. edge case (when intervals is empty
        if(intervals.length == 0){
            return result.toArray(new int[][]{});
        }

        // 1. sort
        Arrays.sort(intervals, (array1, array2) -> {
            return array1[0] - array2[0] != 0? array1[0] - array2[0] : array1[1] - array2[1];
        });

        // 1. add first pair to the result list
        result.add(new int[]{intervals[0][0], intervals[0][1]});

        // process 1 by 1
        for(int[] arr : intervals){
            int[] lastTrackedRange = null;

            int [][] arra = new int[][]{};

            int currentStart = arr[0];
            int currentEnd = arr[1];

            // a) compare the new range to the last one we tracked
            // if new range has a gap before the next range sgtarts ... and to result
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
