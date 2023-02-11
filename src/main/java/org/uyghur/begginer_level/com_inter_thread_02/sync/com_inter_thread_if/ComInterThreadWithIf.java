package org.uyghur.begginer_level.com_inter_thread_02.sync.com_inter_thread_if;

/**
 * interruptions et réveils intempestifs
 * <p>
 * Quatre Threads, une variable d'un entier initialisé 0, un Thread rajoute 1, l'autre enlève 1
 * Étape : vérifier la valeur de la variable(number), incrémente(minus) un ou décrémente un (add), et alerte un autre Thread (wait() et notifyAll())
 * Utiliser await et notifyAll des méthodes Object class
 */

class SharedResourceWithIf {
    private int number = 0;

    // incrément
    public synchronized void increment() throws InterruptedException {
        // vérification, add et alerte
        if (number != 0) {
            this.wait(); // deuxième fois pourrait lâcher
        }
        // add 1
        number++;
        System.out.println(Thread.currentThread().getName() + " -- " + number);
        // alerter autre Thread
        this.notifyAll();
    }

    // décrément
    public synchronized void decrement() throws InterruptedException {
        // vérification, add et alerte
        if (number != 1) {
            this.wait(); // deuxième fois pourrait lâcher
        }
        // minus 1
        number--;
        System.out.println(Thread.currentThread().getName() + " -- " + number);
        // alerter autre Thread
        this.notifyAll();
    }

    public int getNumber() {
        return number;
    }
}

public class ComInterThreadWithIf {
    // dernière étape : créer multiThread et employer les méthodes
    public static void main(String[] args) {
        SharedResourceWithIf sharedResourceWithIf = new SharedResourceWithIf();
        System.out.println("avant l'opération: " + sharedResourceWithIf.getNumber());

        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sharedResourceWithIf.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sharedResourceWithIf.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread2");
        thread2.start();

        Thread thread3 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sharedResourceWithIf.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread3");
        thread3.start();

        Thread thread4 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sharedResourceWithIf.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread4");
        thread4.start();
    }
    /*
    Résultat possible:
    avant l'opération: 0
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
thread1 -- 2
thread3 -- 3
thread2 -- 2
thread3 -- 3

     */
}
