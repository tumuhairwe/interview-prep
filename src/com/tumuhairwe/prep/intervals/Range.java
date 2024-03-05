package com.tumuhairwe.prep.intervals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

public class Range implements Comparable<Range>{
    private final int lowerBound;
    private final int upperBound;
    private boolean free;

    public Range(final int lowerBound, final int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        long unixTime = Long.valueOf(123);
        LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTime), TimeZone.getDefault().toZoneId());
    }

    @Override
    public int compareTo(Range range) {
        //LocalDateTime.now().compareTo(LocalDateTime.now());
        return Integer.compare(this.lowerBound, range.lowerBound);
    }
    public boolean contains(int index){
        boolean isInRange = index > this.lowerBound && index < this.upperBound;
        boolean isOnBoundary = this.lowerBound == index || this.upperBound == index;
        return isOnBoundary || isInRange;
    }
    public boolean encloses(Range range){
        return this.contains(range.lowerBound) && this.contains(range.upperBound);
    }
    public boolean intersects(Range range){ // overlaps
        return this.lowerBound > range.lowerBound || this.upperBound < range.upperBound;
    }
    public static Range getIntersection(Range left, Range right){
        int lb = 0;
        int ub = 0;
        if(left.contains(right.lowerBound)){
            lb = right.lowerBound;
        }
//        else if(right.contains(left.lowerBound)){
//            lb = left.lowerBound;
//        }

        if(left.upperBound == right.upperBound){
            ub = left.upperBound;;
        }
        else if (!left.contains(right.upperBound)){
            ub = right.upperBound - left.upperBound;
        }
        else throw new IllegalStateException("Left and Right may not have intersection");
        return new Range(lb, ub);
    }
    public Range merge(Range other){
        int lb = Math.max(this.lowerBound, other.lowerBound) ;
        int up = Math.min(this.upperBound, other.upperBound);
        return new Range(lb, up);
    }

    @Override
    public String toString() {
        return "Range{" +
                "lowerBound=" + lowerBound +
                ", upperBound=" + upperBound +
                '}';
    }

    public static void main(String[] args) {
        Range oneToThree = new Range(1, 3);
        Range twoToThree = new Range(2, 3);
        System.out.println("[1-3] encloses [2-3] " + oneToThree.encloses(twoToThree));

        Range fiveToSeven = new Range(5, 7);
        System.out.println("[5-7] contains 8 " + fiveToSeven.contains(8));

        Range nineToTwelve = new Range(9, 12);
        Range elevenToThirteen = new Range(11, 13);
        System.out.println("[9-10] intersects [11-13] " + nineToTwelve.intersects(elevenToThirteen));

        List<Range> ranges = new ArrayList<>();
        ranges.add(elevenToThirteen);
        ranges.add(fiveToSeven);
        ranges.add(nineToTwelve);
        ranges.add(oneToThree);

        Collections.sort(ranges);
        System.out.println("Sorted => " + ranges);

        System.out.println("Merged: => " + nineToTwelve.merge(elevenToThirteen));

        // space complexity = O(n)
        // time complexity = O(n)
        System.out.println("Intersection: => " + getIntersection(oneToThree, twoToThree));

        Range r1 = new Range(1, 3);
        Range r2 = new Range(5, 6);
        Range r3 = new Range(9, 10);

        Range r4 = new Range(2, 4);
        Range r5 = new Range(7, 8);

        Range r6 = new Range(8, 11);
        Range r7 = new Range(12, 14);
    }
}
