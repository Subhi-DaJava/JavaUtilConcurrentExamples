package org.java_juc.begginer_level.multi_thread_sync_lock_5.dead_lock;

import java.util.concurrent.TimeUnit;

/**
 * Deux ou plus de Threads attendent (waiting) les ressources, mais ils n'arrivent pas à avoir les ressources, à cause de lock
 */
public class DeadLockDemo {

    static final Object objA = new Object();
    static final Object objB = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (objA) {
                System.out.println(Thread.currentThread().getName() + " détient lock objA, mais veut avoir lock objB");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (objB) {
                    System.out.println(Thread.currentThread().getName() + " détient lock objB");
                }
            }
        }, "Thread_objA").start();


        new Thread(() -> {
            synchronized (objB) {
                System.out.println(Thread.currentThread().getName() + " détient lock objB, mais veut avoir lock objA");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (objA) {
                    System.out.println(Thread.currentThread().getName() + " détient lock objA");
                }
            }
        }, "Thread_objB").start();
    }
}
