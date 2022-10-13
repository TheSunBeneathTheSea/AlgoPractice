package Programmers.Level2;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class 배달 {
  public int solution(int N, int[][] road, int K) {
    int answer = 0;
    int[] distances = new int[N + 1];
    boolean[] isVisited = new boolean[N + 1];

    for (int i = 2; i < distances.length; i++) {
      distances[i] = Integer.MAX_VALUE;
    }

    ArrayList<Edge>[] adjList = new ArrayList[N + 1];

    for (int i = 1; i < adjList.length; i++) {
      adjList[i] = new ArrayList<>();
    }

    for (int[] e : road) {
      adjList[e[0]].add(new Edge(e[1], e[2]));
      adjList[e[1]].add(new Edge(e[0], e[2]));
    }

    PriorityQueue<Edge> pq = new PriorityQueue<>();
    pq.add(new Edge(1, 0));

    while (!pq.isEmpty()) {
      Edge cur = pq.poll();

      if (isVisited[cur.to]) continue;
      isVisited[cur.to] = true;

      for (Edge next : adjList[cur.to]) {
        if (distances[next.to] <= distances[cur.to] + next.weight) continue;
        distances[next.to] = distances[cur.to] + next.weight;
        pq.add(next);
      }
    }

    for (int i = 1; i < distances.length; i++) {
      if (distances[i] <= K) answer++;
    }

    return answer;
  }

  static class Edge implements Comparable<Edge>{
    int to, weight;

    Edge(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
      return this.weight - o.weight;
    }
  }
}
