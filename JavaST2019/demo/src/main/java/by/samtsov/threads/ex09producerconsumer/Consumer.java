package by.samtsov.threads.ex09producerconsumer;
class Consumer extends Thread {
    Store store; // объект склада, с которого покупатель будет брать товар
    int product = 0; // текущее количество товаров со склада
    final int N = 10; // максимально допустимое число
    Consumer(Store store) {
        this.store = store;
    }

    public void run() {
       // try {
            while (product < N) {// пока количество товаров не будет равно 5
                store.get();
               // product = product + ; // берем по одному товару со склада
                System.out.println("Потребитель купил " + product + " товар(ов)");
                //sleep(1000);
            }
        //} catch (InterruptedException e) {
        //    System.out.println("поток потребителя прерван");
        //}
    }
}

