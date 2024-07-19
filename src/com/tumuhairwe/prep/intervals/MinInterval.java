package com.tumuhairwe.prep.intervals;

import java.util.*;

/**
 * LeetCode 1851 (hard). Minimum Interval to Include Each Query
 *
 * You are given a 2D integer array intervals, where intervals[i] = [left_i, right_i] describes the ith interval starting
 * at left_i and ending at right_i (inclusive).
 *
 * The size of an interval is defined as the number of integers it contains, or more formally right_i - left_i + 1.
 * You are also given an integer array queries.
 *
 * The answer to the jth query is the size of the smallest interval i such that left_i <= queries[j] <= right_i.
 * If no such interval exists, the answer is -1.
 * Return an array containing the answers to the queries.
 *
 * ref: https://www.youtube.com/watch?v=5hQ5WWW5awQ
 * ref: https://leetcode.com/problems/minimum-interval-to-include-each-query/?envType=problem-list-v2&envId=plakya4j
 */
public class MinInterval {
    public static void main(String[] args) {
        int[][] intervals = {
            {1,4},{2,4},{3,6},{4,4}
        };
        int[] queries = {2,3,4,5};
        System.out.println(Arrays.toString(new MinInterval().minInterval(intervals, queries)));
    }

    class Interval{
        int start;
        int end;
        int length;
        public Interval(int[] interval){
            this.start = interval[0];
            this.end = interval[1];
            this.length = end - start + 1;
        }
    }

    /**
     * Solution summary
     * - Sort intervals by start
     * - Sort queries
     * - Create minLength Map
     *      - loop thru sort queries [] ...
     *         - find all intervals that end before _each_ query
     *         - put those interval in a pq (pq is sorted by length)
     *         - if at end, pq is not empty, put length at top of pq in minLength map (key=query, val=length)
     * - populate result from minLength map and return
     */
    public int[] minInterval(int[][] intervals, int[] queries) {
        //0. sort queries
        Comparator<int[]> orderByStart = Comparator.comparingInt(arr -> arr[0]);
        Arrays.sort(intervals, orderByStart);

        //1. clone & sort queries
        int[] queriesClone = queries.clone();
        Arrays.sort(queriesClone);

        //2. declare vars
        Map<Integer, Integer> minLengthMap = new HashMap<>(); // key=query, val=minLength

        Comparator<Interval> orderByLength = Comparator.comparingInt(i -> i.length);
        PriorityQueue<Interval> pq = new PriorityQueue<>(orderByLength);

        //3. build map of { queryId, minLength }
        int idx =0;
        for(int query : queriesClone){

            while (idx < intervals.length && intervals[idx][0] <= query) {
                pq.offer(new Interval(intervals[idx]));
                idx++;
            }

            while (!pq.isEmpty() && pq.peek().end < query) {
                pq.poll();
            }

            if(!pq.isEmpty()){
                minLengthMap.put(query, pq.peek().length);
            }
        }

        // 3. construct result
        int[] result = new int[queries.length];
        for (int j = 0; j < result.length; j++) {
            int query = queries[j];
            result[j] = minLengthMap.getOrDefault(query, -1);
        }
        return result;
    }

    /**
     * Solution summary (correct but fail with TimeLimitExceeded)
     * - Create a Range class with (start, end) will encapsulate the contains(int) operation and getSize() operation
     * - Transform 2D array into List of ranges
     * - for each query,
     *      - filter listOfRanges for a range that contains that query
     *      - sort the results by range.size()
     *      - find the first/smallest one
     *      - set answer[i] = smallestRange.size
     */
//    public int[] minInterval(int[][] intervals, int[] queries) {
//            //perform topological sort or ranges
////            List<Range> listOfRanges = new ArrayList<>();
////            for (int i = 0; i < intervals.length; i++) {
////                int left = intervals[i][0];
////                int right = intervals[i][1];
////                Range r = new Range(left, right);
////                listOfRanges.add(r);
////            }
//        List<Range> listOfRanges = Arrays.stream(intervals)
//                .map(ints -> new Range(ints[0], ints[1]))
//                .collect(Collectors.toList());
//
//            int[] answer = new int[queries.length];
//            for (int i = 0; i < queries.length; i++) {
//
//                // treeSet will sort ranges by Range.getSize()
//                int query = queries[i];
//                Optional<Range> smallestMatchingRange = listOfRanges.stream()
//                        .filter(range -> range.contains(query))
//                        .sorted(Comparator.comparingInt(r -> r.getSize()))
//                        .findFirst();
//
//                int val = -1;
//                if(smallestMatchingRange.isPresent()){
//                    val = smallestMatchingRange.get().getSize();
//                }
//
//                answer[i] = val;
//            }
//            return answer;
//
//    }
//
//    class Range implements Comparable<Range>{
//        int start;
//        int end;
//        public Range(int from, int to){
//            this.start = from;
//            this.end = to;
//        }
//        public boolean contains(int time){
//            return time >= start && time <= end;
//        }
//        public int getSize(){
//            return Math.abs(end - start) + 1;
//        }
//        public int compareTo(Range range){
//            // if(start != range.start){
//            //     return Integer.compare(this.start, range.start);
//            // }
//            // return Integer.compare(this.end, range.end);
//            return Integer.compare(getSize(), range.getSize());
//        }
//        public String toString(){
//            return "[" + start + " - " + end + "]";
//        }
//        public Range merge(Range r){
//            int from = Math.min(this.start, r.start);
//            int end = Math.max(this.end, r.end);
//            return new Range(from, end);
//        }
//    }
}