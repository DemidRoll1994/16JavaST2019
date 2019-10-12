package by.samtsov.task03.controller;

import by.samtsov.task03.service.FileReader;

import java.io.IOException;

public class MainController {
    public void start() {
        FileReader fileReader = new FileReader();
        try {
            String[] strings = fileReader.read("data/input.txt");
            // todo
            /*String text=
            for (int i = 0; i < strings.length; i++) {


                System.out.println(i + " - |"+strings[i]+"|");
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
