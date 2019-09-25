package by.samtsov.threads.ex04;

public class StarterEx04 {
    public static void main(String[] args) {
        SuperThread superThread = new SuperThread();
        superThread.start();

        try {
            superThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("finish application");
    }
}
