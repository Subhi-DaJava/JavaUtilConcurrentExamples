package org.java_juc.begginer_level.cf_executor_service_15.completablefurture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        final String msg;
        CompletableFuture<String> futureResponse =
                CompletableFuture.supplyAsync(() -> {
                    for (int i = 0; i < 3; i++) {
                        System.out.println(Thread.currentThread().getName() +  i + " is running");
                    }
                    return "hi, je suis là";
                });

        // Traitement des données récupérées dans la réponse de l'API
        futureResponse.thenAccept(System.out::println); // System.out.println(response);
        try {
             msg = futureResponse.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("This is the message we got after async: " + msg);
    }
}
