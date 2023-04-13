package PerceptronRebuilt;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        String fileName = intro();
//        String fileName = "wdbc";

        String aVariable;
        ArrayList<Double> weightVector;
        Double theta;
        Double alpha;
        String path = fileName+".data";
        String testPath = fileName+".test.data";
        Double iterationError;
        ReturnSet returnSet;
        Double maxIterationError = 0.05;
        Integer iterator = 1;

        Scanner scanner = new Scanner(new File(path));
        String line = scanner.nextLine();
        String[] lines = line.split(",");

        //set class name to 1, if second cals have different name set to 0
        aVariable = lines[lines.length-1];
        //initialize weight Vector
        weightVector = Perceptron.initializeWeightVector(lines.length-1);
        //initialize theta
        theta = Perceptron.initializeTheta();
        //initialize alpha
        alpha = 0.001;


        returnSet = Perceptron.activatePerceptron(path,theta,weightVector,aVariable,alpha);

        iterationError = Perceptron.checkIterationError(returnSet.resultSets);



        while (iterationError>=maxIterationError && iterator < 1000){

            returnSet = Perceptron.activatePerceptron(path, returnSet.theta, returnSet.weightVector,aVariable,alpha);
            iterationError = Perceptron.checkIterationError(returnSet.resultSets);


            iterator++;
        }
//--------------------------------------------------------------------
        theta = returnSet.theta;
        weightVector = returnSet.weightVector;

        ArrayList<ResultSet> result = Perceptron.testPerceptron(testPath,theta,weightVector,aVariable);
        System.out.println("============");
        for (ResultSet element : result) {
            System.out.println(element.output+" - "+element.predicted);
        }
        System.out.println("============");
        iterationError = Perceptron.checkIterationError(result);
        System.out.println("Finalny blad: "+iterationError);
        System.out.println("Epoki: "+iterator);
        System.out.println("============");






    }

    public static String intro(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe pliku: ");
        System.out.print(">");
        String line = scanner.nextLine();
        return line;
    }




}
