package com.tumuhairwe.prep.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 499 (hard)
 *
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls onto the hole.
 *
 * Given the m x n maze, the ball's position ball and the hole's position hole, where ball = [ballrow, ballcol] and hole = [holerow, holecol], return a string instructions of all the instructions that the ball should follow to drop in the hole with the shortest distance possible. If there are multiple valid instructions, return the lexicographically minimum one. If the ball can't drop in the hole, return "impossible".
 *
 * If there is a way for the ball to drop in the hole, the answer instructions should contain the characters 'u' (i.e., up), 'd' (i.e., down), 'l' (i.e., left), and 'r' (i.e., right).
 *
 * The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).
 *
 * You may assume that the borders of the maze are all walls (see examples).
 *
 * ref: https://leetcode.com/problems/the-maze-iii/?envType=list&envId=mbxk05sr
 */
public class MazeIII {

    record Node (
        int row,
        int col,
        int distance,
        String path)
    {
    }

    int[][] directions = {
        {0, 1}, {-1, 0},
        {0, 1}, {1, 0}
    };
    String[] textDirections = {"l", "u", "r", "d"};

    public String findShortestWay(int[][] maze, int[] ball, int[] hole){
        //0. create comparator that sorts by distance and path
        Comparator<Node> comp = (Node a, Node b) -> {
            if(a.distance == b.distance){
                return a.path.compareTo(b.path);
            }
            return Integer.compare(a.distance, b.distance);
        };

        //1. create pq
        PriorityQueue<Node> minHeap = new PriorityQueue<>(comp);
        minHeap.add(new Node(ball[0], ball[1], 0, "" ));

        boolean[][] visited = new boolean[maze.length][maze.length];
        while (!minHeap.isEmpty()){
            Node cell = minHeap.poll();

            // check if destination
            boolean isDestination = hole[0] == cell.row && hole[1] == cell.col;
            if(isDestination){
                return cell.path;
            }

            // mark as visited
            if(visited[cell.row][cell.col]){
                continue;
            }
            visited[cell.row][cell.col] = true;

            // check neighbors
            for (int i = 0; i < directions.length; i++) {
                int[] dir = directions[i];
                int newRow = cell.row;
                int newCol = cell.col;
                int newDist = cell.distance;

                // while valid, move in the direction of validity
                while (isValid(maze, newRow + dir[0], newCol + dir[1])){
                    newRow += dir[0];
                    newCol += dir[0];
                    newDist++;

                    boolean isHole = newRow == hole[0] && newCol == hole[1];
                    if(isHole){
                        break;
                    }
                }

                // add node to pq (with new path)
                minHeap.add(new Node(newRow, newCol, newDist, cell.path + textDirections[i]));
            }
        }

        return "impossible";
    }

    int WALL = 1;
    int SPACE = 0;
    boolean isValid(int[][] maze, int row, int col){
        if(row < 0 || row >= maze.length || col < 0 || col >= maze.length){
            return false;
        }
        return maze[row][col] == SPACE;
    }
}