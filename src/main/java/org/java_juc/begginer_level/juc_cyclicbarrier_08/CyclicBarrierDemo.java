package org.java_juc.begginer_level.juc_cyclicbarrier_08;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Bus ne part pas avant que 10 personnes n'arrivent.
 */
public class CyclicBarrierDemo {
    // définir 10 personnes
    private static final int NUMBER = 10;

    public static void main(String[] args) {
        // créer l'objet de CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () ->
                System.out.println("10 personnes sont arrivées, on peut partir"));

        // attendre jusqu'à 10 personnes
        for (int i = 1; i <= 10; i++) { // if i=9
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " est arrivé");

                    cyclicBarrier.await(); // 10 personnes, ok

                } catch (InterruptedException e) {
                    /*e.printStackTrace();*/
                    throw new RuntimeException();

                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }, "Personne " + i).start();
        }
    }
}
/* résultat possible
Personne 1 est arrivé
Personne 3 est arrivé
Personne 2 est arrivé
Personne 4 est arrivé
Personne 5 est arrivé
Personne 6 est arrivé
Personne 8 est arrivé
Personne 7 est arrivé
Personne 9 est arrivé
Personne 10 est arrivé
10 personnes sont arrivées, on peut partir

 */
