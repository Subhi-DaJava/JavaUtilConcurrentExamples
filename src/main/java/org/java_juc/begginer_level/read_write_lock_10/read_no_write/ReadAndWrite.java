package org.java_juc.begginer_level.read_write_lock_10.read_no_write;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * When read that no right to write
 */
public class ReadAndWrite {
    public static void main(String[] args) {
        // Create object
        final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

        // read
        readLock.lock();
        System.out.println("Coucou, I'm reading");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // unlock read
        //readLock.unlock();

        // write
        writeLock.lock();
        System.out.println("Coucou, I'm writing");

        // unlock write
        writeLock.unlock();

        // unlock read
        //readLock.unlock(); // never write if readLock does not unlock

    }
}
/**
 * Coucou, I'm reading
 *
 * and program is continuing ...
 */