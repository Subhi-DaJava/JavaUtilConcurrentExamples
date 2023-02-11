package org.uyghur.begginer_level.synchronized_lock_01.sync.with;
/**
 * Vendre les tickets
 */
class Ticket {
    private int number = 50;

    public synchronized void saleTicket() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + " sales the ticket order " + (number--) + ", and " + number + " tickets left");
        }
    }
}
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int round = 0;
                for (int i = 0; i < 60; i++) {
                    ticket.saleTicket();
                    round++;
                }
                System.out.println("wow, " + Thread.currentThread().getName() + " you have worked " + round + " times");
            }

        }, "T1").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++)
                ticket.saleTicket();
        }, "T2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 60; i++)
                    ticket.saleTicket();
            }
        }, "T3").start();
    }
}
