package com.vkg.pactice.thread;

class Pen {
}

class Paper {
}

public class DeadLock {

    public static void main(String[] args) {
        final Pen pen = new Pen();
        final Paper paper = new Paper();

        Thread t1 = new Thread() {
            public void run() {
                System.out.println("Thread1 is Requesting for Pen");
                synchronized (pen) {
                    System.out.println("Thread1 is holding Pen");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("Thread1 is Requesting for Paper");
                    synchronized (paper) {
                        System.out.println("Thread1 is Holding Paper");
                    }

                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                System.out.println("Thread2 is Requesting for Paper");
                synchronized (paper) {
                    System.out.println("Thread2 is holding Paper");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("Thread2 is Requesting for Pen");
                    synchronized (pen) {
                        System.out.println("Thread2 is Holding Pen");
                    }

                }
            }
        };

        t1.start();
        t2.start();
    }

}