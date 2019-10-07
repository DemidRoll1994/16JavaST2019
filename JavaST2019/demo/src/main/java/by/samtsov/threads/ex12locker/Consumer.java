package by.samtsov.threads.ex12locker;

class Consumer extends Thread{//implements Runnable {

    private Store store;
    private int count1;
    Consumer(String name, Store store, int count) {
        super.setName(name);
        this.store = store;
        count1=count;
    }

    public void run() {
        for (int i = 0; i < count1; i++) {
            store.get();
        }
    }
}
