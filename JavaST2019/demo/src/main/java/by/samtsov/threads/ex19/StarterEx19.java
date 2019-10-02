package by.samtsov.threads.ex19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StarterEx19 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);

        List<Future<Number>> future = new ArrayList<>();
        for (int i = 0; i <5; i++) {
            future.add(es.submit(new CalcCallable()));
        }

        es.shutdown();
        //на этом этапе мы уже имеем все данные с потоков
        try {
            for (int i = 0; i <5; i++) {
                System.out.println(future.get(i).get());
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
