package com.tumuhairwe.prep;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 362 (medium)
 *
 * Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).
 *
 * Your system should accept a timestamp parameter (in seconds granularity), and you may assume that calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing). Several hits may arrive roughly at the same time.
 *
 * Implement the HitCounter class:
 *
 * HitCounter() Initializes the object of the hit counter system.
 * void hit(int timestamp) Records a hit that happened at timestamp (in seconds). Several hits may happen at the same timestamp.
 * int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).
 */
public class DesignHtCounter {

    private Queue<Integer> hits;
    public DesignHtCounter(){
        hits = new LinkedList<>();
    }
    // just add timestamp to queue
    public void hit(int timestamp){
        hits.add(timestamp);
    }

    /**
     * Solution summary
     * - while queue is not empty ... remove all hits older than 300 seconds ago
     * - return size of hits que
     */
    public int getHits(int timestamp){
        while (!hits.isEmpty()){
            int diff = timestamp - hits.peek();
            if(diff >= 300){
                hits.remove();
            }
            else {
                break;
            }
        }
        return hits.size();
    }
}
