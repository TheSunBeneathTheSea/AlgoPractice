package Acmicpc.one;

import java.io.*;
import java.util.*;

public class Acmicpc14938 {
  static int[] nodes;
  static ArrayList<Edge>[] edges;
  static int findRange;

  public static void main(String[] args) {
    try {
      //    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      StringTokenizer tokenizer = new StringTokenizer(br.readLine());

      int n = Integer.parseInt(tokenizer.nextToken());
      int m = Integer.parseInt(tokenizer.nextToken());
      int r = Integer.parseInt(tokenizer.nextToken());

      nodes = new int[n + 1];
      edges = new ArrayList[n + 1];
      findRange = m;

      for (int i = 1; i < edges.length; i++) {
        edges[i] = new ArrayList<>();
      }

      tokenizer = new StringTokenizer(br.readLine());

      for (int i = 1; i < nodes.length; i++) {
        nodes[i] = Integer.parseInt(tokenizer.nextToken());
      }

      while (r-- > 0) {
        tokenizer = new StringTokenizer(br.readLine());

        int node1 = Integer.parseInt(tokenizer.nextToken());
        int node2 = Integer.parseInt(tokenizer.nextToken());
        int weight = Integer.parseInt(tokenizer.nextToken());

        edges[node1].add(new Edge(node2, weight));
        edges[node2].add(new Edge(node1, weight));
      }

      int maxItem = 0;

      for (int i = 1; i < n + 1; i++) {
        maxItem = Math.max(maxItem, dijkstra(i));
      }

      bw.write(maxItem + "");
      bw.flush();
      bw.close();
      br.close();
    } catch (IOException e) {
    }
  }

  public static int dijkstra(int start) {
    PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> b.weight - a.weight);
    int[] distances = new int[nodes.length];
    int maxItem = 0;

    pq.add(new Edge(start, 0));
    Arrays.fill(distances, 15001);
    distances[start] = 0;

    while (!pq.isEmpty()) {
      Edge cur = pq.poll();
      for (Edge e : edges[cur.dest]) {
        if (distances[e.dest] <= distances[cur.dest] + e.weight) continue;

        distances[e.dest] = distances[cur.dest] + e.weight;
        pq.add(e);
      }
    }

    for (int i = 1; i < distances.length; i++) {
      if (distances[i] > findRange) continue;

      maxItem += nodes[i];
    }

    return maxItem;
  }

  static class Edge {
    int dest;
    int weight;

    public Edge(int dest, int weight) {
      this.dest = dest;
      this.weight = weight;
    }
  }
}
