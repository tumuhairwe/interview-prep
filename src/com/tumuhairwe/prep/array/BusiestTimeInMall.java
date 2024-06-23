package com.tumuhairwe.prep.array;


/**
 * Given a 2D array of data
 * 0 = Unix timestamp e.g. 1480640292
 * 1 = 0 or 1 (ENTRY or EXIT)
 * 2 = number-of-people
 *
 * Find the time at which the mall reached its busiest
 * ref: https://www.pramp.com/question/2WBx3Axln1t7JQ2jQq96
 * ref: https://www.youtube.com/watch?v=GiQEKZOn9K4
 * ref: https://www.youtube.com/watch?v=nSsiyuJI-YY
 * ref: https://github.com/maiquynhtruong/algorithms-and-problems/blob/master/busiest-time-in-the-mall.java
 */
public class BusiestTimeInMall {

    public static void main(String[] args) {
        int [][]data = new int[][]{
                {1487799425, 14, 1},
                {1487799425, 4,  0},
                {1487799425, 2,  0},

        {1487800378, 10, 1},
        {1487801478, 18, 0},
        {1487801478, 18, 1},

        {1487901013, 1,  0},

        {1487901211, 7,  1},
        {1487901211, 7,  0}};

        int busiest = findBusiestPeriod(data);
        System.out.println("The busiest time was " + busiest);
    }
    //TC: O(n)
    //SC: O(1)
    // 2D array is sorted by timestamp ( date[0] )
    static int findBusiestPeriod(int[][] data){
        // may need to reverse sort data (since question says ordered by asc)
        int ENTRY = 1;
        int EXIT = 0;

        int currentTimestamp = data[0][0];
        int peakTimestamp = 0;

        int currentOccupancy = data[0][1];
        int maxOccupancy = currentOccupancy;

        for (int i = 1; i < data.length; i++) {
            if (data[i][0] == currentTimestamp) {
                // update current occupancy
                currentOccupancy += data[i][2] == ENTRY ? data[i][1] : -data[i][1];
            } else {
                if (currentOccupancy > maxOccupancy) {
                    maxOccupancy = currentOccupancy;
                    peakTimestamp = currentTimestamp;
                }

                // update current occupancy
                currentOccupancy += data[i][2] == ENTRY ? data[i][1] : -data[i][1];
                // update current time
                currentTimestamp = data[i][0];
            }
        }

            if(currentOccupancy > maxOccupancy){
                maxOccupancy = currentOccupancy;
                peakTimestamp = currentTimestamp;
            }

        System.out.println("The peakCount was " + maxOccupancy);
        return peakTimestamp;
    }
}
