package org.java_juc.begginer_level.future_cf_executor_service_16.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Désavantages de Future : 4
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);

        final Future<List<Integer>> future = service.submit(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
            return Arrays.asList(1, 2, 3, 4); // if one line, return no need
        });

        final List<Integer> list = future.get();
        System.out.println(list);

        // second future
        final Future<List<Integer>> futureOther = service.submit(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
            return Arrays.asList(1, 2, 3, 4); // if one line, return no need
        });

        final List<Integer> listOther = futureOther.get();
        System.out.println(listOther);

        // third future
        final Future<List<Integer>> futureAnother = service.submit(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
            return Arrays.asList(1, 2, 3, 4); // if one line, return no need
        });

        final List<Integer> listAnother = futureAnother.get();
        System.out.println(listAnother);


        service.shutdown();

        System.out.println("Main thread: " + Thread.currentThread().getName());

        // System.out.println(list);
    }
}

/*
Thread: pool-1-thread-1
[1, 2, 3, 4]
Main thread: main

 */
