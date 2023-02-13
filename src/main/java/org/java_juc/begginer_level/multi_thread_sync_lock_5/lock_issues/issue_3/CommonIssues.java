package org.java_juc.begginer_level.multi_thread_sync_lock_5.lock_issues.issue_3;

import java.util.concurrent.TimeUnit;

/**
 * Synchronized issues : deux synchronized méthodes et une simple méthode
 * Créer deux threads pour démonter les executions, avec un objet de Phone ou deux objets de Phone
 * <p>
 * Ressources partagées Phone class
 * <p>
 * 8 différentes situations :
 * <p>
 * 1. cas général, imprimer sendMessage ou sendEmail ?
 * résultat :
 * coucou-----sendTexto
 * coucou-----Email
 * <p>
 * 2. sleep 3 secondes dans sendMessage(), imprimer sendMessage ou sendEmail ?
 * résultat :
 * coucou-----sendTexto
 * coucou-----Email
 * <p>
 * 3. sleep 3 secondes dans sendMessage(), et appeler sayHello dans le deuxième thread
 * résultat:
 * hi-----Bonjour
 * coucou-----sendTexto
 * <p>
 * 4.
 */


class Phone {
    public synchronized void sendMessage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("coucou-----sendTexto");
    }

    public synchronized void sendEmail() {
        System.out.println("coucou-----Email");
    }

    public void sayHello() {
        System.out.println("hi-----Bonjour");
    }
}

public class CommonIssues {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();

        //new Thread(phone::sendMessage, "Thread_1").start();
        new Thread(() -> {
            try {
                phone.sendMessage();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread_1").start();

        Thread.sleep(100);

        new Thread(phone::sayHello, "Thread_2").start(); //  new Thread(()-> { phone.sayHello();}, "Thread_2").start();
    }
}
