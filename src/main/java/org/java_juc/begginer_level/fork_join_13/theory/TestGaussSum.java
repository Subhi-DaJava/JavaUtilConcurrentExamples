package org.java_juc.begginer_level.fork_join_13.theory;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class TestGaussSum {
    public static void main(String[] args) throws InterruptedException {
        int result = 0;

        int startTime = 0;
        int executeTime;
        int endTime;
        startTime = LocalDateTime.now().getNano();
        for (int i = 1; i <= 100 ; i++) {
           result += i;
        }
        endTime = LocalDateTime.now().getNano();
        executeTime = endTime - startTime;

        System.out.println("Execute time: " + executeTime);

        TimeUnit.SECONDS.sleep(2);
        System.out.println(result);
    }
}
/*
Execute time: 20000000
5050
 */