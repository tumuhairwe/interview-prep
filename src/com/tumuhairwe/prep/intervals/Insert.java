package com.tumuhairwe.prep.intervals;

import java.util.ArrayList;
import java.util.List;

public class Insert {

    public static void main(String[] args) {

    }
    public static int[][] insertInterval(int[][] existingIntervals, int[] newInterval) {

        // Replace this placeholder return statement with your code
        List<int[]> result = new ArrayList<>();

        for(int i=0; i<existingIntervals[0].length; i++){
            int start = existingIntervals[i][0];
            int end = existingIntervals[i][1];

            // handle start // assumeds start < end
            if(end < newInterval[0]){   // interval starts and ends BEFORE newInterval
                result.add(new int[]{start, end});
                continue;
            }

            if (newInterval[0] >= start){   // newInterval starts AFTER start
                start = Math.min(start, newInterval[0]);
            }

            // handle end of range
            if(end >= newInterval[1]){    // newInterval's end may exceed end
                end = Math.max(end, newInterval[1]);  //  max of the 2 should be upperBound
            }
            result.add(new int[]{start, end});
        }
        return result.toArray(new int[][]{});
    }
}
