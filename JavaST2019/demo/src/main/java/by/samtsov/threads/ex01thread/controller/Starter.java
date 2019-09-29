package by.samtsov.threads.ex01thread.controller;

import by.samtsov.threads.ex01thread.service.AnyThread;


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
