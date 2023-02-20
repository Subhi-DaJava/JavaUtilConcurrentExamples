package org.java_juc.begginer_level.juc_semaphore_09;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Occuper un parking : 4 places dans un parking, 8 véhicules, chacun veut prendre une place
 */
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        int permission = 4;
        // étape 1
        Semaphore semaphore = new Semaphore(permission);

        //for (int j = 0; j < 2; j++) {

        // étape 2, 8 véhicules == 8 Threads
        for (int i = 1; i <= 8; i++) {

            new Thread(() -> {
                try {
                    // étape 3 : occuper la place, 4 places seulement, qui ?
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName() + " prend une place");

                    // chaque véhicule occupe cette place pendant quelques secondes
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    // véhicule quitte la place
                    System.out.println(Thread.currentThread().getName() + " >>>>>>> libère la place");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } finally {
                    // étape 4 : release la permission, libère une place, ligne 24
                    semaphore.release();
                }

            }, "Véhicule n°" + i).start();

        }

        // }
    }
}
/* résultat possible
Véhicule n°1 prend une place
Véhicule n°1 >>>>>>> libère la place
Véhicule n°2 prend une place
Véhicule n°2 >>>>>>> libère la place
Véhicule n°4 prend une place
Véhicule n°3 prend une place
Véhicule n°5 prend une place
Véhicule n°6 prend une place
Véhicule n°3 >>>>>>> libère la place
Véhicule n°7 prend une place
Véhicule n°5 >>>>>>> libère la place
Véhicule n°8 prend une place
Véhicule n°6 >>>>>>> libère la place
Véhicule n°4 >>>>>>> libère la place
Véhicule n°8 >>>>>>> libère la place
Véhicule n°7 >>>>>>> libère la place

un autre
Véhicule n°2 prend une place
Véhicule n°1 prend une place
Véhicule n°3 prend une place
Véhicule n°4 prend une place
Véhicule n°1 >>>>>>> libère la place
Véhicule n°5 prend une place
Véhicule n°5 >>>>>>> libère la place
Véhicule n°6 prend une place
Véhicule n°2 >>>>>>> libère la place
Véhicule n°7 prend une place
Véhicule n°4 >>>>>>> libère la place
Véhicule n°3 >>>>>>> libère la place
Véhicule n°8 prend une place
Véhicule n°7 >>>>>>> libère la place
Véhicule n°8 >>>>>>> libère la place
Véhicule n°6 >>>>>>> libère la place

 */
