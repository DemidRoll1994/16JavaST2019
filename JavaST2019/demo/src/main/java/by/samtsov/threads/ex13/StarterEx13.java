package by.samtsov.threads.ex13;

import java.util.concurrent.Semaphore;

public class StarterEx13 {

    public static void main(String[] args) {

        CommonResource commonResource= new CommonResource();
        Semaphore sem = new Semaphore(2); // 1 разрешение

        for (int i = 1; i < 6; i++){

            new Thread(new CountThread(commonResource, sem, "CountThread "+i)).start();
        }
    }

}
