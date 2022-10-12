package Acmicpc.zero.one;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Acmicpc01197 {
  static int[] group;

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());

    int V = Integer.parseInt(tokenizer.nextToken());
    int E = Integer.parseInt(tokenizer.nextToken());

    group = new int[V + 1];
    for (int i = 1; i <= V; i++) {
      group[i] = i;
    }

    PriorityQueue<Edge> pq = new PriorityQueue<>();

    while (E-- > 0) {
      tokenizer = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(tokenizer.nextToken());
      int to = Integer.parseInt(tokenizer.nextToken());
      long weight = Long.parseLong(tokenizer.nextToken());

      pq.add(new Edge(from, to, weight));
    }

    long weightSum = 0;
    int edgeCount = 0;
    while (edgeCount < V - 1) {
      Edge cur = pq.poll();

      if (find(cur.from) == find(cur.to)) continue;

      union(cur.from, cur.to);
      weightSum += cur.weight;
      edgeCount++;
    }

    System.out.println(weightSum);
  }

  public static void union(int a, int b) {
    a = find(a);
    b = find(b);

    if (a != b) {
      group[b] = a;
    }
  }

  public static int find(int a) {
    if (group[a] == a) return a;
    else return group[a] = find(group[a]);
  }

  static class Edge implements Comparable<Edge>{
    int from, to;
    long weight;

    Edge(int from, int to, long weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
      return Long.compare(this.weight, o.weight);
    }
  }
}
