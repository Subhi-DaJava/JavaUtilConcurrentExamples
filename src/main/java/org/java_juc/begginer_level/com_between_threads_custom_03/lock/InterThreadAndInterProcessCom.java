package org.java_juc.begginer_level.com_between_threads_custom_03.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Trois threads(flag=1, 2 et 3), T1 imprime 3 fois, puis T2 imprime 5 fois, en dernier T3 imprime 10 fois pendant 10 itérations
 */
class ResourceShared {
    // attribut
    private int flag = 1; // Thread_1 -> 1, Thread_2 -> 2, Thread_3 -> 3

    // créer lock
    private Lock lock = new ReentrantLock();
    // créer conditions
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    // méthodes

    // imprimer 3 trois fois le message sur la ligne 33
    public void print3(int loop) { // loop = itération = 10
        // fermer thread
        lock.lock();
        try {
            while (flag != 1) {
                c1.await();
            }
            // print the message if flag == 1, assign flag to 2 and notify flag 2
            for (int i = 1; i < 4; i++) {
                System.out.println(Thread.currentThread().getName() + " -- print " + i + " time, in the loop of " + loop);
            }
            this.flag = 2;
            c2.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // ouvrir thread
            lock.unlock();
        }
    }

    public void print5(int loop) { // loop = itération = 10

        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }
            for (int i = 1; i < 6; i++) {
                System.out.println(Thread.currentThread().getName() + " -- print " + i + " time, in the loop of " + loop);
            }
            // print the message if flag == 2, assign flag to 3 and notify flag 3
            this.flag = 3;
            c3.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void print10(int loop) { // loop = itération = 10

        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }
            for (int i = 1; i < 11; i++) {
                System.out.println(Thread.currentThread().getName() + " -- print " + i + " time, in the loop of " + loop);
            }
            // print the message if flag == 3, assign flag to 1 and notify flag 1
            this.flag = 1;
            c1.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}

public class InterThreadAndInterProcessCom {
    public static void main(String[] args) {

        ResourceShared shared = new ResourceShared();

        new Thread(() -> {
            for (int i = 1; i < 11; i++) {
                shared.print3(i);
            }
        }, "Thread_1").start();

        new Thread(() -> {
            for (int i = 1; i < 11; i++) {
                shared.print5(i);
            }
        }, "Thread_2").start();

        new Thread(() -> {
            for (int i = 1; i < 11; i++) {
                shared.print10(i);
            }
        }, "Thread_3").start();
    }
}
/**
 * result: after the first iteration
 * <p>
 * Thread_1 -- print 1 time,  in the loop of 1
 * Thread_1 -- print 2 time,  in the loop of 1
 * Thread_1 -- print 3 time,  in the loop of 1
 * Thread_2 -- print 1 time,  in the loop of 1
 * Thread_2 -- print 2 time,  in the loop of 1
 * Thread_2 -- print 3 time,  in the loop of 1
 * Thread_2 -- print 4 time,  in the loop of 1
 * Thread_2 -- print 5 time,  in the loop of 1
 * Thread_3 -- print 1 time,  in the loop of 1
 * Thread_3 -- print 2 time,  in the loop of 1
 * Thread_3 -- print 3 time,  in the loop of 1
 * Thread_3 -- print 4 time,  in the loop of 1
 * Thread_3 -- print 5 time,  in the loop of 1
 * Thread_3 -- print 6 time,  in the loop of 1
 * Thread_3 -- print 7 time,  in the loop of 1
 * Thread_3 -- print 8 time,  in the loop of 1
 * Thread_3 -- print 9 time,  in the loop of 1
 * Thread_3 -- print 10 time, in the loop of 1
 */
