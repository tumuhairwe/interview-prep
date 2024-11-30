package com.tumuhairwe.prep.strings;

/**
 * LeetCode 2446 (easy)
 *
 * - Given 2 String[] that represent 2 inclusive events that happened ON THE SAME DAY
 * event_1 and event_2 where
 * event_1 = [startTime_1, endTime_1]
 * event_2 = [startTime_2, endTime_2]
 * - And given that event times are in 24hr format of HH:MM
 * - A conflict happens when 2 events have some non-empty intersection (a time common to both events)
 * Return true if there's a conflict between 2 events, False otherwise
 */
public class DetermineIf2EventsHaveConflict {
    public static void main(String[] args) {
        //String[][]
        String[] e1 = {"01:15","02:00"};
        String[] e2 = {"02:00","03:00"};
        System.out.println("Should be true: " + haveConflict(e1, e2));

        String[] c1 = {"01:00","02:00"};
        String[] c2 = {"01:20","03:00"};
        System.out.println("Should be true: " + haveConflict(c1, c2));

        String[] d1 = {"10:00","11:00"};
        String[] d2 = {"14:00","15:00"};
        System.out.println("Should be false: " + haveConflict(d1, d2));
    }
    public static boolean haveConflict(String[] event1, String[] event2) {
        String event1_start = event1[0];;
        String event1_end = event1[1];

        String event2_start = event2[0];;
        String event2_end = event2[1];

        int c1 = event2_end.compareTo(event1_start);
        int c2 = event2_start.compareTo(event1_end);

        return c1 <= 0 && c2 >= 0;
    }
}
