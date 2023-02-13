package org.java_juc.begginer_level.collection_thread_safe_04.arraylist.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ArrayList thread unsafe -> Exception in thread "4" java.util.ConcurrentModificationException
 */
public class ListThreadSafe {
    public static void main(String[] args) {
        List<String> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();

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
