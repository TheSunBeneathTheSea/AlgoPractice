package Acmicpc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Acmicpc01916 {
  static final int INF = 100000000;

  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int n = Integer.parseInt(br.readLine());
      int m = Integer.parseInt(br.readLine());

      ArrayList<Edge>[] edges = new ArrayList[n + 1];

      for (int i = 0; i < edges.length; i++) {
        edges[i] = new ArrayList<>();
      }

      StringTokenizer token;
      while (m-- > 0) {
        token = new StringTokenizer(br.readLine());

        int from = Integer.parseInt(token.nextToken());
        int to = Integer.parseInt(token.nextToken());
        int weight = Integer.parseInt(token.nextToken());

        edges[from].add(new Edge(to, weight));
      }

      token = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(token.nextToken());
      int b = Integer.parseInt(token.nextToken());

      int answer = solve(edges, a, b);

      bw.write(answer + "");
      bw.flush();
      br.close();

      bw.close();
    } catch (IOException e) {

    }
  }

  static int solve(ArrayList<Edge>[] edges, int a, int b) {
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    int[] distances = new int[edges.length];
    boolean[] isVisited = new boolean[edges.length];

    pq.add(new Edge(a, 0));
    Arrays.fill(distances, INF);
    distances[a] = 0;

    while (!pq.isEmpty()) {
      Edge cur = pq.poll();

      if (isVisited[cur.to]) {
        continue;
      }
      for (int i = 0; i < edges[cur.to].size(); i++) {
        int to = edges[cur.to].get(i).to;
        int weight = edges[cur.to].get(i).weight;

        distances[to] = Math.min(distances[to], distances[cur.to] + weight);
        pq.add(new Edge(to, distances[to]));
      }
      isVisited[cur.to] = true;
    }

    return distances[b];
  }

  static class Edge implements Comparable<Edge> {
    int to;
    int weight;

    @Override
    public int compareTo(Edge o) {
      return this.weight - o.weight;
    }

    Edge(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }
  }
}
