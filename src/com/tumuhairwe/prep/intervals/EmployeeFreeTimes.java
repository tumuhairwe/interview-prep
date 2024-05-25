package com.tumuhairwe.prep.intervals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You’re given a list containing the schedules of multiple employees.
 * Each person’s schedule is a list of non-overlapping intervals in sorted order.
 * An interval is specified with the start and end time, both being positive integers.
 *
 * Your task is to find the list of finite intervals representing the free time for all the employees.
 */
public class EmployeeFreeTimes {

    public static void main(String[] args) {
        List<List<Interval>> times = new ArrayList<>();
        System.out.println(employeeFreeTime(times));
    }

    // time complexity == O(n log_k) ... because of the need for sorting
    // where k is number of employee/size of List
    // where n is the number of intervals
    //
    // space complexity: O(k) ... because of need to store the schedules of all the employees
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> freeTime = new ArrayList<>();

        // example of mapping list of integer arrays to list of Interval
        List<int[]> aa = new ArrayList<>();
        aa.add(new int[]{1, 2});
        List<Interval> intervals = aa.stream()
                .map(entry -> new Interval(entry[0], entry[1]))
                .sorted()
                .collect(Collectors.toList());

        // 0. flat-map them into list of Comparable objectss
        List<Interval> allSchedulesSorted = schedule
                .stream()
                .flatMap(s -> s.stream())
                .sorted()
                .collect(Collectors.toList());

        // 1. loop thru list (starting from index 1)
        Interval previousInterval = allSchedulesSorted.get(0);  // seed _prev as index=0

        for (int i=1; i<allSchedulesSorted.size(); i++) {

            // 2. calculate free period
            Interval currentInterval = allSchedulesSorted.get(i);
            int freePeriod = previousInterval.end - currentInterval.start;
            if(freePeriod > 0 ){

                // 4. if there is a free period, add it to result-list (using previous.end and current.start
                freeTime.add(new Interval(previousInterval.end, currentInterval.start));
            }

            previousInterval = currentInterval;
        }

        return freeTime;
    }
}
