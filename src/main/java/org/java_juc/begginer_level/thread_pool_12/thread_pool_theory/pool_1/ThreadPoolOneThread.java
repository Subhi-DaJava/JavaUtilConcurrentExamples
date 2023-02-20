package org.java_juc.begginer_level.thread_pool_12.thread_pool_theory.pool_1;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Supermarket : 1 caisse, 10 consommateurs
 */
public class ThreadPoolOneThread {
    public static void main(String[] args) throws InterruptedException {

        // create thread pool
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

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
/* possible result:
pool-1-thread-1 est train de encaisser...
pool-1-thread-1 est train de encaisser...
pool-1-thread-1 est train de encaisser...
pool-1-thread-1 est train de encaisser...
pool-1-thread-1 est train de encaisser...
pool-1-thread-1 est train de encaisser...
pool-1-thread-1 est train de encaisser...
pool-1-thread-1 est train de encaisser...
pool-1-thread-1 est train de encaisser...
pool-1-thread-1 est train de encaisser...
16000000
 */
// 15000000 nThread, 16000000 oneThread,
