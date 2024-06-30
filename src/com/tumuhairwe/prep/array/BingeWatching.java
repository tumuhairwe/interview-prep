package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A friend of alex has gifted a movie collection, and Alex is excited to watch
 * them all as quickly as possible.
 * The duration of the movies is given in an array durations. Where n in
 * durations[n] is the number of movies and each movie's duration is between 1.01 and 3.00 units of time (up to 2 decimal places)
 * Every day, Alex wants to spend no more than 3.00 unites of time watching the movies and to complete the movies in the
 * minimum number of days possible.
 * If a movies is started, Alex watches the complete movie on teh same day.
 *
 * Find the minimum number of days needed to watch all the movies
 *
 * Example:
 * n = 5
 * durations = [1.90, 1.04, 1.25, 2.5, 1.75]
 *
 * Considering 1-based indexing, Alex can watch the movies in a minimum of 3 days as:
 * Day 1: 1st and 2nd movie: 1.90 + 1.04 <= 3.00
 * Day 2: fourth movie: 2.5 <= 3.00
 * Day 3: 3rd and 4th movie: 1.25 + 1.75 =3.00 <= 3.00
 *
 * Function description
 * Complete the functio findMinimumDays in the editor.
 *
 * findMinimumDays has the following params
 * args: - durations[] - the duration of each movie
 * returns: int ( the minimum number of days required to watch all the movies
 *
 * Bill.com interview June 28 2024
 */
public class BingeWatching {

    public static void main(String[] args) {
        float[] durations = {5,
                1.01f,
                1.01f,
                1.01f,
                1.4f,
                2.4f};

        List<Float> durs = new ArrayList<>(List.of(5f,
                1.01f,
                1.01f,
                1.01f,
                1.4f,
                2.4f));
        System.out.println("Should be 4: " + findMinimumDays(durs));
    }

    /**
     * Solution summary (2 pointers converging to middle)
     * - sort the durations (to have min at start of [] and max at end)
     *
     */
    public static int findMinimumDays(List<Float> durations){
        // constraints
        // duration range: 1.01 - 3.00
        // max_duration to spend per day = 3.00
        // movies must be watched completely (not fraction)

        // [1.90, 1.04, 1.25, 2.5, 1.75]
        // [1.04, 1.25, 1.75, 1.90, 2.5]
        // [1.01, 1.32, 1.4, 1.991]

        //0. sort the durations (goal is to have
        Collections.sort(durations);
        //Arrays.sort(durations);

        //1. declare vars
        int numDaysWatched = 0;
        int p1 = 0;
        int p2 = durations.size() - 1;

        // traverse array with 2 pointers
        while (p1 < p2){
            //i= = 1 ( p1=1.04, p2=2.5) -> 2.5 (p2 is processed),
            //i= = 2 ( p1=1.04, p2=1.90) -> 1.04, 1.90, (both processed)
            //i= = 3 ( p1=1.25, p2=1.90) -> 1.25, 1.75 == 3.0 (both processed)/
            //i= = 4 ( p1=3, p2=2) -> 1.25, 1.75 == violated while loop => exit
            // numDaysWatched = 3 -> return
            float combined = durations.get(p2) + durations.get(p1);

            if(combined <= 3.00){
                numDaysWatched++;
                p2--;
                p1++;
            }
            else {
                numDaysWatched++;
                p2--;   // assuming all entries i durations are <= 3.00
            }
        }

        if(p1 == p2){
            numDaysWatched++;
        }

        return numDaysWatched;
    }
}
