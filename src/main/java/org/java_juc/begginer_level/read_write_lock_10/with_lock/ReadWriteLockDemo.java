package org.java_juc.begginer_level.read_write_lock_10.with_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Deal the problem, adding and reading at the same time, before finishing adding or reading with readLock and write lock!!
 */
// resource
class DataStore {
    // volatile is changing property
    private volatile Map<String, Object> data = new HashMap<>();

    // Create ReadWriteLock object
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // read data
    public void add(String key, Object value) {
        // lock this add method
        readWriteLock.writeLock().lock();

        // sleep some time
        try {
            System.out.println(Thread.currentThread().getName() + " is adding un element " + key);
            TimeUnit.MICROSECONDS.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // release the writeLock
            readWriteLock.writeLock().unlock();
        }

        data.put(key, value);
        System.out.println(Thread.currentThread().getName() + " finish adding the element " + key);
    }

    // read data
    public Object read(String key) {
        // lock readLock before read method
        readWriteLock.readLock().lock();
        Object result;
        // sleep some time
        try {
            System.out.println(Thread.currentThread().getName() + " is reading un element " + key);
            TimeUnit.MICROSECONDS.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // release readLock
            readWriteLock.readLock().unlock();
        }

        result = data.get(key);
        System.out.println(Thread.currentThread().getName() + " finish reading the element " + key);

        return result;
    }
}

// read and change the element in 5 times
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        DataStore myData = new DataStore();

        // create Threads and add data
        for (int i = 1; i <= 5; i++) {
            final int number = i;
            new Thread(() -> {
                myData.add(number + "", number + "");
            }, "Thread n° " + i).start();
        }
        try {
            TimeUnit.MICROSECONDS.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
 * Thread n° 1 is adding un element 1
 * Thread n° 1 finish adding the element 1
 * Thread n° 2 is adding un element 2
 * Thread n° 2 finish adding the element 2
 * Thread n° 3 is adding un element 3
 * Thread n° 3 finish adding the element 3
 * Thread n° 4 is adding un element 4
 * Thread n° 4 finish adding the element 4
 * Thread n° 5 is adding un element 5
 * Thread n° 5 finish adding the element 5
 * Thread n° 1 is reading un element 1
 * Thread n° 2 is reading un element 2
 * Thread n° 5 is reading un element 5
 * Thread n° 4 is reading un element 4
 * Thread n° 3 is reading un element 3
 * Thread n° 2 finish reading the element 2
 * Thread n° 5 finish reading the element 5
 * Thread n° 4 finish reading the element 4
 * Thread n° 1 finish reading the element 1
 * Thread n° 3 finish reading the element 3
 */
