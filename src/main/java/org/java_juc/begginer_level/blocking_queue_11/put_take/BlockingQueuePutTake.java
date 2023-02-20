package org.java_juc.begginer_level.blocking_queue_11.put_take;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * FIFO : First in First out
 */
public class BlockingQueuePutTake {
    public static void main(String[] args) throws InterruptedException {
        // Create BlockingQueue object implementing ArrayBlockingQueue
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);

        // add element
        blockingQueue.put("elt_1");
        blockingQueue.put("elt_2");


        // blockingQueue.put("elt_3"); // processing no ending

        TimeUnit.SECONDS.sleep(2);
        // remove element
        System.out.println(blockingQueue.take()); // elt_1
        System.out.println(blockingQueue.take()); // elt_2

        System.out.println(blockingQueue.take()); // process is continuing

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
