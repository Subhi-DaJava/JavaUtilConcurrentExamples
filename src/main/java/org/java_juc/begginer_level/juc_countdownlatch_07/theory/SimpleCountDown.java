package org.java_juc.begginer_level.juc_countdownlatch_07.theory;

public class SimpleCountDown {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(()->
                    System.out.println(Thread.currentThread().getName() + " person is left"), String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + " : Every one is left");
    }
}
