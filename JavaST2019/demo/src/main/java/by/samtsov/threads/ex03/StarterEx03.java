package by.samtsov.threads.ex03;

import org.w3c.dom.ls.LSOutput;

public class StarterEx03 {
    public static void main(String[] args) {
        System.out.println("Application Started");
        PriorityThread min = new PriorityThread("Min");
        PriorityThread max = new PriorityThread("Maximum");
        PriorityThread norm = new PriorityThread("Norma");
        min.setPriority(Thread.MIN_PRIORITY); // 1
        max.setPriority(Thread.MAX_PRIORITY); // 10
        norm.setPriority(Thread.NORM_PRIORITY); // 5
        min.start();
        norm.start();
        max.start();


        try {
            max.join();
            norm.join(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Application finished");
    }
}
