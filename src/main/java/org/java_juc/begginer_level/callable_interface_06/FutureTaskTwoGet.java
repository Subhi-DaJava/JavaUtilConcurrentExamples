package org.java_juc.begginer_level.callable_interface_06;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Créer deux Threads avec Runnable et Callable interfaces, comparer et implementer ces deux Threads
 *
 * FutureTask : définition et principes
 * 1. Un process se déroule sur un thread de l'axe, créer un autre thread et demander un autre travail, fournir un résultat
 * 2. Demander 4 personnes calculer : 1) 1+2+...+10 2) 11+12+...+50 3) 60+61 4) 70+71, pour la 2ᵉ personne,
 * FutureTask ouvrir un thread individuel et calculer total (fournir un résultat)
 * 3. Test exemple : faire les moins difficiles et puis fair les plus difficiles, fournir un résultat
 */
class ThreadRunnableC implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " RunnableA simple begins...");
        System.out.println("Hello from RunnableC");
    }
}

class TheadCallableC implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " futureTask simple is starting...");
        return "Hi from CallableC";
    }
    // code original
   /* @Override
    public Object call() throws Exception {
        return null;
    }*/
}
public class FutureTaskTwoGet {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // création de thread avec Runnable interface
        new Thread(new ThreadRunnableC(), "Thread_RunnableC").start();

        // création de thread avec FuturTask et Callable
        FutureTask<String> futureTaskC = new FutureTask<>(new TheadCallableC());

        // création e thread avec FuturTask avec lambda
        FutureTask<String> futureTaskLambdaC = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " futureTask lambdaC is starting...");
            return "lambdaC expression";
        });

        // créer un nouveau Thread
        new Thread(futureTaskC, "CallableTaskC").start();
        // vérifier le futureTask finit ou non
        while (!futureTaskC.isDone()) {
            System.out.println("in process...");
        }
        // employer la méthode get(), 2 get() méthodes
        System.out.println("On main thread get message from futurTask: " + futureTaskC.get());
        System.out.println("On main thread get message from futurTask: " + futureTaskC.get());
        System.out.println(Thread.currentThread().getName() + " is over");
    }
}
