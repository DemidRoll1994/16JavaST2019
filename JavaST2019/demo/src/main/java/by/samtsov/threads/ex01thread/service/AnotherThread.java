package by.samtsov.threads.ex01thread.service;

public class AnotherThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread());
        System.out.println(this);
        super.run();
    }
}
