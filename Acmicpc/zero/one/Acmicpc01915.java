package Acmicpc.zero.one;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc01915 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(tokenizer.nextToken());
    int M = Integer.parseInt(tokenizer.nextToken());

    int[][] map = new int[N + 1][M + 1];

    for (int i = 1; i <= N; i++) {
      char[] line = br.readLine().toCharArray();
      for (int j = 1; j <= M; j++) {
        map[i][j] = line[j - 1] - '0';
      }
    }

    int[][] dp = new int[N + 1][M + 1];
    int max = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        if (map[i][j] == 0) {
          dp[i][j] = 0;
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
        }
        if (max < dp[i][j]) max = dp[i][j];
      }
    }

    System.out.println(max * max);
  }
}
