package com.tumuhairwe.prep.pramo;

import java.util.Arrays;

/**
 * Given a 2D array (matrix) inputMatrix of integers, create a function spiralCopy that copies inputMatrix’s values into a 1D array in a spiral order, clockwise. Your function then should return that array. Analyze the time and space complexities of your solution.
 *
 * Example:
 *
 * input:  inputMatrix  = [ [1,    2,   3,  4,    5],
 *                          [6,    7,   8,  9,   10],
 *                          [11,  12,  13,  14,  15],
 *                          [16,  17,  18,  19,  20] ]
 *
 * output: [1, 2, 3, 4, 5, 10, 15, 20, 19, 18, 17, 16, 11, 6, 7, 8, 9, 14, 13, 12]
 * See the illustration below to understand better what a clockwise spiral order looks like.
 */
public class MatrixSpiralCopy {

    enum Direction{
        LEFT_TO_RIGHT (1),
        UP_DOWN (2),
        RIGHT_TO_LEFT (3),
        DOWN_UP (0);

        int nextDirection;

        Direction(int next){
            this.nextDirection = next;
        }

        Direction valueOf(int orddinal){
            return Arrays.stream(Direction.values()).
                    filter(val -> val.ordinal() == orddinal + 1)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
        }

        public int getNext(){
            return this.nextDirection;
        }
    }

    static class Cell{
        int x;
        int y;
        public Cell(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    static int[] spiralCopy(int[][] inputMatrix) {

        // 0. create outpyt array
        int length = inputMatrix.length;
        int width = inputMatrix[0].length;
        int[] output = new int[length * width];

        // 1. populate the array
        Direction direction = Direction.LEFT_TO_RIGHT;
        int filledSpots = 0;

        int rowStart = 0;
        int rowEnd = inputMatrix.length - 1;
        int colStart = 0;
        int colEnd = inputMatrix[0].length;
        Cell start = new Cell(rowStart , colStart);
        Cell end = new Cell(rowEnd, colEnd);

        while (rowStart <= rowEnd && colStart<= colEnd){
            // traverse array
            int penalty = getPenalty(start, direction, length, width); // determine the penalty
            Cell endingPoint = determineEndingPoint(start, direction, penalty);

            int[] result = traverse(direction, inputMatrix, start, endingPoint);

            // copy contents of results in output
            for(int m=0; m < filledSpots + result.length; m++){
                output[filledSpots++] = result[m];
            }
            filledSpots += result.length;

            // update staring point
            //start = staringPoint;

            // determine next direction
            direction = direction.valueOf(direction.getNext());
        }

        return output;
    }

    static Cell determineEndingPoint(Cell start, Direction direction, int penalty){
        Cell end= null;
        int distance = determineDistance(start, end, direction, penalty);

        if(direction == Direction.LEFT_TO_RIGHT){
            end = new Cell(start.x, start.y + distance);
        }
        else if(direction == Direction.UP_DOWN){
            end = new Cell(start.x, start.y + distance);
        }

        return end;
    }

    static int getPenalty(Cell start, Direction direction, int length, int width){
        return 0;
    }
    static int determineDistance(Cell start, Cell end, Direction direction, int widht){
        int distance = -1;
        if(direction == Direction.LEFT_TO_RIGHT || direction == Direction.RIGHT_TO_LEFT){
            distance = (end.x - start.x);
        }
        else if(direction == Direction.UP_DOWN || direction == Direction.DOWN_UP){
            distance = (end.y - start.y);
        }

        return distance;
    }

    static int[] traverse(Direction nextDirection, int[][] inputMatrix, Cell start, Cell end){
        int[] result = new int[start.x * end.y];
        int row = start.x;
        int col = start.y;
        int index = 0;

        if(nextDirection == Direction.LEFT_TO_RIGHT){
            while(row != end.x && col != end.x){
                if(nextDirection == Direction.LEFT_TO_RIGHT){
                    result[index++] = inputMatrix[row][col++];
                }
                else if(nextDirection == Direction.UP_DOWN){
                    result[index++] = inputMatrix[row++][col];
                }
            }
        }
        else if(nextDirection == Direction.UP_DOWN){
            while(row != end.x && col != end.x){

            }
        }
        else if(nextDirection == Direction.RIGHT_TO_LEFT){
            while(row != end.x && col != end.x){
                result[index++] = inputMatrix[row][col--];
            }
        }
        else if(nextDirection == Direction.DOWN_UP){
            while(row != end.x && col != end.x){
                result[index++] = inputMatrix[row--][col];
            }
        }

        return result;
    }
}
