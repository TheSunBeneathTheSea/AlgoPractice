package Acmicpc.one.one;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Acmicpc11567 {
  static final int INF = 60000001;
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(tokenizer.nextToken());
    int M = Integer.parseInt(tokenizer.nextToken());

    long[] distances = new long[N + 1];
    ArrayList<Edge> edges = new ArrayList<>(M);

    Arrays.fill(distances, INF);
    distances[1] = 0;

    while (M-- > 0) {
      tokenizer = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(tokenizer.nextToken());
      int to = Integer.parseInt(tokenizer.nextToken());
      int cost = Integer.parseInt(tokenizer.nextToken());

      edges.add(new Edge(from, to, cost));
    }

    for (int i = 1; i < N; i++) {
      for (Edge edge : edges) {
        if (distances[edge.from] != INF && distances[edge.to] > distances[edge.from] + edge.cost) {
          distances[edge.to] = distances[edge.from] + edge.cost;
        }
      }
    }

    for (Edge edge : edges) {
      if (distances[edge.from] != INF && distances[edge.to] > distances[edge.from] + edge.cost) {
        System.out.println("-1");
        return;
      }
    }

    for (int i = 2; i <= N; i++) {
      bw.write((distances[i] == INF ? -1 : distances[i]) + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
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
