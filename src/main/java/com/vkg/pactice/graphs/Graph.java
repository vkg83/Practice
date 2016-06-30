package com.vkg.pactice.graphs;

import java.util.List;

public interface Graph<V> {
    void addEdge(V startVertex, V endVertex);
    int verticesCount();
    int edgesCount();
    List<V> getNeighbours(V vertex);
    boolean areConnected(V vertexOne, V vertexTwo);
}
