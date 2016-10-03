package com.vkg.pactice.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchClient {
    public static void main(String[] args) throws InterruptedException {
        Driver driver = new Driver();
        driver.main();
    }
}

class Driver { // ...
    void main() throws InterruptedException {
        int N = 10;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i) // create and start threads
            new Thread(new Worker(startSignal, doneSignal)).start();

        doSomethingElse();            // don't let run yet
        startSignal.countDown();      // let all threads proceed
        doSomethingElse();
        doneSignal.await();           // wait for all to finish
        System.out.println("Done");
    }

    private void doSomethingElse() throws InterruptedException {
        System.out.println("Doing something Else");
    }
}

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }
    public void run() {
        try {
            System.out.println("Waiting to start");
            startSignal.await();
            doWork();
            doneSignal.countDown();
            System.out.println("Finished");
        } catch (InterruptedException ex) {} // return;
    }

    void doWork() {
        System.out.println("Doing some Work");
    }
}