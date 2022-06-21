package Acmicpc.zero;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Acmicpc02448 {
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
  static int N;

  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int n = Integer.parseInt(br.readLine());
      N = n;

      markTarget(n, n, 0);
      printTarget();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void markTarget(int size, int x, int y) {
    int nextSize = size / 2;
    if (size > 3) {
      markTarget(nextSize, x, y);
      markTarget(nextSize, x - nextSize, y + nextSize);
      markTarget(nextSize, x + nextSize, y + nextSize);
    } else {
      map.putIfAbsent(y, new ArrayList<>());
      map.get(y).add(x);
    }
  }

  static void printTarget() throws IOException {
    for (int i = 0; i < N; i += 3) {
      ArrayList<Integer> cur = map.get(i);
      StringBuilder sb = new StringBuilder();

      String globalBlank = " ".repeat(N - i - 1);
      for (int j = 0; j < 3; j++) {
        globalBlank = " ".repeat(N - i - j - 1);
        sb.append(globalBlank);

        for (Integer x : cur) {
          String localBlank = " ".repeat(x - 1 - sb.length() - j);
          sb.append(localBlank);

          switch (j) {
            case 0:
              sb.append("*");
              break;
            case 1:
              sb.append("* *");
              break;
            case 2:
              sb.append("*****");
              break;
          }

        }
        if (i == N - 3 && j == 2) {
          bw.write(sb.toString() + globalBlank);
        } else {
          bw.write(sb.toString() + globalBlank + "\n");
        }
        bw.flush();
        sb = new StringBuilder();
      }
    }
  }
}
