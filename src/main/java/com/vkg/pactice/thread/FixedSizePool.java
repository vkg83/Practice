package com.vkg.pactice.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class FixedSizePool {
    LinkedBlockingDeque<Runnable> taskQueue = new LinkedBlockingDeque<>();
    Worker[] workers;
    public FixedSizePool(int size) {
        workers = new Worker[size];
        for (int i = 0; i < size; i++) {
            workers[i] = new Worker(taskQueue);
            workers[i].start();
        }
    }

    public void executeTask(Runnable task) {
        try {
            this.taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        for (int i = 0; i < workers.length; i++) {
            workers[i].complete();
        }
    }

    public void shutdownNow() {
        for (int i = 0; i < workers.length; i++) {
            workers[i].interrupt();
        }
    }

    private static final class Worker extends Thread {
        private LinkedBlockingDeque<Runnable> taskQueue;
        private volatile boolean stop;

        public Worker(LinkedBlockingDeque<Runnable> taskQueue) {
            this.taskQueue = taskQueue;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Started ...");
            try {
                while(!stop || !taskQueue.isEmpty()) {
                    Runnable r = taskQueue.poll(1, TimeUnit.SECONDS);
                    if(r !=null) {
                        r.run();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Ended ...");
        }

        public void complete() {
            stop = true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FixedSizePool pool = new FixedSizePool(50);
        for (int i = 2; i <1000; i++) {
            pool.executeTask(new Task(i));
        }
        //Thread.sleep(2000);
        pool.shutdown();
    }

    private static final class Task implements Runnable {
        private int num;

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println(String.format("%s has taken %d", Thread.currentThread().getName(), num));
            for (int i = 1; i <= 10; i++) {
                System.out.println(String.format("%s - %d * %d = %d", Thread.currentThread().getName(), num, i, num * i));
            }
        }
    }
}
