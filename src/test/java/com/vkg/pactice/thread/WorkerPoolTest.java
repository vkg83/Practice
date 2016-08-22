package com.vkg.pactice.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerPoolTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        WorkerPoolTest test = new WorkerPoolTest();
        test.shouldUseCustomThreadPool();
        System.out.println(System.currentTimeMillis() - start);
    }

    //@Test
    public void shouldUseBuiltInThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10000; i++) {
            pool.execute(new Table(i));
        }

        pool.shutdown();
    }

    public void shouldUseCustomThreadPool() {
        WorkerPool pool = new WorkerPool(4);
        for (int i = 1; i <= 100000; i++) {
            pool.queue(new Table(i));
        }

        pool.stop();
    }
}

class Table implements Runnable {

    private final int number;

    public Table(final int number) {
        this.number = number;
    }

    @Override
    public void run() {
        StringBuffer br = new StringBuffer();
        for (int i = 1; i <= 1000; i++) {
            br.append(String.format("%s -> %d * %d = %d", Thread.currentThread().getName(), number, i, number * i)).append("\n");
        }
        System.out.print(br.toString());
    }
}