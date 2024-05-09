package com.tumuhairwe.prep.graphs;


/**
 * Given a driverless care that is located at the (bottom-left) corner of a N x N grid
 * The care is supposed to get to the opposite Northeast/ (top-right) corner of the grid
 *
 *Given n, the size of the grid’s axes, write a function numOfPathsToDest that returns the number of the possible
 * paths the driverless car can take.
 *
 * Solution Summary
 - Iterate a 2D array
 - call a recursive function
 - base-case: not-t-c-ross the diagonal
 - call function 1 cell to the right
 - call function 1 cell to the top

 - track count of all the valid paths

    TC: O(m x n) - where m = rows & n = cols of the grid
    SC: O(m x n) - where m = rows & n = cols of the grid (w
 ref: https://www.pramp.com/challenge/N5LYMbYzyOtbpovQoY7X
*/
class NumberOfPaths {

    public static void main(String[] args) {
        System.out.println(" 1 Should be 1: " + numOfPathsToDestination(1));
        System.out.println(" 2 Should be 1: " + numOfPathsToDestination(2));
        System.out.println(" 3 Should be 2: " + numOfPathsToDestination(3));
        System.out.println(" 4 Should be 5: " + numOfPathsToDestination(4));
        System.out.println(" 5 Should be 5: " + numOfPathsToDestination(5));

        System.out.println(" 6 Should be 42: " + numOfPathsToDestination(6));
        System.out.println(" 17 Should be 35357670: " + numOfPathsToDestination(17));
    }
    public static int numOfPathsToDestination(int n){
        return callBFS(n, 0, 0);
    }
    static int callBFS(int length,  int currentY, int currentX){

        if(currentX == length - 1 && currentY == length - 1){
            return 1;
        }

        //System.out.println("Current X=" + currentX + ", currentY =" + currentY);

        if(currentX >= length || currentY >= length){
            return 0;
        }

        if(currentX >= currentY ){
            int proposeRow_a = currentY + 1;
            int proposeCol_a = currentX;

            int proposeRow_b = currentY;
            int proposeCol_b = currentX + 1;

            int numberOfWays = callBFS(length, proposeRow_a, proposeCol_a);
            numberOfWays += callBFS(length, proposeRow_b, proposeCol_b);

            return numberOfWays;
            //}
        }

        return 0;
    }
}
