package SingleLayerNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCSV {

    public static void read() throws FileNotFoundException {

        String path = "";
        ArrayList languageList = new ArrayList();
        Scanner scanner = new Scanner(new File(path));


        while (scanner.hasNext()){

            String read = scanner.nextLine();
            String[] readList = read.split(",",1);
            Letters letters = convert(readList[1]);



        }
    }
    public static Letters convert(String text){

        Letters letters = new Letters();
        char[] chars = text.toCharArray();

        for (char element:chars) {

            switch (element){
                case 'a' -> letters.a += 1;
                case 'b' -> letters.b += 1;
                case 'c' -> letters.c += 1;
                case 'd' -> letters.d += 1;
                case 'e' -> letters.e += 1;
                case 'f' -> letters.f += 1;
                case 'g' -> letters.g += 1;
                case 'h' -> letters.h += 1;
                case 'i' -> letters.i += 1;
                case 'j' -> letters.j += 1;
                case 'k' -> letters.k += 1;
                case 'l' -> letters.l += 1;
                case 'm' -> letters.m += 1;
                case 'n' -> letters.n += 1;
                case 'o' -> letters.o += 1;
                case 'p' -> letters.p += 1;
                case 'r' -> letters.r += 1;
                case 's' -> letters.s += 1;
                case 't' -> letters.t += 1;
                case 'u' -> letters.u += 1;
                case 'w' -> letters.w += 1;
                case 'y' -> letters.y += 1;
                case 'z' -> letters.z += 1;
                case 'x' -> letters.x += 1;
                case 'v' -> letters.v += 1;
                case 'q' -> letters.q += 1;

            }


        }
        return letters;

    }




}
