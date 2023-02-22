package org.java_juc.begginer_level.completable_future_14;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// Synchronous et Asynchronous
public class CompletableFutureDemo {
    public static void main(String[] args) {

        // Sync
        CompletableFuture<Void> completableFutureSync = CompletableFuture.runAsync(()-> {
            System.out.println(Thread.currentThread().getName() + " : completableFutureSync");
        });
        try {
            completableFutureSync.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // ASync
       CompletableFuture<String> completableFutureAsync = CompletableFuture.supplyAsync(()-> {
           System.out.println(Thread.currentThread().getName() + " : completableFutureASync");
           return "Full number" + 147;
       });

        try {
            completableFutureAsync.whenComplete((t, u) -> {
                System.out.println("t: " + t);
                System.out.println("u: " + u);
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // ASync
        CompletableFuture<Integer> completableFutureAsyncException = CompletableFuture.supplyAsync(()-> {
            System.out.println(Thread.currentThread().getName() + " : completableFutureASyncException");
            int divisionByZero = 10/0;
            return 147;
        });

        try {
            completableFutureAsyncException.whenComplete((t, u) -> {
                System.out.println("t: " + t);
                System.out.println("u: " + u);
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
/** Résultat possible
 ForkJoinPool.commonPool-worker-1 : completableFutureSync
 ForkJoinPool.commonPool-worker-1 : completableFutureASync
 t: Full number147
 u: null
 ForkJoinPool.commonPool-worker-1 : completableFutureASyncException
 t: null
 u: java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
 Exception in thread "main" java.lang.RuntimeException: java.util.concurrent.ExecutionException: java.lang.ArithmeticException: / by zero
 at org.java_juc.begginer_level.completable_future_14.CompletableFutureDemo.main(CompletableFutureDemo.java:48)
 Caused by: java.util.concurrent.ExecutionException: java.lang.ArithmeticException: / by zero
 at java.util.concurrent.CompletableFuture.reportGet(CompletableFuture.java:357)
 at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1908)
 at org.java_juc.begginer_level.completable_future_14.CompletableFutureDemo.main(CompletableFutureDemo.java:46)
 Caused by: java.lang.ArithmeticException: / by zero
 at org.java_juc.begginer_level.completable_future_14.CompletableFutureDemo.lambda$main$3(CompletableFutureDemo.java:38)
 at java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1604)
 at java.util.concurrent.CompletableFuture$AsyncSupply.exec(CompletableFuture.java:1596)
 at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
 at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
 at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
 at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:175)

 */