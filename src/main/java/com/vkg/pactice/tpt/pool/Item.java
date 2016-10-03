package com.vkg.pactice.tpt.pool;

public class Item implements Poolable {
    private ObjectPool<Item> pool;

    public Item(final ObjectPool<Item> pool) {
        this.pool = pool;
    }

    @Override
    public void close() {
        pool.returnObject(this);
    }
}
