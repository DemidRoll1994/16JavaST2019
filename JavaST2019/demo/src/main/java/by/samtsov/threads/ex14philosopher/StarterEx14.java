package by.samtsov.threads.ex14philosopher;

import java.util.concurrent.Semaphore;

public class StarterEx14 {
    static final int PLATES = 5;
    static final int NUMBER_OF_PHILOSOPHERS = 13;

    public static void main(String[] args) {

        Semaphore sem = new Semaphore(PLATES);
        for (int i = 1; i <= NUMBER_OF_PHILOSOPHERS; i++)
            new Philosopher(sem, i).start();
    }

}
