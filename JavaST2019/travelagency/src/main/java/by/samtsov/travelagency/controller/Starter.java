package by.samtsov.travelagency.controller;

import by.samtsov.travelagency.services.FileReadWriter;

import java.io.IOException;

/**
 * Created by samtsov on 17.09.19.
 */
public class Starter {
    public static void main(String[] args) {
        try {
            new FileReadWriter().readFile2("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //todo Starter
}
