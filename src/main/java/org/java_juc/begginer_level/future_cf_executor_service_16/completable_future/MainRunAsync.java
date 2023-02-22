package org.java_juc.begginer_level.future_cf_executor_service_16.completable_future;

import org.java_juc.begginer_level.future_cf_executor_service_16.completable_future.runAsync.CompletableFutureRunAsync;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class MainRunAsync {
    public static void main(String[] args) {
        CompletableFutureRunAsync completableFutureRunAsync = new CompletableFutureRunAsync();
        try {
            completableFutureRunAsync.saveUsers(new File("user_data.json"));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            completableFutureRunAsync.saveUsersWithExecutor(new File("user_data.json"));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            completableFutureRunAsync.saveUsersWithExecutorAndMultiThreading(new File("user_data.json"));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
