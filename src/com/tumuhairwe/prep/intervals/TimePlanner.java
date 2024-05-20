package com.tumuhairwe.prep.intervals;

import java.util.*;

/**
 * LeetCode
 * Given 2 peoples availability slots ...
 * ref: https://aaronice.gitbook.io/lintcode/sweep-line/meeting-rooms
 *
 * a) find the earliest availability that is common for both of them
 * b) Determine if a person could attend all meetings
 *
 * int[][] == list of { startTime: UnixTimestamp, endTime: UnixTimestamp }
 *
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

        // iterate over both arrays at the same time, keeping track/2nd pointer over the late-occurring schedule
        int pointer_b = 0;
        for (int pointer_a = 0; pointer_a < Math.min(slotsA.length, slotsB.length); pointer_a++) {
            int[] schedule_a = slotsA[pointer_a];
            int[] schedule_b = slotsA[pointer_b];

            if(schedule_a[1] > schedule_b[0]){  // a ends after b starts
                int sharedStart = schedule_b[0] - schedule_a[1];
            }
        }

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
                // no time shared
                else if (a.encloses(b)){    // scenario B : a ENCLOSES b
                    // calculate diff (in Duration)
                    //int diff = b.getEnd() - a.getStart();
                    //Duration.ofSeconds(diff);
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
                        int latestSharedStartTime = Math.max(a.start, b.start) + sharedTime;
                        int earliestSharedEndTime = Math.max(a.end, b.end) - sharedTime;
                        return new int[]{ latestSharedStartTime, earliestSharedEndTime};
                    }
                }
            }
        }
        return null;
    }

}
