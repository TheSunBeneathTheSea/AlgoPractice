package Acmicpc.zero.one;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc01722 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    long[] factorial = new long[21];
    factorial[0] = 1;
    for (int i = 1; i < factorial.length; i++) {
      factorial[i] = factorial[i - 1] * i;
    }
    boolean[] isVisited = new boolean[21];

    int N = Integer.parseInt(br.readLine());
    StringTokenizer tokenizer = new StringTokenizer(br.readLine());
    int[] p = new int[N + 1];

    int command = Integer.parseInt(tokenizer.nextToken());

    if (command == 1) {
      long k = Long.parseLong(tokenizer.nextToken());
      for (int i = 1; i <= N; i++) {
        int count = 1;
        for (int j = 1; j <= N; j++) {
          if (isVisited[j]) continue;

          if (k <= count * factorial[N - i]) {
            k -= ((count - 1) * factorial[N - i]);
            p[i] = j;
            isVisited[j] = true;
            break;
          }
          count++;
        }
      }

      for (int i = 1; i <= N; i++) {
        bw.write(p[i] + " ");
      }
    } else {
      long k = 1;
      for (int i = 1; i <= N; i++) {
        p[i] = Integer.parseInt(tokenizer.nextToken());
        long count = 0;
        for (int j = 1; j < p[i]; j++) {
          if (!isVisited[j]) {
            count++;
          }
        }
        k += count * factorial[N - i];
        isVisited[p[i]] = true;
      }

      bw.write(k + "");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
