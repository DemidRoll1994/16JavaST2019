package by.samtsov.threads.ex10;

public class StarterEx10 {
    public static void main(String[] args) {

        Store store = new Store();
        new Producer(store).start();
        new Consumer(store).start();

    }
}
