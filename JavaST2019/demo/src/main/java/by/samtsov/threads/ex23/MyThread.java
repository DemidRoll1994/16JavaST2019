package by.samtsov.threads.ex23;

public class MyThread implements Runnable {
    SafeCounterWithoutLock scwl;
    public MyThread(SafeCounterWithoutLock scwl) {
        this.scwl = scwl;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            //scwl.increment();
            System.out.println(scwl);
        }
    }
}
