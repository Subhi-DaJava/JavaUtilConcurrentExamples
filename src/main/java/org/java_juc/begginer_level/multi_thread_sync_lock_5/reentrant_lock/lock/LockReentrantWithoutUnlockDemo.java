package org.java_juc.begginer_level.multi_thread_sync_lock_5.reentrant_lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrant lock: synchronized est invisible, lock est visible
 * Note: lock -> unlock !
 */
public class LockReentrantWithoutUnlockDemo {

    public static void main(String[] args) {
        // créer Lock
        Lock lock = new ReentrantLock();

        // créer Thread
        new Thread(() -> {
            try {
                // Lock : fermer
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " couche extérieure");
                try {
                    // Lock : fermer
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " couche milieu");
                    try {
                        // Lock : fermer
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + " couche intérieure");

                    } finally {
                        // unLock : ouvrir/release lock
                        //lock.unlock();
                    }

                } finally {
                    // unLock : ouvrir/release lock
                   // lock.unlock();
                }
            } finally {
                // unLock : ouvrir/release lock
                lock.unlock();
            }

        }, "Thread_1").start();

        new Thread(()-> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " , un autre thread"); // does not print this message, because no unlock before
            } finally {
                lock.unlock();
            }

        }, "Thread_2").start();
    }
}
