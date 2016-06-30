package com.vkg.pactice.thread;

class PingPong2 {
    // One thread will complete the for loop because of synchronized block
    synchronized void hit(long n) {
        for (int i = 1; i < 3; i++)
            System.out.print(n + "-" + i + " ");
    }
}

public class Question1 implements Runnable {
    static PingPong2 pp2 = new PingPong2();

    public static void main(String[] args) {
        new Thread(new Question1()).start();
        new Thread(new Question1()).start();
    }

    public void run() {
        pp2.hit(Thread.currentThread().getId());
    }
}