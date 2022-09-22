package Acmicpc.zero.one;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Acmicpc01033 {
  static ArrayList<Node>[] adjList;
  static boolean[] isVisited;
  static long[] result;

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    long lcm = 1;
    int N = Integer.parseInt(br.readLine());
    result = new long[N];
    isVisited = new boolean[N];
    adjList = new ArrayList[N];

    for (int i = 0; i < adjList.length; i++) {
      adjList[i] = new ArrayList<>();
    }

    StringTokenizer tokenizer;
    for (int i = 0; i < N - 1; i++) {
      tokenizer = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(tokenizer.nextToken());
      int b = Integer.parseInt(tokenizer.nextToken());
      int p = Integer.parseInt(tokenizer.nextToken());
      int q = Integer.parseInt(tokenizer.nextToken());

      adjList[a].add(new Node(b, p, q));
      adjList[b].add(new Node(a, q, p));
      lcm *= p * q / getGCD(p, q);
    }

    result[0] = lcm;
    dfs(0);

    long mgcd = result[0];
    for (int i = 1; i < N; i++) {
      mgcd = getGCD(mgcd, result[i]);
    }


    for (long num : result) {
      bw.write((num / mgcd) + " ");
    }
    bw.flush();
    bw.close();
    br.close();
  }

  public static void dfs(int node) {
    isVisited[node] = true;

    for (Node n : adjList[node]) {
      int next = n.getPartner();
      if (!isVisited[next]) {
        result[next] = result[node] * n.getQ() / n.getP();
        dfs(next);
      }
    }

  }

  public static long getGCD(long a, long b) {
    if (a > b) {
      if (a % b == 0) {
        return b;
      } else {
        return getGCD(b, a % b);
      }
    } else {
      if (b % a == 0) {
        return a;
      } else {
        return getGCD(a, b % a);
      }
    }
  }

  static class Node {
    int partner, p, q;

    public Node(int partner, int p, int q) {
      this.partner = partner;
      this.p = p;
      this.q = q;
    }
    public int getPartner() {
      return partner;
    }

    public int getP() {
      return p;
    }

    public int getQ() {
      return q;
    }
  }
}
