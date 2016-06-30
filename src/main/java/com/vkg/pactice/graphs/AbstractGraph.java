package com.vkg.pactice.graphs;

public abstract class AbstractGraph<V> implements Graph<V> {
    private int edgesCount = 0;
    private int verticesCount = 0;

    public int verticesCount() {
        return verticesCount;
    }

    public int edgesCount() {
        return edgesCount;
    }

}
