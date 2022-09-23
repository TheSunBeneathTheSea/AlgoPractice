package Acmicpc.zero.one;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc01717 {
  static int[] group;

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(tokenizer.nextToken());
    int M = Integer.parseInt(tokenizer.nextToken());

    group = new int[N + 1];

    for (int i = 0; i < group.length; i++) {
      group[i] = i;
    }

    while (M-- > 0) {
      tokenizer = new StringTokenizer(br.readLine());
      int op = Integer.parseInt(tokenizer.nextToken());
      int a = Integer.parseInt(tokenizer.nextToken());
      int b = Integer.parseInt(tokenizer.nextToken());

      if (op == 0) union(a, b);
      else bw.write(find(a) == find(b) ? "YES\n" : "NO\n");
    }
    bw.flush();
    bw.close();
    br.close();
  }

  public static void union(int a, int b) {
    if (group[a] == a && group[b] == b) group[b] = a;
    else if (group[a] == a) {
      union(a, group[b]);
    } else union(group[a], b);
  }

  public static int find(int a) {
    if (group[a] != a) {
      group[a] = find(group[a]);
    }
    return group[a];
  }
}
