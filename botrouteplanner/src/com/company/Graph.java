package com.company;

import java.util.ArrayList;
import java.util.List;


public class Graph {
//klasa wykorzystywana do grafowego przedstawienia domeny obliczniowej
    //zawiera liczbę wierzchołków v oraz liczbę krawędzi e
    //posiada listę sąsiedztw
    private final int v;
    private int e;
    List<Edge>[] neighborhoodLists;

    public Graph(int v) {
        this.v = v;
        this.e = 0;
        this.neighborhoodLists = (List<Edge>[]) new List[v];
        for (int i = 0; i < v; i++) {
            neighborhoodLists[i] = new ArrayList<Edge>();
        }
    }

    public int getNumberOfEdges() {
        return e;
    }

    public int getNumberOfVertices() {
        return v;
    }

    public void addEdge(Edge edge) {
        neighborhoodLists[edge.from()].add(edge);
        e++;
    }

    public Iterable<Edge> getNeighborhoodList(int v) {
        return neighborhoodLists[v];
    }

}
