package by.samtsov.threads.ex05;

import by.samtsov.threads.ex02.RunnablePerson;

import static java.lang.Thread.interrupted;

public class ThreadToInterrupt extends Thread {


    ThreadToInterrupt(){
    }

    public void run() {

        System.out.printf("Поток %s начал работу... \n", Thread.currentThread().getName());
        int counter = 1; // счетчик циклов
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Цикл inter" + counter++);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                interrupt();
                System.out.println("Поток inter прерван");
            }
        }
        System.out.printf("Поток %s завершил работу... \n", Thread.currentThread().getName());
    }
}