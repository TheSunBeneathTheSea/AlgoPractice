package Acmicpc;

import java.util.ArrayList;

public class Acmicpc01167 {
  public static int diameter = 0;
  public static int furthest = -1;
  public static ArrayList<Edge>[] edges;

  public static int solve(ArrayList<Edge>[] givenEdges) {
    edges = givenEdges;
    boolean[] isVisited = new boolean[edges.length + 1];
    isVisited[1] = true;
    dfs(1, 0, isVisited);
    isVisited = new boolean[edges.length + 1];
    isVisited[furthest] = true;
    dfs(furthest, 0, isVisited);

    return diameter;
  }

  public static void dfs(int curNode, int distance, boolean[] isVisited) {
    if (distance >= diameter) {
      diameter = distance;
      furthest = curNode;
    }
    for (int i = 0; i < edges[curNode].size(); i++) {
      Edge nextEdge = edges[curNode].get(i);
      int nextNode = nextEdge.node;
      if (!isVisited[nextNode]) {
        isVisited[nextNode] = true;
        dfs(nextNode, distance + nextEdge.distance, isVisited);
        isVisited[nextNode] = false;
      }
    }
  }
}

class Edge {
  int node;
  int distance;

  public Edge(int node, int distance) {
    this.node = node;
    this.distance = distance;
  }
}