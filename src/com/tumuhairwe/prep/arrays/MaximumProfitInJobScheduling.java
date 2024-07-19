package com.tumuhairwe.prep.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * LeetCode 1235 (hard)
 *
 * We are given n jobs, where every job is scheduled to be done from
 * startTime[i] to endTime[i], obtaining a profit of profit[i].
 * Given the startTime and endTime and profit arrrays, return the maximum profit you can take
 * such that there are no 2 jobs in the subset overlapping time range
 *
 * ref: https://leetcode.com/problems/maximum-profit-in-job-scheduling/description/
 * ref: https://leetcode.com/problems/maximum-profit-in-job-scheduling/solutions/4516345/java-solution-for-maximum-profit-in-job-scheduling-problem/
 */
public class MaximumProfitInJobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit){
        //0. base case: if the lengths of the 3 arrays are not equals, return false
        if(startTime.length == 0|| (startTime.length != endTime.length || endTime.length != profit.length)){
            return 0;
        }

        //1. create and populate 2D jobs[]  {start, end, profit}
        int n = startTime.length;;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{ startTime[i], endTime[i], profit[i] };
        }

        //2 sort [] by endTIme
        //Comparator<int[]> orderByEndTime = Comparator.comparingInt(arr -> arr[1]);
        Arrays.sort(jobs, Comparator.comparingInt(arr -> arr[1]));

        //3. use TreeMap to store [key=startTime, val=maxProfit]
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(0, 0);  // seed TreeMap indicating no job scheduled

        //4. loop thru jobs[] ... calculate job with max profit
        for (int[] job : jobs){
            int jobStart = job[0];
            int jobEnd = job[1];
            int jobProfit = job[2];

            int prevProfit = treeMap.floorEntry(jobStart).getValue();   // get-greatest-key <= jobStart
            int maxProfit = prevProfit + jobProfit;
            if(maxProfit > treeMap.lastEntry().getValue()) { // get-greatest-key in entire ap
                treeMap.put(jobEnd, maxProfit); // will create entry with jobEnd as startTime (inclusive)
            }
        }
        // key with maxProfit will be entry with latest jobStart
        return treeMap.lastEntry().getValue();
    }
}
