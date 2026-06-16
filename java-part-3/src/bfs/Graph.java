package bfs;

import java.util.*;

public class Graph {
    LinkedList<Integer>[] adjacencyList;

    public Graph(int vertex) {
        this.adjacencyList = new LinkedList[vertex + 1];
        for (int i = 0; i <= vertex; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public LinkedList<Integer>[] getAdjacencyList() {
        return adjacencyList;
    }

    public void addEdge(int v, int w) {
        adjacencyList[v].add(w);
        adjacencyList[w].add(v);
    }

    public void printGraph() {
        for (int i = 0; i < adjacencyList.length; i++) {
            System.out.println("Vertex " + i + ": " + adjacencyList[i]);
        }
    }
}
