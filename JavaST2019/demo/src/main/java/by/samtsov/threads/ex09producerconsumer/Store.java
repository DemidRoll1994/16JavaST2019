package by.samtsov.threads.ex09producerconsumer;

class Store {
    int counter = 0; // счетчик товаров
    final int N = 5; // максимально допустимое число

    // синхронизированный метод для производителей
    void put() {

            counter++; // кладем товар
            System.out.println ("склад имеет " + counter + " товар(ов)");
    }
    // метод для покупателей
     void get() {

            counter--; //берем товар
         System.out.println ("склад имеет " + counter + " товар(ов)");
    }
}
