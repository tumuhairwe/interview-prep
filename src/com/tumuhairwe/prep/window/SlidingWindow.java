package com.tumuhairwe.prep.window;

public class SlidingWindow {

    public static void main(String[] args) {
        String dnaSequence = "ACTG";
    }
}
class Window implements Comparable<Window>{
    int start;
    int end;
    int sum;

    @Override
    public int compareTo(Window o) {
        return Integer.compare(sum, o.sum);
    }
}
