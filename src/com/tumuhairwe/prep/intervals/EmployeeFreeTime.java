package com.tumuhairwe.prep.intervals;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 759 (hard)
 * Given a list of employee schedules (representing working time), where each employee has a list of non-overlapping
 * intervals, and these intervals are in sorted order,
 * return the list of finite intervals representing common, positive-length free time for all employees in sorted order
 *
 * ref: https://leetcode.com/problems/employee-free-time/description/
 */
public class EmployeeFreeTime {

    /**
     * Solution summary
     * - Flatten the list of of list of intervals && sort by start time
     * - iterate thru allIntervals list starting with 1 (prev = index=0, curr=1)
     * - if prev ends before curr, create a new interval of that free time and add to the return-list
     * - else update prev (based on whichever ends 1st, curr or prev)
     * ref: https://leetcode.com/problems/employee-free-time/solutions/113134/simple-java-sort-solution-using-priority-queue-or-just-arraylist/
     */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // flatMap the intervals sort all by start time
        Comparator<Interval> orderByStart = Comparator.comparingInt(i -> i.start);
        // List<Interval> list = schedule.stream().flatMap(l -> l.stream()).collect(Collectors.toList());
        List<Interval> allIntervals = schedule.stream()
                .flatMap(Collection::stream)
                .sorted(orderByStart)
                .collect(Collectors.toList());

        // merge sorted intervals into 1
        List<Interval> freeTime = new ArrayList<>();
        Interval prev = freeTime.get(0);
        for (int i = 1; i < allIntervals.size(); i++) {
            Interval curr = allIntervals.get(i);

            if(prev.end < curr.start){
                freeTime.add(new Interval(prev.end, curr.start));
                prev = curr;
            }
            else{
                prev = prev.end < curr.end ? curr : prev;
            }
        }
        return freeTime;
    }
}
