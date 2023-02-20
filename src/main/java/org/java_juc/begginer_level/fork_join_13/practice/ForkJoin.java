package org.java_juc.begginer_level.fork_join_13.practice;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * Calculer la somme des entiers de un à 100, méthode : additionner les entiers, si la différence de la fin et le début est moins que 11,
 * sinon continuer à diviser les entiers en deux
 * left side : 1-50 -> {1-25} ->[ {1-12} et [{13-25}->{13-14} et {15-25} ] et {26-50}...
 * right side : 51-100 -> {51-75} et {76-100}...
 */
class GaussSum extends RecursiveTask<Integer> {
    // division si difference > 11
    private final static Integer DIFFERENCE = 11;

    private int firstNumber; // entier début
    private int lastNumber; // dernier entier
    private int result;

    public GaussSum(int firstNumber, int lastNumber) {
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
    }

    // fork et join
    @Override
    protected Integer compute() {
        // vérifier la DIFFERENCE et additionner les
        if (lastNumber - firstNumber <= DIFFERENCE) {
            for (int i = firstNumber; i <= lastNumber; i++) {
                result += i;
            }
        } else { // diviser
            int middleNumber = (lastNumber + firstNumber) / 2;
            // left side
            GaussSum leftSide = new GaussSum(firstNumber, middleNumber);
            // right side
            GaussSum rightSide = new GaussSum(middleNumber + 1, lastNumber);
            // implementer fork méthode
            leftSide.fork();
            rightSide.fork();
            // fusionner Résultat
            result = leftSide.join() + rightSide.join();
        }

        return result;
    }
}

public class ForkJoin {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int startTime = 0;
        int executeTime;
        int endTime;
        // créer GaussSum object
        GaussSum gaussSum = new GaussSum(1, 100);
        // créer ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        final ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(gaussSum); // submit task

        startTime = LocalDateTime.now().getNano();

        endTime = LocalDateTime.now().getNano();
        // Résultat de Fork et Join
        System.out.println(forkJoinTask.get());

        TimeUnit.SECONDS.sleep(2);


        executeTime = endTime - startTime;

        System.out.println("Execute time: " + executeTime);

        forkJoinPool.shutdown();
    }
}
/*
5050
Execute time: 16000000

 */


