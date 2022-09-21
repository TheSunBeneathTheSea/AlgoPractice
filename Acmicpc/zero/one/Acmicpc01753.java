package Acmicpc.zero.one;

import java.io.*;
import java.util.*;

public class Acmicpc01753 {
  static class Edge01753 implements Comparable<Edge01753> {
    int to;
    int weight;

    Edge01753(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge01753 o) {
      return this.weight - o.weight;
    }
  }

  static LinkedList<Edge01753>[] adjList;


  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      StringTokenizer token = new StringTokenizer(br.readLine());
      int v = Integer.parseInt(token.nextToken());
      int e = Integer.parseInt(token.nextToken());

      int start = Integer.parseInt(br.readLine());
      adjList = new LinkedList[v + 1];

      for (int i = 1; i < adjList.length; i++) {
        adjList[i] = new LinkedList<>();
      }

      for (int i = 0; i < e; i++) {
        token = new StringTokenizer(br.readLine());

        int from = Integer.parseInt(token.nextToken());
        int to = Integer.parseInt(token.nextToken());
        int weight = Integer.parseInt(token.nextToken());

        adjList[from].add(new Edge01753(to, weight));
      }

      String answer = solve(start);

      bw.write(answer);
      bw.flush();
      br.close();

      bw.close();
    } catch (IOException e) {

    }
  }

  public static String solve(int start) {
    PriorityQueue<Edge01753> pq = new PriorityQueue<>();
    int[] distances = new int[adjList.length];
    boolean[] isVisited = new boolean[adjList.length];
    Arrays.fill(distances, 3000001);
    distances[start] = 0;

    pq.add(new Edge01753(start, 0));

    while (!pq.isEmpty()) {
      Edge01753 cur = pq.poll();

      if (isVisited[cur.to]) {
        continue;
      }

      for (int i = 0; i < adjList[cur.to].size(); i++) {
        int to = adjList[cur.to].get(i).to;
        int weight = adjList[cur.to].get(i).weight;

        distances[to] = Math.min(distances[to], distances[cur.to] + weight);
        pq.add(new Edge01753(to, distances[to]));
      }

      isVisited[cur.to] = true;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < distances.length; i++) {
      if (distances[i] >= 3000001) {
        sb.append("INF");
      } else {
        sb.append(distances[i]);
      }
      sb.append("\n");
    }

    return sb.toString();
  }
}
