package com.vkg.pactice.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdjListGraph<V> extends AbstractGraph<V> {
    private Map<V, List<V>> adjListMap;

    public void addEdge(final V startVertex, final V endVertex) {
        getAdjacencyListOf(startVertex).add(endVertex);
    }

    private List<V> getAdjacencyListOf(final V startVertex) {
        List<V> adjList = adjListMap.get(startVertex);

        if (adjList == null) {
            adjList = new ArrayList<V>();
            adjListMap.put(startVertex, adjList);
        }

        return adjList;
    }

    public List<V> getNeighbours(final V vertex) {
        return new ArrayList<V>(getAdjacencyListOf(vertex));
    }

    public boolean areConnected(final V vertexOne, final V vertexTwo) {
        return false;
    }
}
