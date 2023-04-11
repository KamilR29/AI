package kNearestNeighbors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        intro();

    }
    public static void intro() throws FileNotFoundException{

        boolean isEnd = true;
        while (isEnd){
            Scanner dataR = new Scanner(System.in);
            System.out.println("========================================");
            System.out.println("Wycierz opcje:");
            System.out.println("1 - Wczytanie danych z pliku");
            System.out.println("2 - Wprowadzenie własnych danych");
            System.out.println("3 - Zakończ porgram");
            System.out.println("========================================");
            System.out.print(">");
            String dataS = dataR.nextLine();
            if(dataS.equals("1")){
                System.out.println("Podaj nazwe pliku");
                System.out.print(">");
                String fileName = dataR.nextLine();
                System.out.println("Podaj k sąsiadów");
                System.out.print(">");
                String readK = dataR.nextLine();
                Scanner read = new Scanner(new File(fileName+".test.data"));

                int k = Integer.parseInt(readK);
                int sum = 0;
                int sumCorrect = 0;

                while (read.hasNext()) {
                    String data = read.nextLine();
                    int result = knn(data, k,fileName);
                    sum++;
                    sumCorrect += result;
                }
                System.out.println("-------------------------------------------------------------");
                System.out.println("Poprawność klasyfikacji przy k = " + k + " wynosi " + sumCorrect + "/" + sum);
                System.out.println("-------------------------------------------------------------");


            } else if (dataS.equals("2")) {
                System.out.println("Podaj nazwe pliku treningowego");
                System.out.print(">");
                String fileName = dataR.nextLine();
                System.out.println("Podaj k sąsiadów");
                System.out.print(">");
                String readK = dataR.nextLine();

                System.out.println("Podaj dane dla przykładu tesowego");
                System.out.print(">");
                String readL = dataR.nextLine();
                int k = Integer.parseInt(readK);
                int sum = 0;
                int sumCorrect = 0;
                int result = knn(readL, k,fileName);
                sum++;
                sumCorrect += result;

                System.out.println("-------------------------------------------------------------");
                System.out.println("Poprawność klasyfikacji przy k = " + k + " wynosi " + sumCorrect + "/" + sum);
                System.out.println("-------------------------------------------------------------");


            } else if (dataS.equals("3")) {

                isEnd = false;

            }

        }

    }
    public static int knn(String dataTest, int k, String fileName) throws FileNotFoundException {


        Scanner scanner = new Scanner(new File(fileName+".data"));

        ArrayList<ResultVector> list = new ArrayList<>();



        String[] testVector = dataTest.split(",");
        String data;
        String[] v2;
        double resultV = 0;


        while (scanner.hasNext()){
            data = scanner.nextLine();
            v2 = data.split(",");




            for (int i = 0; i <v2.length-1 ; i++) {

                resultV += Math.pow((Double.parseDouble(v2[i]) - Double.parseDouble(testVector[i])),2);
            }
            resultV = Math.sqrt(resultV);





            if(list.size()<k){
                list.add(new ResultVector(v2[v2.length-1],resultV));
            } else{
                Collections.sort(list);
                Collections.reverse(list);

                if (list.get(0).getV()>resultV) {
                    list.remove(0);
                    list.add(new ResultVector(v2[v2.length-1],resultV));
                }
            }


        }




        Map<String,Integer> map = new TreeMap<>();



        for (ResultVector element:list) {

            if(map.containsKey(element.name)){
                map.put(element.name, map.get(element.name)+1);
            }else {
                map.put(element.name,1);
            }

        }


        int max = 0;
        String result = "";
        for (Map.Entry<String,Integer> e:map.entrySet()) {
            if(e.getValue() > max){
                max = e.getValue();
                result = e.getKey();
            }
        }
        if (testVector[testVector.length-1].equals(result)){
            return 1;

        }else {
            return 0;
        }


    }

}
class ResultVector implements Comparable<ResultVector>{
    String name;
    double v;

    public ResultVector(String name, double v) {
        this.name = name;
        this.v = v;
    }

    public double getV() {
        return v;
    }

    @Override
    public int compareTo(ResultVector r) {
        return Double.compare(this.getV(), r.getV());
    }
}
