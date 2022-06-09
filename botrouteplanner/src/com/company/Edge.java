package com.company;

//krawędz grafu zawierająca wierzchołki źródłowy i końcowy oraz wagę
public class Edge {

    // wierzcholek zrodlowy
    private final int from;
    // wierzcholek docelowy
    private final int to;
    // waga
    private final float weight;

    public Edge(int from, int to, float weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public float getWeight() {
        return weight;
    }

    //wyświetlam na standardowe wyjście szczegóły krawędzi
    @Override
    public String toString() {
        return String.format("%d->%d (%f) ", from, to, weight);
    }
}
