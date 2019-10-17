package by.samtsov.task03.controller;

//import by.samtsov.task03.service.FileReader;

import java.io.*;

public class MainController {
    public void start() {

        new TESTSERVICE().start();
       /* FileReader fileReader = null;
        try {
            fileReader = new FileReader("data/input.txt");

        int c;
        while((c=fileReader.read())!=-1){

            System.out.print((char)c);
        }
        try {
            int strings = fileReader.read();
            System.out.println(strings.length);
            // todo
            /*String text=
            for (int i = 0; i < strings.length; i++) {


                System.out.println(i + " - |"+strings[i]+"|");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
