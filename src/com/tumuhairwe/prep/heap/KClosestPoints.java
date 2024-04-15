package com.tumuhairwe.prep.heap;

import java.util.*;

/**
 * Given a list of points on a plane where the plane is a 2D array
 * with x and y coordinates, find the K closest points to the origin (0,0)
 *
 * Note: Here, the distance between two points on a plane is the Euclidean distance:
 * is square_root( x_squared + y_squared)
 *
 * Solution:
 * - Implement Point with compareTo() -- to maintain sorted order of points
 * - Create PriorityQueue<Point>
 * - While pq.size() < K ... look thru 2D array and add Point to pq
 * - When PQ.size() == 2D array ... check pq.peek() and compare with currentPoint
 * - if currentPoint is closer, add to maxHeap / pq
 * - return PQ (should hav exactly K points)
 */
public class KClosestPoints {

    public static List<Point> kClosest(Point[] points, int k){
        //Comparator<Point> comp = (p1, p2) -> p2.distFromOrigin() - p1.distFromOrigin();
        //PriorityQueue<Point> maxHeap2 = new PriorityQueue<>(comp);    // if Point wasn't comparable

        // min-heap will order Comparable<Point> based on formulae defined in distanceFromOrigin()
        PriorityQueue<Point> maxHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            maxHeap.add(points[i]);
        }

        for (int i=k; i< points.length; i++){
            if(points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()){
                maxHeap.poll();
                maxHeap.add(points[i]);
            }
        }

        return new ArrayList<>(maxHeap);
    }

    // Driver code
    public static void main(String[] args) {
        Point[] pointsOne = new Point[] {
                new Point(1, 3), new Point(3, 4), new Point(2, -1)
        };
        Point[] pointsTwo = new Point[] {
                new Point(1, 3), new Point(2, 4), new Point(2, -1), new Point(-2, 2), new Point(5, 3), new Point(3, -2)
        };
        Point[] pointsThree = new Point[] {
                new Point(1, 3), new Point(5, 3), new Point(3, -2), new Point(-2, 2)
        };
        Point[] pointsFour = new Point[] {
                new Point(2, -1), new Point(-2, 2), new Point(1, 3), new Point(2, 4)
        };
        Point[] pointsFive = new Point[] {
                new Point(1, 3), new Point(2, 4), new Point(2, -1), new Point(-2, 2), new Point(5, 3), new Point(3, -2), new Point(5, 3), new Point(3, -2)
        };
        Point[][] points = {pointsOne, pointsTwo, pointsThree, pointsFour, pointsFive};
        int[] kList = {2, 3, 1, 4, 5};
        for (int i = 0; i < points.length; i++) {

            System.out.print((i + 1) + ".\tSet of points: ");
            for (Point p: points[i])
                System.out.print("[" + p.x + " , " + p.y + "] ");
            System.out.println("\n\tK: " + kList[i]);
            List<Point> result = KClosestPoints.kClosest(points[i], kList[i]);
            System.out.print("\tHere are the k = " + kList[i] + " points closest to the origin(0, 0): ");
            for (Point p: result)
                System.out.print("[" + p.x + " , " + p.y + "] ");
            System.out.println("\n");
            //System.out.println(KClosestPoints.repeat("-", 100));
        }
    }
}

class Point implements Comparable<Point> {
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int distFromOrigin(){
        // ignoring sqrt
        int x_squared = x * x;
        int y_squared = y * y;
        return x_squared + y_squared;
    }

    @Override
    public int compareTo(Point p2) {
        return p2.distFromOrigin() - this.distFromOrigin();
    }
}
