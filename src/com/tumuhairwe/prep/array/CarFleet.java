package com.tumuhairwe.prep.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * LeetCode 853 (medium) Car Fleet
 *
 * There are n cars at given miles away from the starting mile 0, traveling to reach the mile target.
 *
 * You are given two integer array position and speed, both of length n, where position[i] is the starting mile of the ith car and speed[i] is the speed of the ith car in miles per hour.
 *
 * A car cannot pass another car, but it can catch up and then travel next to it at the speed of the slower car.
 *
 * A car fleet is a car or cars driving next to each other. The speed of the car fleet is the minimum speed of any car in the fleet.
 *
 * If a car catches up to a car fleet at the mile target, it will still be considered as part of the car fleet.
 *
 * Return the number of car fleets that will arrive at the destination.
 *
 * ref: https://leetcode.com/problems/car-fleet/description/
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

    /**
     * Solution summary (maintian monotonic stack of speeds)
     * - Transform position[] and spee[] into Car[] ....
     * - sort array by position
     * - Create stack of times ...
     * - iterate cars[] ... only timeToReachTarget to monotonic stack if time is > what's on the stack's peek()
     * - At the end, return size-of-stack i.e. number of fleets (car collections) that will move at different speeds.
     */
    public int carFleet(int target, int[] position, int[] speed){
        //0. sort cars by position
        Car[] cars = new Car[position.length];
        for (int i = 0; i < position.length; i++) {
            cars[i] = new Car(position[i], speed[i]);
        }

        //1. sort cars
        Arrays.sort(cars);

        //2. put in stack and combine into fleet if speed is >
        Stack<Double> times = new Stack<>();
        for (Car car : cars){
            double timeToReachTarget = (double) (target - car.position)/car.speed;
            if (!times.isEmpty() && timeToReachTarget <= times.peek()){
                continue;   // merge the 2 cars into a fleet
            }

            times.push(timeToReachTarget);
        }

        return times.size();
    }
}
