package org.java_juc.begginer_level.multi_thread_sync_lock_5.reentrant_lock.sync;

/**
 * Reentrant lock: synchronized est invisible, lock est visible
 */
public class SynchronizedLock {
    // récursive
    public synchronized void add() { // Exception in thread "main" java.lang.StackOverflowError
        add();
    }

    public void add(int n) {
        add(n);
    }
    public static void main(String[] args) {

        new SynchronizedLock().add();

        // new SynchronizedLock().add(5);

        Object obj = new Object();

        new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " couche extérieure");

                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + " couche milieu");

                    synchronized (obj) {
                        System.out.println(Thread.currentThread().getName() + " couche intérieure");
                    }
                }
            }
        }, "Thread_1").start();
    }
}
