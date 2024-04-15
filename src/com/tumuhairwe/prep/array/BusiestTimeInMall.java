package com.tumuhairwe.prep.array;


/**
 * Given a 2D array of data
 * 0 = Unix timestamp e.g. 1480640292
 * 1 = 0 or 1 (ENTRY or EXIT)
 * 2 = number-of-people
 *
 * Find the time at which the mall reached its busiest
 * ref: https://www.pramp.com/question/2WBx3Axln1t7JQ2jQq96
 */
public class BusiestTimeInMall {
    private static final int ENTRY = 1;
    private static final int EXIT = 0;

    public static void main(String[] args) {
        int [][]data = new int[][]{ {1487799425, 14, 1},
                {1487799425, 4,  0},
        {1487799425, 2,  0},
        {1487800378, 10, 1},
        {1487801478, 18, 0},
        {1487801478, 18, 1},
        {1487901013, 1,  0},
        {1487901211, 7,  1},
        {1487901211, 7,  0}};

//        List<Entry> list = Arrays.asList(data).stream()
//                .map(entry -> new Entry(entry[0], entry[2], entry[1]))
//                .collect(Collectors.toList());

        int busiest = findBusiestPeriod(data);
        System.out.println("The busiest time was " + busiest);
    }
    static int findBusiestPeriod(int[][] data){
        // may need to reverse sort data (since question says ordered by asc)
        int currentOccupancy = 0;
        int peakTimestamp = 0;
        int peakCount = 0;

        for (int i = 0; i < data.length; i++) {
            currentOccupancy = data[i][2] == ENTRY ? currentOccupancy + data[i][1] : currentOccupancy - data[i][1];
            if(currentOccupancy > peakCount){
                peakCount = currentOccupancy;
                peakTimestamp = data[i][0];
            }   // remember to return earliest one
        }

        // keep track in pq (of max_occupanc

        System.out.println("The peakCount was " + peakCount);
        return peakTimestamp;
    }
    static class Entry{
        int timestamp;
        int action;
        int numberOfPeople;
        public Entry(int timestamp, int action, int numberOfPeople){
            this.timestamp = timestamp;
            this.numberOfPeople = numberOfPeople;
            this.action = action;
        }
    }
}
