package com.tumuhairwe.prep.array;

/**
 * LeetCode 657
 * There's a robot starting at position (0, 0) on a 2-D plan... given a sequence of its moves
 * determine if the robot ends up at (0,0) after it completes its moves
 *
 * You are given a string moves that represents the move sequence of the robot where moves[i] represents its ith move. Valid moves are 'R' (right), 'L' (left), 'U' (up), and 'D' (down).
 *
 * Return true if the robot returns to the origin after it finishes all of its moves, or false otherwise.
 *
 * Note: The way that the robot is "facing" is irrelevant. 'R' will always make the robot move to the right once, 'L' will always make it move left, etc. Also, assume that the magnitude of the robot's movement is the same for each move.
 */
public class RobotReturnToOrigin {

    public static void main(String[] args) {
        String s1 = "UD";
        String s2 = "LL";
        String s3 = "RLUURDDDLU";


    }
    /**
     * Solution summary
     * - init x=0, y=0;
     * - iterate chars in string ... map U, D, L, R to direction on 2D plane
     * - return if (x == 0 && y == 0)
     */
    public static boolean judgeCircle(String moves){
        int x = 0;
        int y = 0;

        //for(int i=; i<moves.length; i++) {  char ch = moves.toCharArray*()[i] == TLE
        for(char ch : moves.toCharArray()){
            if (ch == 'U'){
                y--;
            }
            else if (ch == 'D'){
                y++;
            }
            else if (ch == 'R'){
                x++;
            }
            else if (ch == 'L'){
                x--;
            }
        }

        return x == 0 && y == 0;
    }
}
