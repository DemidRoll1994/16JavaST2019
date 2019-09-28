package by.samtsov.threads.ex12;

public class StarterEx12 {
    public static void main(String[] args) {

        Store store = new Store();
        Thread producer = new Producer("MAZ", store, 7);
        producer.start();
        Thread consumer1= new Consumer("Iran", store, 3);
        consumer1.start();
        Thread consumer2= new Consumer("Uran", store, 2);
        consumer2.start();
        Thread consumer3= new Consumer("Magelan", store, 2);
        consumer3.start();

        try{
            consumer1.join();
            consumer2.join();
            consumer3.join();
            producer.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();


    }
}
