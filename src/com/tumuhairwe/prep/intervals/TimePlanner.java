package com.tumuhairwe.prep.intervals;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Given 2 peoples availability slots ...
 * ref: https://aaronice.gitbook.io/lintcode/sweep-line/meeting-rooms
 *
 * a) find the earliest availability that is common for both of them
 * b) Determine if a person could attend all meeting
 *
 * int[][] == list of { startTime: UnixTimestamp, endTime: UnixTimestamp }
 */
public class TimePlanner {
    public static void main(String[] args) {

    }

    // TC = O(n_squared) => bcoz we are calculating the diff between the upper bound of each
    public static int[] meetingPlanner(int[][] slotsA, int[][] slotsB, int dur) {
        // TreeSet will sort by Comparator of Interval (uses startTime)
        Set<Interval> slotAAvailability = new TreeSet<>();
        for (int i = 0; i < slotsA.length; i++) {
            slotAAvailability.add(new Interval(slotsA[i][0], slotsA[i][1]));
        }

        Set<Interval> slotBAvailability = new TreeSet<>();
        for (int i = 0; i < slotsB.length; i++) {
            slotBAvailability.add(new Interval(slotsA[i][0], slotsA[i][1]));
        }

        /*
        List<Interval> slotAAvailability = Arrays.asList(slotsB)
                .stream()
                .map(arr -> new Interval(arr[0], arr[1]))
                .sorted()
                .collect(Collectors.toList());
        List<Interval> slotBAvailability = Arrays.asList(slotsA)
                .stream()
                .map(arr -> new Interval(arr[0], arr[1]))
                .sorted()
                .collect(Collectors.toList());
        */
        // find intersection between slotA and slotB
        for (Interval a : slotAAvailability){
            // if A intersects with any of B's availability
                // find the first b that
            for (Interval b : slotBAvailability){
                if(a.equals(b)){    // scenario A : EQUaL
                    int diff = a.getEnd() - a.getStart();
                    if(diff >= dur){
                        // we have a match
                        return new int[]{ a.start, a.end};
                    }
                    else continue;  // same duration/interval but its too short for dur
                }
                else if (a.encloses(b)){    // scenario B : a ENCLOSES b
                    // calculate diff (in Duration)
                    int diff = b.getEnd() - a.getStart();
                    Duration.ofSeconds(diff);
                }
                else if(a.intersects(b)){   // scenario c: a INTERSECT b
                    int sharedTime = 0;
                    if(a.startsBefore(b)){
                        sharedTime = a.getIntersection(b);
                    }
                    else if (b.startsBefore(a)){
                        sharedTime = b.getIntersection(a);
                    }

                    if(sharedTime >= dur){
                        int latestSharedStartTime = a.getStart() + sharedTime;
                        int earliestSharedEndTime = a.getEnd() - sharedTime;
                        return new int[]{ latestSharedStartTime, earliestSharedEndTime};
                    }
                }
            }
        }
        return null;
    }

}
