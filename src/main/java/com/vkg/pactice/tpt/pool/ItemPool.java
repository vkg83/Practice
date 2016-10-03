package com.vkg.pactice.tpt.pool;

public class ItemPool extends ObjectPool<Item> {
    int count = 0;
    public ItemPool(final int maxPoolSize, final int minPoolSize) {
        super(maxPoolSize, minPoolSize);
    }

    @Override
    protected Item createObject() {
        count++;
        System.out.println("Creating Item " + count);
        return new Item(this);
    }
}
