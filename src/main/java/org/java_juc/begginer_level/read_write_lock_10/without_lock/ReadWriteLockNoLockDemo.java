package org.java_juc.begginer_level.read_write_lock_10.without_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Problem, adding and reading at the same time, before finishing adding or reading !!
 */
// resource
class DataResource {
    // volatile is changing property
    private volatile Map<String, Object> data = new HashMap<>();

    // read data
    public void add(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + " is adding un element " + key);

        // sleep some time
        try {
            TimeUnit.MICROSECONDS.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        data.put(key, value);
        System.out.println(Thread.currentThread().getName() + " finish adding the element " + key);
    }

    // read data
    public Object read(String key) {
        Object result;

        System.out.println(Thread.currentThread().getName() + " is reading un element " + key);

        // sleep some time
        try {
            TimeUnit.MICROSECONDS.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = data.get(key);
        System.out.println(Thread.currentThread().getName() + " finish reading the element " + key);

        return result;
    }
}

// read and change the element in 5 times
public class ReadWriteLockNoLockDemo {
    public static void main(String[] args) {
        DataResource myData = new DataResource();

        // create Threads and add data
        for (int i = 1; i <= 5; i++) {
            final int number = i;
            new Thread(() -> {
                myData.add(number + "", number + "");
            }, "Thread n° " + i).start();
        }

        // create Threads and read data
        for (int i = 1; i <= 5; i++) {
            final int number = i;
            new Thread(() -> {
                myData.read(number + "");
            }, "Thread n° " + i).start();
        }
    }

}
/** possible result:
 * Thread n° 2 is adding un element 2
 * Thread n° 1 is adding un element 1
 * Thread n° 3 is adding un element 3
 * Thread n° 4 is adding un element 4
 * Thread n° 5 is adding un element 5
 * Thread n° 2 is reading un element 2
 * Thread n° 1 is reading un element 1
 * Thread n° 3 is reading un element 3
 * Thread n° 5 is reading un element 5
 * Thread n° 4 is reading un element 4
 * Thread n° 3 finish adding the element 3
 * Thread n° 5 finish reading the element 5
 * Thread n° 3 finish reading the element 3
 * Thread n° 4 finish adding the element 4
 * Thread n° 1 finish reading the element 1
 * Thread n° 5 finish adding the element 5
 * Thread n° 1 finish adding the element 1
 * Thread n° 2 finish adding the element 2
 * Thread n° 2 finish reading the element 2
 * Thread n° 4 finish reading the element 4
 */
