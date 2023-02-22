package org.java_juc.begginer_level.future_cf_executor_service_16.completable_future;

import org.java_juc.begginer_level.future_cf_executor_service_16.completable_future.model.User;
import org.java_juc.begginer_level.future_cf_executor_service_16.completable_future.supplyAsync.CompletableFutureSupplyAsync;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainSupplyAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFutureSupplyAsync supplyAsync = new CompletableFutureSupplyAsync();
        final List<User> allUsers = supplyAsync.getAllUsers();
        System.out.println("Print form main Method: " + Thread.currentThread().getName());
        for (int i = 16; i < 21; i++) {
            System.out.println(allUsers.get(i));
        }

        CompletableFutureSupplyAsync supplyAsyncWithExecutor = new CompletableFutureSupplyAsync();

        final List<User> allUsersWithExecutor = supplyAsyncWithExecutor.getAllUsersWithExecutor();
        System.out.println("Print form main Method: " + Thread.currentThread().getName());
        for (int i = 22; i < 26; i++) {
            System.out.println(allUsersWithExecutor.get(i));
        }

        CompletableFutureSupplyAsync supplyAsyncWithExecutorService = new CompletableFutureSupplyAsync();

        final List<User> allUsersWithExecutorService = supplyAsyncWithExecutorService.getAllUsersWithExecutorService();
        System.out.println("Print form main Method: " + Thread.currentThread().getName());
        for (int i = 27; i < 31; i++) {
            System.out.println(allUsersWithExecutorService.get(i));
        }
    }
}
