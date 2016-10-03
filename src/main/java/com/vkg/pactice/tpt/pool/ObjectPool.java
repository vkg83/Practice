package com.vkg.pactice.tpt.pool;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class ObjectPool<T extends Poolable> {
    private int maxPoolSize;
    private int minPoolSize;
    private ArrayList<T> pool;
    private int clearInterval = 10000;
    private int poolCount;

    public ObjectPool(int maxPoolSize, int minPoolSize) {
        this.maxPoolSize = maxPoolSize;
        this.minPoolSize = minPoolSize;
        this.pool = new ArrayList<T>();
        initialize();
    }

    protected void initialize() {
        for (int i = 0; i < minPoolSize; i++) {
            this.pool.add(createObject());
            poolCount++;
        }


        final Thread thread = new Thread(() -> {
            synchronized (pool) {
                try {
                    pool.wait(clearInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int removeCount = pool.size() - minPoolSize;
                if (removeCount > 0) {
                    System.out.println("Cleaning up " + removeCount + " Idle Items out of " + pool.size());
                    Iterator<T> itr = pool.iterator();
                    while (itr.hasNext() && removeCount > 0) {
                        itr.next();
                        itr.remove();
                        poolCount--;
                        removeCount--;
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public T getObject() {
        T obj = null;
        synchronized (pool) {
            while(pool.size() <= 0 && poolCount >= maxPoolSize) {
                try {
                    pool.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(pool.size() == 0) {
                pool.add(createObject());
                poolCount++;
            }

            obj = pool.remove(0);
        }
        return obj;
    }

    abstract protected T createObject();

    public void returnObject(final T obj) {
        synchronized (pool) {
            pool.add(obj);
            pool.notify();
        }
    }
}
