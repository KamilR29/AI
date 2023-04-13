package PerceptronRebuilt;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
    public static ArrayList<ResultSet> testPerceptron(String path, Double theta, ArrayList<Double> weightVector, String aVariable) throws FileNotFoundException {

        String line;
        String[]lines;
        ArrayList<Double> inputVector;
        Integer output;
        Integer predicted;
        ArrayList<ResultSet> resultSets = new ArrayList<>();

        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()){
            line = scanner.nextLine();
            lines = line.split(",");
            inputVector = convertToVector(lines);
            output = Perceptron.countOutput(theta,weightVector,inputVector);
            if(lines[lines.length-1].equals(aVariable)){
                predicted = 1;
            }else {
                predicted = 0;
            }
            resultSets.add(new ResultSet(output,predicted));
        }
        return resultSets;
    }
//    public static ReturnSet activatePerceptron(Object inputVector, Double theta, ArrayList<Double> weightVector, String aVariable, Double alpha) throws FileNotFoundException {
//
//
//        String[]lines;
//
//        Integer output;
//        Integer predicted;
//        ArrayList<ResultSet> resultSets = new ArrayList<>();
//
//
//
//
//
//
//
//
//
//            output = Perceptron.countOutput(theta,weightVector, (ArrayList<Double>) inputVector);
//
//            if(lines[lines.length-1].equals(aVariable)){
//                predicted = 1;
//            }else {
//                predicted = 0;
//            }
//
//            resultSets.add(new ResultSet(output,predicted));
//            weightVector = Perceptron.recountWeightVector(weightVector,alpha,predicted,output,inputVector);
//            theta = Perceptron.recountTheta(theta,alpha,predicted,output);
//
//
//
//
//        ReturnSet returnSet = new ReturnSet(resultSets,theta,weightVector);
//
//        return returnSet;
//
//    }


    public static Double checkIterationError(ArrayList<ResultSet> resultSets){
        Double iterationError = 0.0;
        for (int i = 0; i < resultSets.size(); i++) {
            iterationError = iterationError + Math.pow((resultSets.get(i).predicted-resultSets.get(i).output),2);
        }
        iterationError = iterationError/resultSets.size();

        return iterationError;

    }

    public static ArrayList<Double> convertToVector(String[] lines){
        ArrayList<Double> inputVector = new ArrayList<>();
        for (int i = 0; i < lines.length-1; i++) {
            inputVector.add(Double.valueOf(lines[i]));
        }
        return inputVector;
    }




}
