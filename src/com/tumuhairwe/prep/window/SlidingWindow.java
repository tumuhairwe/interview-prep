package com.tumuhairwe.prep.window;

public class SlidingWindow implements Comparable<SlidingWindow>{
    int start;
    int end;
    int sum;

    @Override
    public int compareTo(SlidingWindow o) {
        return Integer.compare(sum, o.sum);
    }
}
