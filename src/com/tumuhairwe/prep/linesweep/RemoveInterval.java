package com.tumuhairwe.prep.linesweep;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1272 Remove Interval (medium) -- sweep line algo
 * (opposite of merge Intervals LeetCode 56)
 * A set of real numbers can be represented as the union of several disjoint intervals where each interval
 * is closed-open [a, b). A real number x is the in the set if one of its intervals [a, b)
 * i.e. a <= x < b
 * Given a sorted list of disjoint intervals, representing a set of real numbers as described above, where
 * intervals[i] = [a, b] represents the intervals [a, b), and given anohter interval "toBeRemoved"
 * Return the set of real numbers with the interval toBeRemoved from intervals
 *
 * ref: https://leetcode.com/problems/remove-interval/description/?envType=problem-list-v2&envId=mzw3cyy6
 * solution: https://leetcode.com/problems/remove-interval/editorial/?envType=problem-list-v2&envId=mzw3cyy6
 */
public class RemoveInterval {
    /**
     * Solution summary
     * There are 3 cases
     * - case a: there's no intersection
     * - case b: there's a left overlap with tbr
     * - case c: there's a right overlap with tbr
     *
     * 0. iterate the intervals (already sorted ... so no need to sort)
     * 1. if there's no overlap ... add current-interval to result
     * 2. if there's a left overlap, newInterval = [startOfCurr, startOfTbr] -> add to result
     * 3. if there's a right overlap, newInterval = [tbrEnd, endOfCurr] -> add to result
     * 4. return result
     *
     * tc: O(n) -- line sweep
     * sc: O(1) -- just for temp vars
     */
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved){
        //0. declare vars

        List<List<Integer>> ans = new ArrayList<>();

        //1. iterate intervals (no need to sort since they're already sorted)
        int tbrStart = toBeRemoved[0];;
        int tbrEnd = toBeRemoved[1];
        for (int[] curr : intervals){
            boolean startsAfterTBRends = curr[0] > tbrEnd;
            boolean endsBeforeTBRstarts = curr[1] < tbrStart;

            //case a: there's no overlap -> just add the interval to result
            if(startsAfterTBRends || endsBeforeTBRstarts){
                ans.add(List.of(curr[0], curr[1]));
            }
            else {
                //case b: left overlap -> keep curr.start up to tbrStart
                if(curr[0] < toBeRemoved[0]){
                    ans.add(List.of(curr[0], tbrStart));
                }
                // case c: right overlap -> keep tbrEnd up to curr.end
                if(curr[1] > toBeRemoved[1]){
                    ans.add(List.of(tbrEnd, curr[1]));
                }
            }
        }
        return ans;
    }
}
