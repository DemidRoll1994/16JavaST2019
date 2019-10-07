package by.samtsov.threads.ex05disableinterrupt;

public class ThreadToInterrupt extends Thread {


    ThreadToInterrupt(){
    }

    public void run() {

        System.out.printf("Поток %s начал работу... \n", Thread.currentThread().getName());
        int counter = 1; // счетчик циклов
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Цикл inter" + counter++);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                interrupt();
                System.out.println("Поток inter прерван");
            }
        }
        System.out.printf("Поток %s завершил работу... \n", Thread.currentThread().getName());
    }
}