package org.java_juc.begginer_level.multi_thread_sync_lock_5.fair_unfair_sync_lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Vendre les tickets
 * <p>
 * Lock nonFairLock1 = new ReentrantLock(); // A non-fair lock (Default is non-fair), l'efficace
 * Lock nonFairLock2 = new ReentrantLock(false); // A non-fair lock, l'efficace
 * Lock fairLock2 = new ReentrantLock(true); // A fair lock, moins efficace
 * <p>
 * You can specify the fairness of a lock when creating the ReentrantLock class.
 * <p>
 * In a fair lock, threads acquire the lock in the order they request it.
 * <p>
 * In a non-fair lock, jumping ahead is allowed.
 * <p>
 * When requesting the same lock later, a later thread can get the lock before the waiting threads.
 * <p>
 * The tryLock() method of the ReentrantLock class always uses a non-fair lock.
 */
class FairLockTicket {
    private int number = 50;
    private final ReentrantLock lock = new ReentrantLock(true);

    public void saleTicket() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + " sales the ticket order " + (number--) + ", and " + number + " tickets left");
            }
        } finally {
            lock.unlock();
        }
    }
}

public class FairSyncLockSaleTicket {
    public static void main(String[] args) {
        FairLockTicket ticket = new FairLockTicket();

        new Thread(() -> {
            int round = 0;
            for (int i = 0; i < 60; i++) {
                ticket.saleTicket();
                round++;
            }
            System.out.println("wow, " + Thread.currentThread().getName() + " you have worked " + round + " times");
        }, "T1").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++)
                ticket.saleTicket();
        }, "T2").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++)
                ticket.saleTicket();
        }, "T3").start();
    }
}