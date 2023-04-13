package PerceptronRebuilt;

import java.util.ArrayList;

public class ReturnSet {

    ArrayList<ResultSet> resultSets;
    Double theta;
    ArrayList<Double> weightVector;

    public ReturnSet(ArrayList<ResultSet> resultSets, Double theta, ArrayList<Double> weightVector) {
        this.resultSets = resultSets;
        this.theta = theta;
        this.weightVector = weightVector;
    }
}
