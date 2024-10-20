package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 909 (medium)
 * You're given an n x n integer matrix 'board' where the cells are labeled from 1 to n^2 in a
 * Boustrophedone style (starting from the bottom_left of the board (i.e. board[n-1][0] and
 * alternating direction in each row
 *
 * You start on square 1 of the board. In each move, starting from curr ... you do the following
 * - choose a destination squre 'next' with a label in the range [curr + 1, min(curr + 6, n^2]
 * This choice simulates the result of a standard 6-sided die roll: i.e.,
 * there are always at most 6 destinations, regardless of the size of the board
 * - If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
 * - The game ends when you reach the square n2.
 *
 * A board square on row r and column c has a snake or ladder if board[r][c] != -1.
 * The destination of that snake or ladder is board[r][c].
 *
 * Note that you only take a snake or ladder at most once per move. If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.
 *
 * For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2.
 * You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
 *
 * Return the least number of moves required to reach the square n2.
 * If it is not possible to reach the square, return -1.
 * Squares 1 and n2 are not the starting points of any snake or ladder.
 * ref: https://leetcode.com/problems/snakes-and-ladders/description/
 */
public class SnakesAndLadders {

    class Entry{
        int moves;
        int squareId;
        Entry(int squareId, int moves){
            this.squareId = squareId;
            this.moves = moves;
        }
    }
    class PositionOnBoard{
        int row;
        int col;
        PositionOnBoard(int r, int c){
            row = r;
            col = c;
        }
    }
    public int snakesAndLadders(int[][] board) {
        int length = board.length;
        final int target = length * length;

        Queue<Entry> que = new LinkedList<>();
        que.add(new Entry(length - 1, 0));

        //2. define visited set
        Set<Integer> visited = new HashSet<>();

        //3. reverse array
        // because we want the zero-th row to start at the bottom of the board
        Collections.reverse(Arrays.asList(board));

        // PQ tc:
        // to get the min => O(1)
        // to heapify => O(n)
        // to push => o(log_n)
        // to pop => O(log_n)

        //4. do Bfs
        while (!que.isEmpty()){
            Entry curr = que.remove();
            int currentSquare = curr.squareId;
            int numMoves = curr.moves;;

            //4.2 roll the dice (to calc nextSquare)
            final int DICE_SIZE = 6;
            for (int i = 0; i < DICE_SIZE; i++) {
                int nextSquare = curr.squareId + i;

                //4.3 translate int (nextSquare) to 2D position on board
                PositionOnBoard pob = getPositionOnBoard(i, length);
                int row = pob.row;
                int col = pob.col;

                //4.4 if isSnake || isLadder -> nextSquare = board[row][col]
                boolean isSnakeOrLadder = board[row][col] != -1;
                if(isSnakeOrLadder){
                    nextSquare = board[row][col];
                }

                //4.5 if is target -> return
                if(nextSquare == target){
                    return numMoves + 1;
                }

                //4.6 mark as visited and add to queue
                if(!visited.contains(nextSquare)){
                    visited.add(nextSquare);
                    que.add(new Entry(nextSquare, numMoves+ 1));
                }
            }
        }

        return -1;
    }

    private PositionOnBoard getPositionOnBoard(int squareId, int length) {
        int row = (squareId - 1) / length;
        int col = (squareId - 1) % length;
        boolean isOdd = row % 2 != 0;
        if(isOdd){
            col = length - 1 - col;
        }
        return new PositionOnBoard(row, col);
    }
}
