package org.uyghur.begginer_level.com_inter_thread_02.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * interruptions et réveils intempestifs, avec While Loop
 * <p>
 * Quatre Threads, une variable d'un entier initialisé 0, un Thread rajoute 1, l'autre enlève 1
 * Étape : vérifier la valeur de la variable(number), incrémente(minus) un ou décrémente un (add), et alerte un autre Thread (wait() et notifyAll())
 * Utiliser await et notifyAll des méthodes Object class
 */

class SharedResource {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // incrément
    public void increment() throws InterruptedException {
        // vérification, add et alerte
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            // add 1
            number++;
            System.out.println(Thread.currentThread().getName() + " -- " + number);
            // alerter autre Thread
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    // décrément
    public void decrement() throws InterruptedException {
        // vérification, add et alerte
        lock.lock();
        try {
            while (number != 1) {
                condition.await();
            }
            // minus 1
            number--;
            System.out.println(Thread.currentThread().getName() + " -- " + number);
            // alerter autre Thread
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int getNumber() {
        return number;
    }
}
public class LockInterface {
    // dernière étape : créer multiThread et employer les méthodes
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        System.out.println("avant l'opération: " + sharedResource.getNumber());

        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sharedResource.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sharedResource.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread2");
        thread2.start();

        Thread thread3 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sharedResource.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread3");
        thread3.start();

        Thread thread4 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sharedResource.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread4");
        thread4.start();
    }
    /*
thread1 -- 1
thread2 -- 0
thread1 -- 1
thread2 -- 0
thread1 -- 1
thread2 -- 0
thread1 -- 1
thread2 -- 0
thread1 -- 1
thread2 -- 0
thread1 -- 1
thread2 -- 0
thread1 -- 1
thread2 -- 0
thread1 -- 1
thread2 -- 0
thread1 -- 1
thread2 -- 0
thread1 -- 1
thread2 -- 0
thread3 -- 1
thread4 -- 0
thread3 -- 1
thread4 -- 0
thread3 -- 1
thread4 -- 0
thread3 -- 1
thread4 -- 0
thread3 -- 1
thread4 -- 0
thread3 -- 1
thread4 -- 0
thread3 -- 1
thread4 -- 0
thread3 -- 1
thread4 -- 0
thread3 -- 1
thread4 -- 0
thread3 -- 1
thread4 -- 0
     */
}
