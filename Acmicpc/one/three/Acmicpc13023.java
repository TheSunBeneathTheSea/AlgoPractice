package Acmicpc.one.three;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Acmicpc13023 {
  static boolean[] isVisited;
  static ArrayList<Integer>[] adjList;
  static int maxDepth, maxIdx;
  static boolean exist = false;

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(tokenizer.nextToken());
    int M = Integer.parseInt(tokenizer.nextToken());

    isVisited = new boolean[N];
    adjList = new ArrayList[N];

    for (int i = 0; i < N; i++) {
      adjList[i] = new ArrayList<>();
    }

    while (M-- > 0) {
      tokenizer = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(tokenizer.nextToken());
      int b = Integer.parseInt(tokenizer.nextToken());

      adjList[a].add(b);
      adjList[b].add(a);
    }

    for (int i = 0; i < N; i++) {
      isVisited = new boolean[N];
      dfs(i, 1);
    }

    System.out.println(exist ? 1 : 0);
  }

  public static void dfs(int idx, int depth) {
    if (isVisited[idx]) return;

    if (maxDepth < depth) {
      maxDepth = depth;
      maxIdx = idx;
    }

    isVisited[idx] = true;

    if (depth == 5) {
      exist = true;
      return;
    }

    for (int next : adjList[idx]) {
      dfs(next, depth + 1);
    }

    isVisited[idx] = false;
  }
}
