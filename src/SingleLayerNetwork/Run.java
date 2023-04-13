package SingleLayerNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Run {
    public Run() {
    }

    public void run(String fileName) throws FileNotFoundException {

//        String fileName = intro();
//        String fileName = "wdbc";

        String aVariable;
        ArrayList<Double> weightVector;
        Double theta;
        Double alpha;

        String path = fileName;
        String testPath = fileName;
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


        returnSet = activatePerceptron(path,theta,weightVector,aVariable,alpha);

        iterationError = checkIterationError(returnSet.resultSets);



        while (iterationError>=maxIterationError && iterator < 1000){

            returnSet = activatePerceptron(path, returnSet.theta, returnSet.weightVector,aVariable,alpha);
            iterationError = checkIterationError(returnSet.resultSets);


            iterator++;
        }

        theta = returnSet.theta;
        weightVector = returnSet.weightVector;

        ArrayList<ResultSet> result = testPerceptron(testPath,theta,weightVector,aVariable);
        System.out.println("============");
        for (ResultSet element : result) {
            System.out.println(element.output+" - "+element.predicted);
        }
        System.out.println("============");
        iterationError = checkIterationError(result);
        System.out.println("Finalny blad: "+iterationError);
        System.out.println("Epoki: "+iterator);
        System.out.println("============");






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
    public static ReturnSet activatePerceptron(String path, Double theta, ArrayList<Double> weightVector, String aVariable, Double alpha) throws FileNotFoundException {

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
            weightVector = Perceptron.recountWeightVector(weightVector,alpha,predicted,output,inputVector);
            theta = Perceptron.recountTheta(theta,alpha,predicted,output);


        }


        ReturnSet returnSet = new ReturnSet(resultSets,theta,weightVector);

        return returnSet;

    }
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
    public static String intro(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe pliku: ");
        System.out.print(">");
        String line = scanner.nextLine();
        return line;
    }
    public static void readFile(){

    }




}
