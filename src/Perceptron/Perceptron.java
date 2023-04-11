package Perceptron;

import java.util.ArrayList;
import java.util.Random;

public class Perceptron {



    public static ArrayList<Double> initializeWeightVector(Integer size){
        ArrayList<Double> weightsVector = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i <size ; i++) {
            weightsVector.add(random.nextDouble());
        }
        return weightsVector;
    }
    public static Double initializeTheta(){
        double theta;
        Random random = new Random();
        theta = random.nextDouble();
        return theta;
    }

    public static int countOutput(Double theta, ArrayList<Double> weightsVector, ArrayList<Double> inputVector){

        Double net = 0.0;

        for (int i = 0; i < weightsVector.size(); i++) {
            net += weightsVector.get(i)*inputVector.get(i);
        }
        net -=theta;

        if (net>=0){
            return 1;
        }
            return 0;

    }
    public static ArrayList<Double> recountWeightVector(ArrayList<Double> weightVector, Double alpha, Integer predicted, Integer output, ArrayList<Double> inputVector ){

        ArrayList<Double> newWeightVector = new ArrayList<>();
        Double delta = alpha*(predicted-output);
        ArrayList<Double> tmpVector = new ArrayList<>();
        for (int i = 0; i < inputVector.size(); i++) {
            tmpVector.add(inputVector.get(i) * delta);
        }
        for (int i = 0; i < weightVector.size(); i++) {
            newWeightVector.add(weightVector.get(i)+tmpVector.get(i));
        }

        return newWeightVector;

    }
    public  static Double recountTheta(Double theta ,Double alpha, Integer predicted, Integer output){


        Double newTheta = theta - alpha * (predicted - output);
        return newTheta;

    }




}
