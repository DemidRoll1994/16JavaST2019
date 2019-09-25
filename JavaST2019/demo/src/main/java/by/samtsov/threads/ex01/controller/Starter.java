package by.samtsov.threads.ex01.controller;

import by.samtsov.threads.ex01.service.AnyThread;


public class Starter {

    public static void main(String[] args) {
        AnyThread anyThread = new AnyThread();
        anyThread.setName("tot samiy potok");
        anyThread.start();
        System.out.println(anyThread);
        Thread anotherThread = new Thread("ne tot potok");
        anotherThread.start();

    }

}
