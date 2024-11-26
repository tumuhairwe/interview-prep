package com.tumuhairwe.prep.pramp;

import java.util.*;
import java.util.stream.Collectors;

public class TimePlanner {

    static int[] meetingPlanner(int[][] slotsA, int[][] slotsB, int dur) {
        // your code goes here
        Set<Interval> slotAiItervals= new TreeSet<>();
        Set<Interval> slotBIntervals = new TreeSet<>();
        for(int i=0; i<slotsA.length; i++){
            Interval it = new Interval(slotsA[i][0], slotsA[i][1]);
            slotAiItervals.add(it);
        }
        for(int i=0; i<slotsB.length; i++){
            Interval it = new Interval(slotsB[i][0], slotsB[i][1]);
            slotBIntervals.add(it);
        }


        Set<Interval> relevantSlotA = slotAiItervals
                .stream()
                .filter(i -> i.getDuration() >= dur)
                .collect(Collectors.toSet());

        Set<Interval> relevantSlotB = slotBIntervals
                .stream()
                .filter(i -> i.getDuration() >= dur)
                .collect(Collectors.toSet());

        Interval earliestSlot = null;
        for(Interval a : relevantSlotA){
            for(Interval b : relevantSlotB){
                if(a.equals(b)){
                    earliestSlot = a;
                }
                else if(a.encloses(b)){
                    earliestSlot = a;
                }
                else if(a.startsBefore(b)){
                    earliestSlot = a;
                }
                else if(b.startsBefore(a)){
                    earliestSlot = b;
                }
            }
        }

        return new int[]{earliestSlot.start, earliestSlot.end };
    }

    public static void main(String[] args) {

    }

    public static class Interval implements Comparable<Interval>{
        int start;
        int end;

        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
        public int compareTo(Interval i){
            return this.start - i.start;
        }

        boolean intersects(Interval i){
            boolean runsInto = i.start >= this.start;
            boolean bleedsInto = i.end < this.end;
            return runsInto || bleedsInto;
        }

        boolean encloses(Interval i){
            return this.start >= i.start && this.end >= i.end;
        }

        int getDuration(){
            return this.end - this.start;
        }

        boolean startsBefore(Interval i){
            return this.start < i.start;
        }
        boolean endsBefore(Interval i){
            return this.end < i.end;
        }
    }
}