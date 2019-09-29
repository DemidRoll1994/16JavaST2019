package by.samtsov.threads.ex01thread.service;

/**
 * threads can be started only one time.
 * second time you can use only run method, but in the parent (main method)
 * thread
 */
public class AnyThread extends Thread{
    // вызывать нужно метод start, а не run.

    public void run(){
        System.out.println();
    }
}
