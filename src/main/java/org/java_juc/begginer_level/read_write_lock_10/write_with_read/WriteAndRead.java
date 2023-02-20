package org.java_juc.begginer_level.read_write_lock_10.write_with_read;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * When write that could read
 */
public class WriteAndRead {
    public static void main(String[] args) {
        // Create object
        final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();


        // write
        writeLock.lock();
        System.out.println("Coucou, I'm writing");
        // read
        readLock.lock();
        System.out.println("Coucou, I'm reading");

        // unlock write
        writeLock.unlock();

        // unlock read
        readLock.unlock();

    }
}
/** result
 * Coucou, I'm wring
 * Coucou, I'm reading
 */
