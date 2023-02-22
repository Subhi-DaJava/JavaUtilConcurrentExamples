package org.java_juc.begginer_level.future_cf_executor_service_16.fc_supplyrun_thenaccept.multi_thread_applyasync;

import org.java_juc.begginer_level.future_cf_executor_service_16.completable_future.model.User;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Invite to an event :
 * GetAllUsers -> filter female -> filter salary > 90000 -> extract allEmails -> send message to every Email
 */
public class UserServiceThenApplyAsync {
    public CompletableFuture<Void> sendNotification() {
        final CompletableFuture<Void> completableFutureWithThenApply = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread getAllUsers : " + Thread.currentThread().getName());
            return UserData.getAllUsers();
        }).thenApplyAsync(users -> {
            System.out.println("Thread filters Female : " + Thread.currentThread().getName());
            return users.stream().filter(user -> "Female".equals(user.getGender())).collect(Collectors.toList());
        }).thenApplyAsync(users -> {
            System.out.println("Thread filters Salary greater than 90000 : " + Thread.currentThread().getName());
            return users.stream().filter(user -> user.getSalary() > 90000).collect(Collectors.toList());
        }).thenApplyAsync(users -> {
            System.out.println("Thread extracts emails : " + Thread.currentThread().getName());
            return users.stream().map(User::getEmail).collect(Collectors.toList()); // user.getEmail()
        }).thenAcceptAsync(emails -> {
            System.out.println("Thread sends emails : " + Thread.currentThread().getName());
            emails.forEach(this::sendEmail);
        });
        return completableFutureWithThenApply;
    }

    public void sendEmail(String emailAddress) {
        System.out.println("Hi, you are invited to this event: " + emailAddress);
    }
}
