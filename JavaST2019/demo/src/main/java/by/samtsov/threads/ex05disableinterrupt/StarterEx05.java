package by.samtsov.threads.ex05disableinterrupt;

public class StarterEx05 {
    public static void main(String[] args) {

        System.out.println("Главный поток начал работу...");
        ThreadToDisable myThread = new ThreadToDisable();
        Thread myT = new Thread(myThread, "name of ThreadToDisable");
        myT.start();
        ThreadToInterrupt threadToInterrupt = new ThreadToInterrupt();
        Thread threadToInterruptImpl = new Thread(threadToInterrupt, "name of " +
                "threadToInterrupt");
        threadToInterruptImpl.start();
        try{
            Thread.sleep(1100);

            //myThread.disable();
            threadToInterruptImpl.interrupt();
            Thread.sleep(1050);
        }
        catch(InterruptedException e){
            System.out.println(1);
            //threadToInterruptImpl.interrupt();
            System.out.println(2);
            System.out.println("Поток прерван");
        }
        System.out.println("Главный поток завершил работу...");






/*

/*
        try{
            Thread.sleep(1100);

            myThread.disable();
            Thread.sleep(1000);
            //threadToInterruptImpl.interrupt();

        }
        catch(InterruptedException e){
            //threadToInterruptImpl.interrupt();// необходимо только потому
            // что jvm не
            // дает явно завершить поток во время слипа
            System.out.println("Поток прерван");
        }
        System.out.println("Главный поток завершил работу...");
*/
    }
}
