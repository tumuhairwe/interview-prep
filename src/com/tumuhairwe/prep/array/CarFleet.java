package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * LeetCode 853 (medium) Car Fleet
 *
 * There are n cars at given miles away from the starting mile 0, traveling to reach the mile target.
 *
 * You are given two integer array position and speed, both of length n,
 * where position[i] is the starting mile of the ith car and speed[i] is the speed of the ith car in miles per hour.
 * - A car cannot pass another car, but it can catch up and then travel next to it at the speed of the slower car.
 * - A car fleet is a car or cars driving next to each other. The speed of the car fleet is the minimum speed of any car in the fleet.
 * - If a car catches up to a car fleet at the mile target, it will still be considered as part of the car fleet.
 *
 * Return the number of car fleets that will arrive at the destination.
 *
 * ref: https://leetcode.com/problems/car-fleet/description/
 * ref: https://www.youtube.com/watch?v=Pr6T-3yB9RM
 * ref: https://leetcode.com/problems/car-fleet/solutions/5360448/simple-greedy-solution-in-java-beats-75/
 */
public class CarFleet {

    class Car implements Comparable<Car>{
        int position;
        int speed;
        public Car(int position, int speed){
            this.position = position;
            this.speed = speed;
        }

        @Override
        public int compareTo(Car c) {
            return Integer.compare(c.position, position);
        }
    }

    public static void main(String[] args) {

    }

    /**
     * Solution summary
     * - put all pairs (key=position, value=speed) into TreeMap (map that sorts by key) -- with reverseOrder comparator -- to put highest 1st
     * - create stack
     * - for each entry in map, calculate timeToReachTarget
     * - maintain monotonic Stack<Double> of times-to-reach-target
     * - return stack.size()
     */
    public int carFleet(int target, int[] position, int[] speed){
        // we need map in reverse sorted order
        //1. store pairs of position & speed in reverse sorted order i.e. biggest (position) first ... smallest last
        Comparator<Integer> comp = Comparator.reverseOrder();
        TreeMap<Integer, Integer> map = new TreeMap<>(comp);
        for (int i = 0; i < position.length; i++) {
            map.put(position[i], speed[i]);
        }

        //2. create monotonic stack to keep track of time-to-reach target
        Stack<Double> stack = new Stack<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            int pos = entry.getKey();
            int spd = entry.getValue();

            //3. skip any entry that has a time less than the stack.peek()
            double timeToReachTarget = (double)(target - pos) / spd;
            if(!stack.isEmpty() && timeToReachTarget <= stack.peek()){
                continue;
            }

            //4. if time-to-reach-target is greater than stack.peek() ... add it to stack
            stack.push(timeToReachTarget);
        }

        // return total size of stack (i.e. all cars moving at separate speeds) i.e. not crashed
        return stack.size();
    }

    /**
     * Solution summary (maintain monotonic stack of speeds)
     * - Transform position[] and speed[] -> Car[] ....
     * - sort array by position
     * - Create stack of times ...
     * - iterate cars[] ... only timeToReachTarget to monotonic stack if time is > what's on the stack's peek()
     * - At the end, return size-of-stack i.e. number of fleets (car collections) that will move at different speeds.
     *
     * TC: O(n log n)
     * SC: O(n) -- n == length of stack
     */
    public int carFleet_impl2(int target, int[] position, int[] speed){
        //0. sort cars by position
        Car[] cars = new Car[position.length];
        for (int i = 0; i < position.length; i++) {
            cars[i] = new Car(position[i], speed[i]);
        }

        //1. sort cars
        Arrays.sort(cars);  // TC: n log n

        //2. put in stack and combine into fleet if speed is >
        Stack<Double> times = new Stack<>();
        for (Car car : cars){   // TC: O(n)
            double timeToReachTarget = (double) (target - car.position)/car.speed;
            if (!times.isEmpty() && timeToReachTarget <= times.peek()){
                continue;   // merge the 2 cars into a fleet
            }

            times.push(timeToReachTarget);
        }

        return times.size();
    }
}
