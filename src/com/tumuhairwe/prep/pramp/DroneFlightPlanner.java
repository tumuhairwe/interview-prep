package com.tumuhairwe.prep.pramp;

/**
    - Going up -> -1 KW
    - Going down -> 1 kw
    - sideways -> 0

    - Calculate minimal amount of energy from START to END

    Steps
    1. Iterate over the 2D array
    2. for every movement -> calculate energySpent
    3. Focus on 3rd column (index) == height
 *
 * Example:
 * input:  route = [ [0,   2, 10],
 *                   [3,   5,  0],
 *                   [9,  20,  6],
 *                   [10, 12, 15],
 *                   [10, 10,  8] ]
 *
 * output: 5 # less than 5 kWh and the drone would crash before the finish
 *           # line. More than `5` kWh and it’d end up with excess energy
 *
 * ref: https://www.pramp.com/challenge/BrLMj8M2dVUoY95A9x3X
 * ref: https://abhinavece.gitbooks.io/pramp-interview-questions/content/drone-flight-planner.html
 */
public class DroneFlightPlanner {

    static int calcDroneMinEnergy(int[][] route) {

        int amountEnergy = 0;

        for(int row=1; row<route.length; row++){

//        int row = route[row][0];
//        int col = route[row][1];
            //int distance = route[row][2];

            int previousHeight = route[row - 1][2];
            int currentHeight = route[row][2];

            // calculate amout of energe
            if(previousHeight < currentHeight){
                amountEnergy += currentHeight - previousHeight;
                //amountEnergy += currentHeight;
            }
            else if(currentHeight > previousHeight){
                amountEnergy -= previousHeight - currentHeight; //currentHeight - previousHeight;
            }
            //else  // sideways == same i.e. energy

        }

        return amountEnergy;
    }
}
