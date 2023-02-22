package org.java_juc.begginer_level.future_cf_executor_service_16.completable_future.supplyAsync;

import org.java_juc.begginer_level.future_cf_executor_service_16.completable_future.model.User;
import org.java_juc.begginer_level.future_cf_executor_service_16.completable_future.repository.UserRepository;

import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureSupplyAsync {
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        final CompletableFuture<List<User>> usersList = CompletableFuture.supplyAsync(() -> {
            System.out.println("The thread global: " + Thread.currentThread().getName());
            for (int i = 0; i <= 5; i++) {
                System.out.println(UserRepository.getUsers().get(i));
            }
            return UserRepository.getUsers();
        });
        return usersList.get();
    }



    public List<User> getAllUsersWithExecutor() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(4);
        final CompletableFuture<List<User>> usersList = CompletableFuture.supplyAsync(() -> {
            System.out.println("The thread global: " + Thread.currentThread().getName());
            for (int i = 11; i <= 15; i++) {
                System.out.println(UserRepository.getUsers().get(i));
            }
            return UserRepository.getUsers();
        }, executor);

        return usersList.get();
    }

    public List<User> getAllUsersWithExecutorService() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        final CompletableFuture<List<User>> usersList = CompletableFuture.supplyAsync(() -> {
            System.out.println("The thread global: " + Thread.currentThread().getName());
            for (int i = 6; i <= 10; i++) {
                System.out.println(UserRepository.getUsers().get(i));
            }
            return UserRepository.getUsers();
        }, executor);

        executor.shutdown();

        return usersList.get();
    }
}
