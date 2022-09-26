package Acmicpc.zero.one;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Acmicpc01516 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    int[] inDegree = new int[N + 1];
    int[] times = new int[N + 1];
    int[] result = new int[N + 1];

    ArrayList<Integer>[] requirementsList = new ArrayList[N + 1];

    for (int i = 1; i <= N; i++) {
      requirementsList[i] = new ArrayList<>();
    }

    StringTokenizer tokenizer;
    for (int i = 1; i <= N; i++) {
      tokenizer = new StringTokenizer(br.readLine());

      times[i] = Integer.parseInt(tokenizer.nextToken());

      int next = Integer.parseInt(tokenizer.nextToken());
      while (next != -1) {
        requirementsList[next].add(i);
        inDegree[i]++;
        next = Integer.parseInt(tokenizer.nextToken());
      }
    }

    ArrayDeque<Integer> deque = new ArrayDeque<>();

    for (int i = 1; i <= N; i++) {
      if (inDegree[i] == 0) {
        deque.add(i);
        result[i] = times[i];
      }
    }

    while (!deque.isEmpty()) {
      Integer cur = deque.poll();

      for (int next : requirementsList[cur]) {
        result[next] = Math.max(result[next], result[cur] + times[next]);
        if (--inDegree[next] == 0) deque.add(next);
      }
    }

    for (int i = 1; i <= N; i++) {
      bw.write(result[i] + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
