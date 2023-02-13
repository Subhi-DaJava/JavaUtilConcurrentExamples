package org.java_juc.begginer_level.collection_thread_safe_04.set.handle_exception;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ArrayList thread safe -> CopyOnWriteArrayList
 */
public class SetThreadSafe {
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                // add new element in the listB
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
