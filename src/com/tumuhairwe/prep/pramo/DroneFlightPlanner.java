package com.tumuhairwe.prep.pramo;

/**
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
                //amountEnergy += Math.diff(currentHeight, previousHeight);
                //amountEnergy += currentHeight;
            }
            else if(currentHeight > previousHeight){
                //amountEnergy -= Math.diff(previousHeight, currentHeight); //currentHeight - previousHeight;
            }
            //else

        }

        return amountEnergy;
    }
}
