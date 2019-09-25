package by.samtsov.threads.ex05;

public class ThreadToDisable implements Runnable {

    private boolean isActive;

    ThreadToDisable() {
        isActive = true;
    }

    void disable() {
        isActive = false;
    }

    public void run() {

        System.out.printf("Поток %s начал работу... \n", Thread.currentThread().getName());
        int counter = 1; // счетчик циклов
        while (isActive) {
            System.out.println("Цикл dis" + counter++);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Поток прерван");
            }
        }
        System.out.printf("Поток %s завершил работу... \n", Thread.currentThread().getName());
    }
}
