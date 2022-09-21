package Acmicpc.one.seven;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc17070 {
  static int[][] map;
  static Pipe[][] dp;

  public static void main(String[] args) {
    try {
      //    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int n = Integer.parseInt(br.readLine());
      map = new int[n + 1][n + 1];
      dp = new Pipe[n + 1][n + 1];

      for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
          dp[i][j] = new Pipe();
        }
      }
      dp[1][2].horizontal++;

      StringTokenizer tokenizer;

      for (int i = 1; i <= n; i++) {
        tokenizer = new StringTokenizer(br.readLine());

        for (int j = 1; j <= n; j++) {
          map[i][j] = Integer.parseInt(tokenizer.nextToken());
        }
      }

      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
          if (map[i][j] == 1) continue;

          dp[i][j].horizontal += dp[i][j - 1].horizontal + dp[i][j - 1].diagonal;
          dp[i][j].vertical += dp[i - 1][j].vertical + dp[i - 1][j].diagonal;

          if (map[i - 1][j] == 1 || map[i][j - 1] == 1) continue;
          dp[i][j].diagonal += dp[i - 1][j - 1].vertical + dp[i - 1][j - 1].horizontal + dp[i - 1][j - 1].diagonal;
        }
      }

      System.out.println(dp[n][n].vertical + dp[n][n].horizontal + dp[n][n].diagonal);

    } catch (IOException e) {
    }
  }

  static class Pipe {
    int vertical, horizontal, diagonal;

    public Pipe() {
      this.vertical = 0;
      this.horizontal = 0;
      this.diagonal = 0;
    }
  }
}
