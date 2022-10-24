package Acmicpc.zero.one;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc01256 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //BufferedReader br = new BufferedReader(new FileReader("input"));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(tokenizer.nextToken());
    int M = Integer.parseInt(tokenizer.nextToken());
    int K = Integer.parseInt(tokenizer.nextToken());

    long[][] dp = new long[N + M + 1][N + M + 1];

    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        if (j == 0) dp[i][j] = 1;
        else if (i == j) dp[i][j] = 1;
      }
    }
    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length - 1; j++) {
        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
        if (dp[i][j] > 10_0000_0000) dp[i][j] = 10_0000_0001;
      }
    }

    if (dp[N + M][M] < K) {
      System.out.println("-1");
      return;
    }

    StringBuilder sb = new StringBuilder();
    while (N != 0 || M != 0) {
      if (dp[N + M - 1][M] >= K) {
        N--;
        sb.append('a');
        continue;
      }
      K -= dp[N + M - 1][M--];
      sb.append('z');
    }

    System.out.println(sb.toString());
  }
}
