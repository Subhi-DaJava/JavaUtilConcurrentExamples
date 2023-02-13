package org.java_juc.begginer_level.collection_thread_safe_04.arraylist.handle_exception.co_on_wr_a;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList thread safe -> CopyOnWriteArrayList
 */
public class ListThreadSafe {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                // add new element in the listB
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
