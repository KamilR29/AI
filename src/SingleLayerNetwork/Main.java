package SingleLayerNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {




    public static void main(String[] args) throws FileNotFoundException {

        String path = "";
        ArrayList languageList = new ArrayList();
        Scanner scanner = new Scanner(new File(path));


        while (scanner.hasNext()){

            String read = scanner.nextLine();
            String[] readList = read.split(",",1);



        }



    }



}
