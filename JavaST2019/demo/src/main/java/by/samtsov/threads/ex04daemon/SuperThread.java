package by.samtsov.threads.ex04daemon;

import by.samtsov.threads.ex02runnable.RunnablePerson;

public class SuperThread extends Thread {
    @Override
    public void run() {
        System.out.println("start application");
        RunnablePerson alice = new RunnablePerson("Alice");
        RunnablePerson bob = new RunnablePerson("Bob");

        Thread aliceThread = new Thread(alice);
        Thread bobThread = new Thread(bob);

        aliceThread.setDaemon(true);

        aliceThread.start();
        bobThread.start();

        super.run();
        System.out.println("finish super");

    }


}
