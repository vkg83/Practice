package com.vkg.pactice.thread;


public class ThreadOddEven {
    public static void main(String[] args) {

        LockObj obj = new LockObj();

        Thread t1 = new Thread(new IntPrinter(obj, false));
        Thread t2 = new Thread(new IntPrinter(obj, true));
        t2.start();
        t1.start();
    }
}

class LockObj {
    private boolean currentTurn;

    public boolean isTurn(final boolean turn) {
        return currentTurn == turn;
    }

    public void toggleTurn() {
        currentTurn = !currentTurn;
        notify();
    }
}

class IntPrinter implements Runnable {
    LockObj lock;
    boolean isEven;

    public IntPrinter(final LockObj lock, boolean isEven) {
        this.lock = lock;
        this.isEven = isEven;
    }

    @Override
    public void run() {
        for (int i = isEven? 0 : 1; i < 100; i+=2) {
            synchronized (lock) {
                if(notMyTurn()) {
                    waitForChance();
                }
                performWork(i);
                lock.toggleTurn();
            }
        }
    }

    private void waitForChance() {
        try {
            lock.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void performWork(final int i) {
        try {
            Thread.sleep(1 + (long)(Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }

    private boolean notMyTurn() {
        return lock.isTurn(isEven);
    }
}

