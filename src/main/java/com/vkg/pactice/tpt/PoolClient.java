package com.vkg.pactice.tpt;

import com.vkg.pactice.tpt.pool.Item;
import com.vkg.pactice.tpt.pool.ItemPool;

public class PoolClient {
    public static void main(String[] args) throws Exception {
        ItemPool pool = new ItemPool(8, 4);
        for (int i = 0; i < 50; i++) {
            Thread th = new Thread(new PoolObjectUtilizer(pool));
            th.start();
        }
    }
}

class PoolObjectUtilizer implements Runnable {
    ItemPool pool;

    public PoolObjectUtilizer(final ItemPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ") Getting Object from Pool");
        Item item = pool.getObject();
        System.out.println(Thread.currentThread().getName() + ") Got Object from Pool");
        try {
            Thread.sleep((long)(1000 + Math.random() * 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        item.close();
        System.out.println(Thread.currentThread().getName() + ") returned Object to Pool");
    }
}