package com.vkg.pactice.trees;

public interface Tree<I> {
    void insert(I item);
    void delete(I item);
    boolean find(I item);
}
