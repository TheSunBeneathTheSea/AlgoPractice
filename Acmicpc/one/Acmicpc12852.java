package Acmicpc.one;

import java.io.*;
import java.util.ArrayDeque;

public class Acmicpc12852 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    ArrayDeque<Pair> queue = new ArrayDeque<>();

    queue.add(new Pair(N, 0, N + " "));

    while (!queue.isEmpty()) {
      Pair cur = queue.poll();
      if (cur.value == 1) {
        bw.write(cur.length + "\n");
        bw.write(cur.path);
        break;
      }

      if(cur.value % 3 == 0) queue.add(new Pair(cur.value / 3, cur.length + 1, cur.path + cur.value / 3 + " "));
      if(cur.value % 2 == 0) queue.add(new Pair(cur.value / 2, cur.length + 1, cur.path + cur.value / 2 + " "));
      queue.add(new Pair(cur.value - 1, cur.length + 1, cur.path + (cur.value - 1) + " "));
    }

    bw.flush();
    bw.close();
    br.close();
  }

  static class Pair {
    int value, length;
    String path;

    public Pair(int value, int length, String path) {
      this.value = value;
      this.length = length;
      this.path = path;
    }
  }
}
