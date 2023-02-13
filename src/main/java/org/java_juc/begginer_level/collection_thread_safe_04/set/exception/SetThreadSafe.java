package org.java_juc.begginer_level.collection_thread_safe_04.set.exception;

import java.util.*;

/**
 * HashSet thread unsafe -> Exception in thread "6" java.util.ConcurrentModificationException
 */
public class SetThreadSafe {
    public static void main(String[] args) {
        Set<String> listA = new HashSet<>();
        Set<String> listB = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
               // add new element in the listA
                listA.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(listA);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                // add new element in the listB
                listB.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(listB);
            }, String.valueOf(i)).start();
        }
    }
}
