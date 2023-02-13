package org.java_juc.advandced_level.user_daemon_01;

import java.util.concurrent.TimeUnit;

/**
 * Savoir un Thread est User ou Daemon, la différence entre eux
 */

public class DaemonDemo {
    public static void main(String[] args) {
        final Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t is working, thread: " +
                    (Thread.currentThread().isDaemon() ? "Daemon" : "user"));
            while (true) {

            }
        }, "thread");
        // thread.setDaemon(true);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "\t .....is over buy, Monitor");

    }
}
