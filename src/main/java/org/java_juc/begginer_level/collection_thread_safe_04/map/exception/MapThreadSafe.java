package org.java_juc.begginer_level.collection_thread_safe_04.map.exception;

import java.util.*;

/**
 * HashMap thread unsafe -> Exception in thread "19" java.util.ConcurrentModificationException
 */
public class MapThreadSafe {
    public static void main(String[] args) {
        Map<String, String> mapA = new HashMap<>();
        Map<String, String> mapB = new HashMap<>();


        for (int i = 0; i < 10; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
               // add new element in the listA
                mapA.put(key, UUID.randomUUID().toString().substring(0, 5));
                System.out.println(mapA);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 50; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                // add new element in the listB
                mapB.put(key, UUID.randomUUID().toString().substring(0, 5));
                System.out.println(mapB);
            }, String.valueOf(i)).start();
        }
    }
}
