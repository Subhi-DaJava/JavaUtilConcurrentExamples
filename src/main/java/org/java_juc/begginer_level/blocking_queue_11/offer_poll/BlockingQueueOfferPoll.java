package org.java_juc.begginer_level.blocking_queue_11.offer_poll;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * FIFO: First in First Out
 */
public class BlockingQueueOfferPoll {
    public static void main(String[] args) throws InterruptedException {
        // Create BlockingQueue object implementing ArrayBlockingQueue
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);

        // add element
        System.out.println(blockingQueue.offer("elt_1")); // true
        System.out.println(blockingQueue.offer("elt_2")); // true

        System.out.println(blockingQueue.peek()); // elt_1

        System.out.println(blockingQueue.offer("elt_3")); // false

        TimeUnit.SECONDS.sleep(2);

        // remove element
        System.out.println(blockingQueue.poll()); // elt_1
        System.out.println(blockingQueue.poll()); // elt_2

        System.out.println(blockingQueue.poll()); // null

    }
    /*
true
true
elt_1
false
elt_1
elt_2
null
 */
}
