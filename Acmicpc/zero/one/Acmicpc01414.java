package Acmicpc.zero.one;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Acmicpc01414 {
  static int[] group;

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));

    int N = Integer.parseInt(br.readLine());
    group = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      group[i] = i;
    }

    PriorityQueue<Edge> pq = new PriorityQueue<>();
    int sum = 0;
    for (int row = 1; row <= N; row++) {
      char[] line = br.readLine().toCharArray();

      for (int col = 1; col <= N; col++) {
        int cur;

        if (Character.isLowerCase(line[col - 1])) cur = line[col - 1] - 'a' + 1;
        else if (Character.isUpperCase(line[col - 1])) cur = line[col - 1] - 'A' + 27;
        else continue;

        if (row == col) sum += cur;
        else pq.add(new Edge(row, col, cur));
      }
    }

    int count = 0;
    while(!pq.isEmpty() && count < N - 1) {
      Edge cur = pq.poll();

      if (find(cur.from) == find(cur.to)) {
        sum += cur.weight;
        continue;
      }

      union(cur.from, cur.to);
      count++;
    }

    if (count < N - 1) {
      System.out.println("-1");
      return;
    }

    while (!pq.isEmpty()) {
      sum += pq.poll().weight;
    }

    System.out.println(sum);
  }

  public static void union(int a, int b) {
    a = find(a);
    b = find(b);

    group[b] = a;
  }

  public static int find(int a) {
    if (group[a] == a) return a;

    return group[a] = find(group[a]);
  }

  static class Edge implements Comparable<Edge>{
    int from, to, weight;

    Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
      return this.weight - o.weight;
    }
  }
}
