package org.java_juc.begginer_level.collection_thread_safe_04.map.handle_exception;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map thread safe -> ConcurrentHashMap
 */
public class MapThreadSafe {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 50; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                // add new element in the listA
                map.put(key, UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
