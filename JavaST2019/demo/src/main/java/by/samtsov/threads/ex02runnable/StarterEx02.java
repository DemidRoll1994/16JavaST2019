package by.samtsov.threads.ex02runnable;

public class StarterEx02 {
    public static void main(String[] args) {
        RunnablePerson alice = new RunnablePerson("Alice");
        RunnablePerson bob = new RunnablePerson("Bob");

        Thread aliceThread = new Thread(alice);
        Thread bobThread= new Thread(bob);
        aliceThread.setPriority(1);
        bobThread.setPriority(10);

        aliceThread.start();
        bobThread.start();
    }
}
