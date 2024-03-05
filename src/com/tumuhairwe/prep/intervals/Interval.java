package com.tumuhairwe.prep.intervals;

import java.util.Objects;

class Interval implements Comparable<Interval>{
    int start;
    int end;
    boolean closed;
    public Interval(int start, int end)
    {
        this.start = start;
        this.end = end;
        this.closed = true; // by default, the interval is closed
    }
        
    // set the flag for closed/open
    public void setClosed(boolean closed)
    {
        this.closed = closed;
    }

    @Override
    public int compareTo(Interval o) {
        return Integer.compare(this.start, o.start);
    }

    public boolean contains(int timestamp){
        boolean isInRange = timestamp > start && timestamp < end;
        boolean isOnBoundary = timestamp == start || timestamp == end;
        return isInRange || isOnBoundary;
    }
    public boolean encloses(Interval interval){
        return this.contains(interval.start) && this.contains(interval.end);
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int diff(Interval b){
        int start = Math.min(this.start, b.getStart());
        int end = Math.min(this.end, b.getEnd());
        return end - start;
    }

    public boolean startsBefore(Interval interval){
        return this.start < interval.start;
    }
    public boolean endBefore(Interval interval){
        return this.end < interval.end;
    }

    public boolean intersects(Interval interval){ // overlaps
        return this.start > interval.start || this.end < interval.end;
    }
    public int getIntersection(Interval interval){
        int sharedStartTime = Math.max(this.start, interval.start);
        int sharedEndTime = Math.min(this.end, interval.end);

        return sharedEndTime - sharedStartTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return start == interval.start && end == interval.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    public Interval merge(Interval interval){
        int start = Math.max(this.start, interval.start);
        int end = Math.max(this.end, interval.end);
        return new Interval(start, end);
    }
}