package Acmicpc.zero.one;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Acmicpc01707 {
  public static ArrayList<Integer>[] adjList;
  public static boolean[] isVisited;
  public static int[] group;
  public static boolean isValid;

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int K = Integer.parseInt(br.readLine());

    while (K-- > 0) {
      StringTokenizer tokenizer = new StringTokenizer(br.readLine());

      int V = Integer.parseInt(tokenizer.nextToken());
      int E = Integer.parseInt(tokenizer.nextToken());
      isVisited = new boolean[V + 1];
      group = new int[V + 1];
      isValid = true;

      adjList = new ArrayList[V + 1];

      for (int i = 1; i <= V; i++) {
        adjList[i] = new ArrayList<>();
      }

      while (E-- > 0) {
        tokenizer = new StringTokenizer(br.readLine());

        int v1 = Integer.parseInt(tokenizer.nextToken());
        int v2 = Integer.parseInt(tokenizer.nextToken());

        adjList[v1].add(v2);
        adjList[v2].add(v1);
      }

      for (int i = 1; i <= V; i++) {
        dfs(i);
      }

      if (isValid) bw.write("YES\n");
      else bw.write("NO\n");

      bw.flush();
    }
  }
  public static void dfs(int start) {
    isVisited[start] = true;

    for (int i : adjList[start]) {
      if (!isVisited[i]) {
        group[i] = (group[start] + 1) % 2;
        dfs(i);
      } else if (group[start] == group[i]) isValid = false;
    }
  }
}
