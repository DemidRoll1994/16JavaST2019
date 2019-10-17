package by.samtsov.threads.ex12locker;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Store {
    ReentrantLock locker = new ReentrantLock(); // создаем заглушку
    Condition condition = locker.newCondition(); // получаем условие, связанное
    String itog;
    private int product = 0;

    public void put() {
        itog += "\n" + Thread.currentThread().getName() + " зашел в put";
        locker.lock(); // устанавливаем блокировку
        itog += "\n" + Thread.currentThread().getName() + " заблокировал " +
                "put";
        while (product >= 3) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                locker.unlock();
            }
        }
        product += 7;
       /* try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        itog += "\n" + Thread.currentThread().getName()
                + " добавил 7 товаров из " + product;
        itog += "\n" + "-----------------------------------";
        condition.signalAll();
        locker.unlock();
        print();
        itog="";
    }

    public void get() {
        itog += "\n" + Thread.currentThread().getName() + " зашел в get";
        locker.lock(); // устанавливаем блокировку
        itog += "\n" + Thread.currentThread().getName() + " заблокировал " +
                "get";
        while (product < 1) {
            try {
                //wait();
                condition.await();
            } catch (InterruptedException e) {
                locker.unlock();
            }
        }
        product--;
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        itog += "\n" + Thread.currentThread().getName() + " купил 1 товар " +
                "из " + product;
        itog += "\n" + "--------------------------------";
        condition.signal();
        locker.unlock();
    }

    public void print() {
        System.out.println(itog);
    }
}
