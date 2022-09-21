package Acmicpc.one.six;

import java.io.*;
import java.util.ArrayDeque;

public class Acmicpc16953 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      String[] input = br.readLine().split(" ");

      long a = Long.parseLong(input[0]);
      long b = Long.parseLong(input[1]);

      ArrayDeque<Long[]> queue = new ArrayDeque<>();

      long count = -1;
      queue.add(new Long[]{a, 1l});

      while (!queue.isEmpty()) {
        Long[] cur = queue.poll();

        if (cur[0] == b) {
          count = cur[1];
          break;
        } else if (cur[0] > b) {
          continue;
        } else {
          queue.add(new Long[]{cur[0] * 2, cur[1] + 1});
          queue.add(new Long[]{Long.parseLong(cur[0] + "1"), cur[1] + 1});
        }
      }

      bw.write(count + "");
      bw.flush();
      br.close();
      bw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
