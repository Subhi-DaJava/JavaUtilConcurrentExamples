package org.java_juc.begginer_level.blocking_queue_11.offer_poll_time;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * FIFO: First in First Out
 */
public class BlockingQueueOfferPollTime {
    public static void main(String[] args) throws InterruptedException {
        // Create BlockingQueue object implementing ArrayBlockingQueue
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);

        // add element
        System.out.println(blockingQueue.offer("elt_1")); // true
        System.out.println(blockingQueue.offer("elt_2", 3L, TimeUnit.SECONDS)); // true

        System.out.println(blockingQueue.offer("elt_3", 2L, TimeUnit.SECONDS)); // false

        TimeUnit.SECONDS.sleep(2);
        // remove element
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS)); // elt_1
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS)); // elt_2
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS)); // null


    }
/*
true
true
false
elt_1
elt_2
null

 */
}
