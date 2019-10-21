package by.samtsov.task03.controller;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MainControllerTest {

    @Test
    public void testChoseFile() {
        new MainController().start();
    }

}