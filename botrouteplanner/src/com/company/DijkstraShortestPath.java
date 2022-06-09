package com.company;

import java.util.*;

public class DijkstraShortestPath {

    public DijkstraShortestPath() {
    }

    class DistanceToEdge implements Comparable<DistanceToEdge> {

        private final int edge;

        private float distance;

        public DistanceToEdge(int edge, float distance) {
            this.edge = edge;
            this.distance = distance;
        }

        public float getDistance() {
            return distance;
        }

        public void setDistance(long distance) {
            this.distance = distance;
        }

        public int getEdge() {
            return edge;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DistanceToEdge other = (DistanceToEdge) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (distance != other.distance)
                return false;
            if (edge != other.edge)
                return false;
            return true;
        }



        private DijkstraShortestPath getOuterType() {
            return DijkstraShortestPath.this;
        }

        @Override
        public int compareTo(DistanceToEdge o) {
            return 0;
        }
    }

    //tablica krwędzi
    private Edge[] edgeTo;
    //tablica przechowująca odległości
    private float[] distanceTo;

    private Queue<DistanceToEdge> priorityQueue;


    //algorytm dijkstry obliczający sieżkę z wierzchołka początkowego
    public DijkstraShortestPath(Graph graph, int source) {

        edgeTo = new Edge[graph.getNumberOfVertices()];
        distanceTo = new float[graph.getNumberOfVertices()];
        priorityQueue = new PriorityQueue<DistanceToEdge>(
                graph.getNumberOfVertices());

        for (int v = 0; v < graph.getNumberOfVertices(); v++) {
            distanceTo[v] = Long.MAX_VALUE;
        }

        distanceTo[source] = 0L;

        priorityQueue.offer(new DistanceToEdge(source, 0L));

        while (!priorityQueue.isEmpty()) {
            relax(graph, priorityQueue.poll().getEdge());
        }

    }

    private void relax(Graph graph, int v) {

        //dla każdego lemenetu z listy sąsiedztwa sprawdzam odległości i zapisuje najkorzytniejszą
        for (Edge edge : graph.getNeighborhoodList(v)) {
            int w = edge.to();


            if (distanceTo[w] > distanceTo[v] + edge.getWeight()) {
                distanceTo[w] = distanceTo[v] + edge.getWeight();
                edgeTo[w] = edge;
                DistanceToEdge dte = new DistanceToEdge(w, distanceTo[w]);

                priorityQueue.remove(dte);

                priorityQueue.offer(dte);
            }
        }

    }

    //metoda zwraca odległość do wierchołka v
    public float getDistanceTo(int v) {
        return distanceTo[v];
    }

    //metoda sprawdza czy istnieje połączenie
    public boolean hasPathTo(int v) {
        return distanceTo[v] < Long.MAX_VALUE;
    }

    //metoda zwraca ścieżkę do wierzchołka v
    public Iterable<Edge> getPathTo(int v) {
        Deque<Edge> path = new ArrayDeque<Edge>();

        if (!hasPathTo(v)) {
            return path;
        }

        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }


