package by.samtsov.threads.ex12locker;

import java.util.concurrent.TimeUnit;

class Producer extends Thread{

    private Store store;
    private int count;
    Producer(String name, Store store,int count) {
        super.setName(name);
        this.store = store;
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            store.put();

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

