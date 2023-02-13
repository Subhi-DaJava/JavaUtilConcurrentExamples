package org.java_juc.begginer_level.synchronized_lock_01.sync.without;
/**
 * Vendre les tickets
 */
class Ticket {
    private int number = 50;

    public void saleTicket() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + " sales the ticket order " + (number--) + ", and " + number + " tickets left");
        }
    }
}
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticketSansSync = new Ticket();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 60; i++)
                    ticketSansSync.saleTicket();
            }
        }, "T1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 60; i++)
                    ticketSansSync.saleTicket();
            }
        }, "T2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 60; i++)
                    ticketSansSync.saleTicket();
            }
        }, "T3").start();
    }
}
