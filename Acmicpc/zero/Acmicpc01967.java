package Acmicpc.zero;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Acmicpc01967 {
  static final int INF = 1000000;

  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int n = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] adjList = new ArrayList[n + 1];

      for (int i = 0; i < adjList.length; i++) {
        adjList[i] = new ArrayList<>();
      }

      while (br.ready()) {
        StringTokenizer token = new StringTokenizer(br.readLine());

        int from = Integer.parseInt(token.nextToken());
        int to = Integer.parseInt(token.nextToken());
        int weight = Integer.parseInt(token.nextToken());

        adjList[from].add(new Edge(to, weight));
        adjList[to].add(new Edge(from, weight));
      }

      int answer = n == 1 ? 0 : solve(adjList);

      bw.write(answer + "");
      bw.flush();
      br.close();
      bw.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static int solve(ArrayList<Edge>[] adjList) {
    int[] distances = dijkstra(adjList, 1);

    int max = 0;
    int maxIdx = -1;
    for (int i = 0; i < distances.length; i++) {
      if (distances[i] > max && distances[i] != INF) {
        max = distances[i];
        maxIdx = i;
      }
    }

    max = 0;
    distances = dijkstra(adjList, maxIdx);
    for (int i = 0; i < distances.length; i++) {
      if (distances[i] > max && distances[i] != INF) {
        max = distances[i];
      }
    }

    return max;
  }

  public static int[] dijkstra(ArrayList<Edge>[] adjList, int start) {
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    boolean[] isVisited = new boolean[adjList.length];
    int[] distances = new int[adjList.length];

    Arrays.fill(distances, INF);
    distances[start] = 0;
    isVisited[start] = true;

    pq.add(new Edge(start, 0));

    while (!pq.isEmpty()) {
      Edge cur = pq.poll();
      isVisited[cur.to] = true;

      for (int i = 0; i < adjList[cur.to].size(); i++) {
        Edge next = adjList[cur.to].get(i);

        if (isVisited[next.to]) {
          continue;
        }

        if (distances[next.to] > distances[cur.to] + next.weight) {
          distances[next.to] = distances[cur.to] + next.weight;
          pq.add(next);
        }
      }
    }

    return distances;
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
