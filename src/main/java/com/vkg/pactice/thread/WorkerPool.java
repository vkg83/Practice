package com.vkg.pactice.thread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class WorkerPool {
    private Thread[] threads;
    private BlockingDeque<Runnable> queue;
    private volatile boolean stopFlag;
    private long start = System.currentTimeMillis();

    public WorkerPool(int count) {
        threads = new Thread[count];
        queue = new LinkedBlockingDeque<>();
        initPool();
    }

    public void queue(Runnable task) {
            queue.offer(task);
    }

    private void initPool() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Worker());
            threads[i].start();
        }
    }

    private class Worker implements Runnable {

        @Override
        public void run() {
            while(!stopFlag || !queue.isEmpty()) {
                try {
                    queue.take().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() +": "+ (System.currentTimeMillis() - start));
        }
    }

    public void stop() {
        stopFlag = true;
        synchronized (queue) {
            queue.notifyAll();
        }
    }
}
