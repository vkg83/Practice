package com.vkg.pactice.thread;

public class RunnableThread {
    public static void main(String args[]) {
        new NewThread();
    }
}

class NewThread implements Runnable {
    private Thread t;

    NewThread() {
        t = new Thread(this, "My Thread");
        t.start();
    }


    public void run() {
        System.out.println(t.getName());
    }

}

