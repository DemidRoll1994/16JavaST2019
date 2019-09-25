package by.samtsov.threads.ex06;

public class CountThread implements Runnable {

        CommonResource res;
        CountThread(CommonResource res){
            this.res=res;
        }
        public void run(){

            unSynchronizedMethod();
            SynchronizedMethod();
        }

    private void SynchronizedMethod() {

        synchronized (res) {
            res.x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private void unSynchronizedMethod() {

            res.x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }

    }
}
