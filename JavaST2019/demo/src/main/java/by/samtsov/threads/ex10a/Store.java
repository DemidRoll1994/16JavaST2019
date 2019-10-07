package by.samtsov.threads.ex10a;

class Store {
    int maxVolume=3;
    private int product = 0;

    public synchronized void put() {
        while (product >= maxVolume) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product++;
        System.out.println("Производитель " + Thread.currentThread().getName()
                + " добавил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notifyAll(); // будим всех покупателей
    }

    public synchronized void get() {
        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product--;
        System.out.println("Покупатель " + Thread.currentThread().getName()
                + " купил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
}
