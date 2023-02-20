package org.java_juc.begginer_level.blocking_queue_11.add_remove;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * FIFO : First in First out
 */
public class BlockingQueueAddRemove {
    public static void main(String[] args) throws InterruptedException {
        // Create BlockingQueue object implementing ArrayBlockingQueue
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);

        // add element
        System.out.println(blockingQueue.add("elt1")); // true
        System.out.println(blockingQueue.add("elt2")); // true

        System.out.println(blockingQueue.element()); // elt1

        //System.out.println(blockingQueue.add("elt3")); // throw Queue full Exception

        try {
            System.out.println(blockingQueue.add("elt3"));
        } catch (Exception e) {
            // e.printStackTrace();
            // throw new RuntimeException("Array is full!!");
            System.out.println("blocking queue is full");
        }

        TimeUnit.SECONDS.sleep(2);

        // remove element
        System.out.println(blockingQueue.remove()); // elt1
        System.out.println(blockingQueue.remove()); // elt2

        System.out.println(blockingQueue.remove()); // throw noSuchElementException


    }

    /*
    true
    true
    elt1
Exception in thread "main" java.lang.IllegalStateException: Queue full
	at java.util.AbstractQueue.add(AbstractQueue.java:98)
	at java.util.concurrent.ArrayBlockingQueue.add(ArrayBlockingQueue.java:312)
	at org.java_juc.begginer_level.blocking_queue_11.add_remove.BlockingQueueAddRemove.main(BlockingQueueAddRemove.java:16)

     */

    /*
    true
   true
   elt1
   blocking queue is full
   elt1
   elt1
Exception in thread "main" java.util.NoSuchElementException
	at java.util.AbstractQueue.remove(AbstractQueue.java:117)
	at org.java_juc.begginer_level.blocking_queue_11.add_remove.BlockingQueueAddRemove.main(BlockingQueueAddRemove.java:31)

     */
}
