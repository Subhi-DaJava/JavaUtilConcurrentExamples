package org.java_juc.begginer_level.thread_pool_12.thread_pool_practice;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * Personnaliser ThreadPoolExecutor avec 7 params
 */
public class CustomThreadPoolExecutor {
    public static void main(String[] args) throws InterruptedException {

        // create custom ThreadPoolExecutor
        ExecutorService threadPool =
                new ThreadPoolExecutor(
                        3,
                        6,
                        2L,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(3),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy() // or others
                );

        int startTime = 0;
        int executeTime;
        int endTime;
        try {
            startTime = LocalDateTime.now().getNano();

            for (int i = 1; i <= 10; i++) {

                // exécuter
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " est train de encaisser...");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // put the thread dans le pool
            threadPool.shutdown(); // be careful shutdownNow()!
            endTime = LocalDateTime.now().getNano();
        }

        TimeUnit.SECONDS.sleep(2);
        executeTime = endTime - startTime;

        System.out.println(executeTime);
    }
}
/*
pool-1-thread-1 est train de encaisser...
pool-1-thread-2 est train de encaisser...
pool-1-thread-2 est train de encaisser...
pool-1-thread-2 est train de encaisser...
pool-1-thread-2 est train de encaisser...
pool-1-thread-3 est train de encaisser...
pool-1-thread-4 est train de encaisser...
pool-1-thread-5 est train de encaisser...
pool-1-thread-6 est train de encaisser...
java.util.concurrent.RejectedExecutionException: Task org.java_juc.begginer_level.thread_pool_12
	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
	at org.java_juc.begginer_level.thread_pool_12.thread_pool_practice.CustomThreadPoolExecutor.main(CustomThreadPoolExecutor.java:33)
18000000

 */