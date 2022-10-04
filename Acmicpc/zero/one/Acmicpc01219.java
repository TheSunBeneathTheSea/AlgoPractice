package Acmicpc.zero.one;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Acmicpc01219 {
  static final long MIN = Long.MIN_VALUE;
  static final long MAX = Long.MAX_VALUE;

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(tokenizer.nextToken());
    int start = Integer.parseInt(tokenizer.nextToken());
    int end = Integer.parseInt(tokenizer.nextToken());
    int M = Integer.parseInt(tokenizer.nextToken());

    long[] money = new long[N];
    ArrayList<Edge> edges = new ArrayList<>(M);

    Arrays.fill(money, MIN);

    while (M-- > 0) {
      tokenizer = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(tokenizer.nextToken());
      int to = Integer.parseInt(tokenizer.nextToken());
      int cost = Integer.parseInt(tokenizer.nextToken());

      edges.add(new Edge(from, to, cost));
    }

    int[] earn = new int[N];
    tokenizer = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      earn[i] = Integer.parseInt(tokenizer.nextToken());
    }
    money[start] = earn[start];

    for (int i = 0; i < N + 100; i++) {
      for (Edge e : edges) {
        if (money[e.from] == MIN) continue;
        else if (money[e.from] == MAX) {
          money[e.to] = MAX;
        } else if (money[e.to] < money[e.from] - e.cost + earn[e.to]) {
          money[e.to] = money[e.from] - e.cost + earn[e.to];

          if (i >= N - 1) money[e.to] = MAX;
        }
      }
    }

    if (money[end] == MIN) System.out.println("gg");
    else if (money[end] == MAX) System.out.println("Gee");
    else System.out.println(money[end]);
  }

  static class Edge {
    int from, to, cost;

    Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }
}
