package by.samtsov.threads.ex09;

public class StarterEx09 {
    public static void main(String[] args) {

        Store store = new Store();
        new Producer(store).start();
        new Consumer(store).start();

    }
}
