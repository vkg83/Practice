package com.vkg.pactice.thread;

public class Question2 {
    public static void main(String[] args) {
        new Question2().go();
    }

    public void go() {
        Runnable r = new Runnable() {
            public void run() {
                System.out.print("foo");
            }

        };
        Thread t = new Thread(r);
        t.start();
        // multiple start will throw IllegalThreadStateException
        t.start();
    }
}
