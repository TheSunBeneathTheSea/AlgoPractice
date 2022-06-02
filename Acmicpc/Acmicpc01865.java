package Acmicpc;

import java.io.*;
import java.util.*;

public class Acmicpc01865 {
  public static final int INF = 6000000;

  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int tc = Integer.parseInt(br.readLine());

      while (tc-- > 0) {
        StringTokenizer token = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());
        int w = Integer.parseInt(token.nextToken());

        ArrayList<Edge> edgeList = new ArrayList<>();
        ArrayList<Edge> wormholeList = new ArrayList<>();

        while (m-- > 0) {
          token = new StringTokenizer(br.readLine());

          int from = Integer.parseInt(token.nextToken());
          int to = Integer.parseInt(token.nextToken());
          int weight = Integer.parseInt(token.nextToken());

          edgeList.add(new Edge(from, to, weight));
          edgeList.add(new Edge(to, from, weight));
        }
        while (w-- > 0) {
          token = new StringTokenizer(br.readLine());

          int from = Integer.parseInt(token.nextToken());
          int to = Integer.parseInt(token.nextToken());
          int weight = Integer.parseInt(token.nextToken());

          wormholeList.add(new Edge(from, to, -1 * weight));
        }

        bw.write(solve(n, edgeList, wormholeList) + "\n");

      }

      bw.flush();
      br.close();

      bw.close();
    } catch (IOException e) {

    }
  }

  public static String solve(int v, ArrayList<Edge> eList, ArrayList<Edge> wList) {
    Edge w = wList.get(0);

    int[] distances = new int[v + 1];
    Arrays.fill(distances, INF);
    distances[w.start] = 0;

    for (int i = 1; i < v; i++) {
      for (int j = 0; j < eList.size(); j++) {
        Edge cur = eList.get(j);

        distances[cur.destination] = Math.min(distances[cur.destination], distances[cur.start] + cur.weight);
      }
      for (int j = 0; j < wList.size(); j++) {
        Edge cur = wList.get(j);

        distances[cur.destination] = Math.min(distances[cur.destination], distances[cur.start] + cur.weight);
      }
    }

    boolean hasNegativeCycle = false;
    for (int j = 0; j < eList.size(); j++) {
      Edge cur = eList.get(j);

      if (distances[cur.destination] > distances[cur.start] + cur.weight) {
        hasNegativeCycle = true;
      }
    }
    for (int j = 0; j < wList.size(); j++) {
      Edge cur = wList.get(j);

      if (distances[cur.destination] > distances[cur.start] + cur.weight) {
        hasNegativeCycle = true;
      }
    }

    return hasNegativeCycle ? "YES" : "NO";
  }

  private static class Edge {
    int start;
    int destination;
    int weight;

    Edge(int start, int destination, int weight) {
      this.start = start;
      this.destination = destination;
      this.weight = weight;
    }
  }
}
