package org.java_juc.begginer_level.juc_countdownlatch_07.theory;

import java.util.concurrent.CountDownLatch;

/**
 * Dès qu'il n'y personne dans la salle de réunion, le gardien ferme la porte
 */
public class CountDownLatchDemo {
    // 10 personnes quittent d'une salle de réunion, puis le gardien la ferme à la clé
    public static void main(String[] args) throws InterruptedException {
        // créer CountDownLatch object, initialiser
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " personne quitte la salle");
                // décrémenter
                countDownLatch.countDown();

            }, String.valueOf(i)).start();
        }
        // attendre jusqu'à personne dans la pièce, countDownLatch == 0
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + " gardien ferme la salle.");
    }
}
/* résulta possible
1 personne quitte la salle
2 personne quitte la salle
3 personne quitte la salle
6 personne quitte la salle
4 personne quitte la salle
7 personne quitte la salle
5 personne quitte la salle
10 personne quitte la salle
9 personne quitte la salle
8 personne quitte la salle
main gardien ferme la salle.
 */