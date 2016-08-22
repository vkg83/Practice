package com.vkg.pactice.thread;

public class ThreadSyncOddEven {
    public static void main(String[] args) {
        Value current = new Value();

        Thread threadOdd = new Thread(new NumberPrinter(false, current));
        Thread threadEven = new Thread(new NumberPrinter(true, current));

        threadEven.start();
        threadOdd.start();
    }
}

class Value {
    int val = 0;

    public void print() {
        System.out.println(val);
    }

    public synchronized void increment() {
        val++;
        notify();
    }

    public boolean isEven() {
        return val % 2 == 0;
    }
}

class NumberPrinter implements Runnable {
    Value value;
    boolean even;

    public NumberPrinter(final boolean even, Value v) {
        this.even = even;
        this.value = v;
    }

    @Override
    public void run() {
        while (value.val < 100) {
            synchronized (value) {

                if (even != value.isEven()) {
                    try {
                        value.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                value.print();
                value.increment();
            }
        }
    }
}
