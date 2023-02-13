package org.java_juc.begginer_level.multi_thread_sync_lock_5.lock_issues.issue_6;

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
 * 3. sleep 3 secondes dans sendMessage(), et appeler sayHello dans le deuxième thread, imprimer sendMessage ou sendEmail ?
 * résultat:
 * hi-----Bonjour
 * coucou-----sendTexto
 * <p>
 * 4. deux objets de Phone, imprimer sendMessage ou sendEmail ?
 * résultat :
 * coucou-----Email
 * coucou-----sendTexto
 * <p>
 * 5. deux static synchronized méthodes, un objet de Phone, imprimer sendMessage ou sendEmail ?
 * résultat :
 * coucou-----sendTexto
 * coucou-----Email
 * <p>
 * 6. deux static synchronized méthodes, deux objets de Phone, imprimer sendMessage ou sendEmail ?
 * * résultat :
 * * coucou-----sendTexto
 * * coucou-----Email
 */


class Phone {
    public static synchronized void sendMessage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("coucou-----sendTexto");
    }

    public static synchronized void sendEmail() {
        System.out.println("coucou-----Email");
    }

    public void sayHello() {
        System.out.println("hi-----Bonjour");
    }
}

public class CommonIssues {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phoneB = new Phone();

        //new Thread(phone::sendMessage, "Thread_1").start();
        new Thread(() -> {
            try {
                phone.sendMessage();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread_1").start();

        Thread.sleep(100);

        //new Thread(phoneB::sendEmail, "Thread_2").start();
        new Thread(() -> {
            phoneB.sendEmail();
        }, "Thread_2").start();
    }
}
