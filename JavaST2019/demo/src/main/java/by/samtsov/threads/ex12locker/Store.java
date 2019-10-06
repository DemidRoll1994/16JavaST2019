package by.samtsov.threads.ex12locker;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Store{
    private int product = 0;

    ReentrantLock locker = new ReentrantLock(); // создаем заглушку
    Condition condition = locker.newCondition(); // получаем условие, связанное
    public void put() {
        locker.lock(); // устанавливаем блокировку
        while (product >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                locker.unlock();
            }
        }
        product++;
        System.out.println("Производитель добавил 1 товар");
        System.out.println("Товаров на складе: " + product);
        condition.signalAll();
        locker.unlock();
    }

    public void get() {
        locker.lock();
        while (product < 1) {
            try {
                //wait();
                condition.await();
            } catch (InterruptedException e) {
                locker.unlock();
            }
        }
        product--;
        System.out.println("Покупатель купил 1 товар");
        System.out.println("Товаров на складе: " + product);
        condition.signal();
        locker.unlock();
    }
}
