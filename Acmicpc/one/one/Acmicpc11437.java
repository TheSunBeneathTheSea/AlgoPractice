package Acmicpc.one.one;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Acmicpc11437 {
  static int[][] parents;
  static int[] depth;

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    ArrayList<Integer>[] tree = new ArrayList[N + 1];

    for (int i = 1; i < tree.length; i++) {
      tree[i] = new ArrayList<>();
    }

    boolean[] isVisited = new boolean[N + 1];
    depth = new int[N + 1];
    int[] parents0 = new int[N + 1];

    StringTokenizer tokenizer;
    for (int i = 0; i < N - 1; i++) {
      tokenizer = new StringTokenizer(br.readLine());

      int node1 = Integer.parseInt(tokenizer.nextToken());
      int node2 = Integer.parseInt(tokenizer.nextToken());

      tree[node1].add(node2);
      tree[node2].add(node1);
    }

    ArrayDeque<Integer[]> queue = new ArrayDeque<>();

    int maxDepth = 0;
    isVisited[1] = true;
    queue.add(new Integer[] {1, 0});
    while (!queue.isEmpty()) {
      Integer[] cur = queue.poll();

      for (Integer child : tree[cur[0]]) {
        if (isVisited[child]) continue;

        isVisited[child] = true;
        parents0[child] = cur[0];
        depth[child] = cur[1] + 1;
        queue.add(new Integer[]{child, cur[1] + 1});
        if (maxDepth < depth[child]) maxDepth = depth[child];
      }
    }

    int k = 1;
    while ((int) Math.pow(2, k) <= maxDepth) k++;

    parents = new int[k][N + 1];
    parents[0] = parents0;

    for (int i = 1; i < parents.length; i++) {
      parents[i] = new int[N + 1];
    }

    for (int i = 1; i < parents.length; i++) {
      for (int j = 1; j < parents[0].length; j++) {
        parents[i][j] = parents[i - 1][parents[i - 1][j]];
      }
    }

    int M = Integer.parseInt(br.readLine());

    while (M-- > 0) {
      tokenizer = new StringTokenizer(br.readLine());

      int node1 = Integer.parseInt(tokenizer.nextToken());
      int node2 = Integer.parseInt(tokenizer.nextToken());

      bw.write(findLCA(node1, node2) + "\n");
    }

    bw.flush();
    br.close();
    bw.close();
  }

  public static int findLCA(int node1, int node2) {
    while (node1 != node2) {
      if (depth[node1] < depth[node2]) {
        int tmp = node1;
        node1 = node2;
        node2 = tmp;
      }

      int depth1 = depth[node1];
      int depth2 = depth[node2];
      if (depth1 > depth2) {
        int gap = depth1 - depth2;

        int k = 0;

        while ((int) Math.pow(2, k + 1) <= gap) k++;

        node1 = parents[k][node1];
      } else {
        int k;
        for (k = parents.length - 1; k >= 0; k--) {
          if (node1 == node2) break;

          if (parents[k][node1] != parents[k][node2]) {
            node1 = parents[k][node1];
            node2 = parents[k][node2];
          }
        }

        break;
      }
    }
    if (node1 != node2) {
      node1 = parents[0][node1];
    }

    return node1;
  }
}