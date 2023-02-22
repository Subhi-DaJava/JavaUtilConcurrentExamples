package org.java_juc.begginer_level.future_cf_executor_service_16.completable_future.runAsync;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_juc.begginer_level.future_cf_executor_service_16.completable_future.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CompletableFutureRunAsync {
    public Void saveUsers(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();

        // runAsync(Runnable())
        final CompletableFuture<Void> runAsyncMethod = CompletableFuture.runAsync(() -> {
            try {
                final List<User> users = mapper.readValue(jsonFile, new TypeReference<List<User>>() {
                });

                // execute des tasks for business logic
                System.out.println("Thread with runAsync: " + Thread.currentThread().getName());
                System.out.println(users.size());
                for (int i = 0; i < 10; i++) {
                    System.out.println(users.get(i));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //runAsyncMethod.toCompletableFuture();
        return runAsyncMethod.get();
    }

   /*
    possible result:
    Thread with runAsync: ForkJoinPool.commonPool-worker-1 -> "common or global thread"
1000
User{id='642-82-1580', firstName='Audra', lastName='Grabert', email='agrabert0@hc360.com', gender='Female', salary=44496, userName='agrabert0'}
User{id='167-97-9573', firstName='Jade', lastName='Bisterfeld', email='jbisterfeld1@newsvine.com', gender='Female', salary=18786, userName='jbisterfeld1'}
User{id='270-83-6795', firstName='Dominique', lastName='Benz', email='dbenz2@tinypic.com', gender='Male', salary=45236, userName='dbenz2'}
User{id='669-19-4763', firstName='Louise', lastName='Kondratyuk', email='lkondratyuk3@ezinearticles.com', gender='Female', salary=51650, userName='lkondratyuk3'}
User{id='454-71-2542', firstName='Scarlet', lastName='Thorsby', email='sthorsby4@howstuffworks.com', gender='Female', salary=57853, userName='sthorsby4'}
User{id='176-22-7640', firstName='Charo', lastName='Po', email='cpo5@techcrunch.com', gender='Female', salary=55745, userName='cpo5'}
User{id='473-84-3266', firstName='Felice', lastName='Dufore', email='fdufore6@sourceforge.net', gender='Female', salary=98562, userName='fdufore6'}
User{id='351-06-3025', firstName='Hubey', lastName='Ninnis', email='hninnis7@bizjournals.com', gender='Male', salary=40514, userName='hninnis7'}
User{id='601-34-7149', firstName='Hedy', lastName='Fearfull', email='hfearfull8@hugedomains.com', gender='Female', salary=12668, userName='hfearfull8'}
User{id='324-14-8101', firstName='Derek', lastName='Hagerty', email='dhagerty9@skyrock.com', gender='Male', salary=54889, userName='dhagerty9'}
    */


    // runAsync(Runnable, Executor)
    public Void saveUsersWithExecutor(File jsonFile) throws ExecutionException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        Executor executor = Executors.newFixedThreadPool(5);
        // runAsync(Runnable(), Executor)
        final CompletableFuture<Void> runAsyncMethodWithExecutor = CompletableFuture.runAsync(() -> {
            try {
                final List<User> users = mapper.readValue(jsonFile, new TypeReference<List<User>>() {
                });

                // execute des tasks for business logic
                System.out.println("Thread with runAsync: " + Thread.currentThread().getName());
                System.out.println(users.size());

                for (int i = 0; i < 10; i++) {
                    System.out.println(users.get(i));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        //runAsyncMethodWithExecutor.toCompletableFuture();
        return runAsyncMethodWithExecutor.get();
    }

    // runAsync(Runnable, Executor) avec plusieurs Threads
    public Void saveUsersWithExecutorAndMultiThreading(File jsonFile) throws ExecutionException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        Executor executor = Executors.newFixedThreadPool(5);
        // runAsync(Runnable(), Executor)
        CompletableFuture<Void> runAsyncMethodWithExecutor = CompletableFuture.runAsync(() -> {
            try {
                    final List<User> users = mapper.readValue(jsonFile, new TypeReference<List<User>>() {
                    });

                    // execute des tasks for business logic
                    System.out.println("Thread with runAsync: " + Thread.currentThread().getName());
                    System.out.println(users.size());

                    for (int i = 0; i < 10; i++) {
                        System.out.println(users.get(i));
                    }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executor);


        //runAsyncMethodWithExecutor.toCompletableFuture();
        return runAsyncMethodWithExecutor.get();
    }
}
