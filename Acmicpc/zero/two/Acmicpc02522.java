package Acmicpc.zero.two;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Acmicpc02522 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(tokenizer.nextToken());
    int M = Integer.parseInt(tokenizer.nextToken());
    int[] inDegree = new int[N + 1];

    ArrayList<Integer>[] adjList = new ArrayList[N + 1];

    for (int i = 0; i <= N; i++) {
      adjList[i] = new ArrayList<>();
    }

    while (M-- > 0) {
      tokenizer = new StringTokenizer(br.readLine());

      int shorter = Integer.parseInt(tokenizer.nextToken());
      int taller = Integer.parseInt(tokenizer.nextToken());

      adjList[shorter].add(taller);
      inDegree[taller]++;
    }

    ArrayDeque<Integer> deque = new ArrayDeque<>();

    for (int i = 1; i <= N; i++) {
      if (inDegree[i] == 0) deque.add(i);
    }

    while (!deque.isEmpty()) {
      Integer cur = deque.poll();
      bw.write(cur + " ");

      for (int next : adjList[cur]) {
        if (--inDegree[next] == 0) deque.addFirst(next);
      }
    }

    bw.write("\n");
    bw.flush();
    bw.close();
    br.close();
  }
}
