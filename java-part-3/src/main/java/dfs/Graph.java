package dfs;

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
        for (int i = 1; i < adjacencyList.length; i++) {
            System.out.println("Vertex " + i + ": " + adjacencyList[i]);
        }
    }

    public void dfs(int start) {
        boolean[] visited = new boolean[adjacencyList.length];
        System.out.println("정점 " + start + "에서 시작하는 DFS");
        dfsRecursive(start, visited);
        System.out.println();
    }

    private void dfsRecursive(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int adj : adjacencyList[vertex]) {
            if (!visited[adj]) {
                dfsRecursive(adj, visited);
            }
        }
    }
}