    //funkcja tworzy graf z przekazanej domeny obliczeniowej
    //przyjmuje domenę z której zostanie utworzony graf
    //przyjmuje numery wierzchołków, poczatkowego i końcowego
    public List<Edge> setGraph(Grid grid, int start, int end){

        //tworzę nowy graf o liczbie wierzchołków o wartosci wymiarów gridu (x,y)
        Graph graph = new Graph(grid.xSize*grid.ySize);

        //dodaje krawędzie do grafu
        //z kazdego wierzchołka bot będzie mógł poruszac sie w 4 strony ( góra, dół, prawo, lewo)
        for(int i=0;i<grid.xSize;i++){
            for(int j=0;j<grid.ySize;j++){

                //sprawdzam czy wierzchołek nie jest przypadkiem wyłączony z użytku (stan "O")
                Character stateOfVerticle = grid.conteiner[i][j].state;
                if(!stateOfVerticle.equals("O")) {


                    //sprawdzam czy nie nastapi wyjście z domeny obliczeniowej
                    if (i + 1 > 0 && i + 1 < grid.xSize) {
                        //sprzawdzam stan wierzchołka z którym chcę połączyć (prawo)
                        Character state = grid.conteiner[i + 1][j].state;
                        if (!state.equals("O")) {
                            //sprawdzam czas aby utworzyć wagę dla danej krawędzi
                            float time = grid.conteiner[i][j].getMoveTime();
                            if (grid.conteiner[i + 1][j].getMoveTime() > time) time = grid.conteiner[i + 1][j].getMoveTime();
                            graph.addEdge(new Edge(grid.conteiner[i][j].number, grid.conteiner[i + 1][j].number, time));
                        }
                    }

                    //sprawdzam czy nie nastapi wyjście z domeny obliczeniowej
                    if (j + 1 > 0 && j + 1 < grid.ySize) {
                        //sprzawdzam stan wierzchołka z którym chcę połączyć (dół)
                        Character state = grid.conteiner[i][j + 1].state;
                        if (!state.equals("O")) {
                            //sprawdzam czas aby utworzyć wagę dla danej krawędzi
                            float time = grid.conteiner[i][j].getMoveTime();
                            if (grid.conteiner[i][j + 1].getMoveTime() > time) time = grid.conteiner[i][j + 1].getMoveTime();
                            graph.addEdge(new Edge(grid.conteiner[i][j].number, grid.conteiner[i][j + 1].number, time));
                        }
                    }

                    //sprawdzam czy nie nastapi wyjście z domeny obliczeniowej
                    if (i - 1 >= 0 && i - 1 < grid.xSize) {
                        //sprzawdzam stan wierzchołka z którym chcę połączyć (lewo)
                        Character state = grid.conteiner[i - 1][j].state;
                        if (!state.equals("O")) {
                            //sprawdzam czas aby utworzyć wagę dla danej krawędzi
                            float time = grid.conteiner[i][j].getMoveTime();
                            if (grid.conteiner[i - 1][j].getMoveTime() > time) time = grid.conteiner[i - 1][j].getMoveTime();
                            graph.addEdge(new Edge(grid.conteiner[i][j].number, grid.conteiner[i - 1][j].number, time));
                        }
                    }

                    //sprawdzam czy nie nastapi wyjście z domeny obliczeniowej
                    if (j - 1 >= 0 && j - 1 < grid.ySize) {
                        //sprzawdzam stan wierzchołka z którym chcę połączyć (góra)
                        Character state = grid.conteiner[i][j - 1].state;
                        if (!state.equals("O")) {
                            //sprawdzam czas aby utworzyć wagę dla danej krawędzi
                            float time = grid.conteiner[i][j].getMoveTime();
                            if (grid.conteiner[i][j - 1].getMoveTime() > time) time = grid.conteiner[i][j - 1].getMoveTime();
                            graph.addEdge(new Edge(grid.conteiner[i][j].number, grid.conteiner[i][j - 1].number, time));
                        }
                    }
                }
            }
        }


        //zadaje punkt startowy
        int source = start;
        DijkstraShortestPath shortestPath = new DijkstraShortestPath(graph, source);

        //zadaje punkt końcowy
        int target = end;

        //tworzę liste krawędzi wynikowych
        List<Edge> path = new ArrayList<>();
        //sprawdzam czy istnieje połączenie
        if (shortestPath.hasPathTo(target)) {

            //zapisuje krawędzie do listy krawędzi
            for (Edge edge : shortestPath.getPathTo(target)) {
                path.add(edge);
            }
        //w przypadku braku połączenia wypisuje na ekran informacje o braku ścieżki
        } else { System.out.printf("%d do %d - brak sciezki  ", source, target); }

        //zwracam ścieżkę
        return path;
    }
}
