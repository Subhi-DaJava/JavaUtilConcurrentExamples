package org.java_juc.begginer_level.collection_thread_safe_04.arraylist.handle_exception.collections;

import java.util.*;

/**
 * ArrayList thread safe -> handle with Collections.synchronizedList(new ArrayList<>())
 */
public class ListThreadSafe {
    public static void main(String[] args) {

        List<String> list = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                // add new element in the list
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
