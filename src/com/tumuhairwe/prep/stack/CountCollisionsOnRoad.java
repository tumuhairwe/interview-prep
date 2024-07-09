package com.tumuhairwe.prep.stack;

import java.util.Stack;

/**
 * LeetCode 2211 (medium) Count collisions on a raod
 *
 * There are n cars on an infinitely long road.
 * The cars are numbered from 0 to n - 1 from left to right and each car is present at a unique point.
 *
 * You are given a 0-indexed string directions of length n.
 * directions[i] can be either 'L', 'R', or 'S' denoting whether the ith car is moving towards the left,
 * towards the right, or staying at its current point respectively. Each moving car has the same speed.
 *
 * The number of collisions can be calculated as follows:
 *
 * When two cars moving in opposite directions collide with each other, the number of collisions
 * increases by 2.
 * When a moving car collides with a stationary car, the number of collisions increases by 1.
 * After a collision, the cars involved can no longer move and will stay at the point where they collided.
 * Other than that, cars cannot change their state or direction of motion.
 *
 * Return the total number of collisions that will happen on the road.
 */
public class CountCollisionsOnRoad {
    public static void main(String[] args) {
        System.out.println("Should be 5 " +countCollisions("RLRSLL"));
        System.out.println("Should be 0 " +countCollisions("LLRR"));
    }
    public static int countCollisions(String directions){
        char LEFT = 'L';
        char RIGHT = 'R';
        char STATIONARY = 'S';

        //0. init vars
        Stack<Character> stack = new Stack<>();
        int numCollisions = 0;

        //1. iterate directions
        for(char dir : directions.toCharArray()){
            //2.0 process left
            if(!stack.isEmpty() && dir == LEFT && stack.peek() != LEFT){
                // save topOfStack && increment collisions
                char prev = stack.pop();
                numCollisions++;

                //2.1 if top-of-stack was RIGHT -> increment again (i.e. double collision)
                if(prev == RIGHT){
                    numCollisions++;
                }

                //2.2 increment collisions & pop while top of stack is R
                while (!stack.isEmpty() && stack.peek() == RIGHT){
                    numCollisions++;
                    stack.pop();
                }

                //2.3 change state to stay
                stack.push(STATIONARY);
            }
            // 3.0 process stationary (single collision)
            else if(!stack.isEmpty() && dir == STATIONARY && stack.peek() == RIGHT){
                //3.1 increment numCollisions & pop stack while top of stack is R
                while (!stack.isEmpty() && stack.peek() == RIGHT){
                    numCollisions++;
                    stack.pop();
                }

                //3.2 change state to stationary
                stack.push(STATIONARY);
            }
            // 4.0 process right
            else {
                stack.push(dir);
            }
        }
        return numCollisions;
    }
}
