package Acmicpc.zero.nine;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Acmicpc09465 {

  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int t = Integer.parseInt(br.readLine());

      while (t-- > 0) {
        int n = Integer.parseInt(br.readLine());

        int[][] stickers = new int[2][n];

        for (int i = 0; i < 2; i++) {
          String[] token = br.readLine().split(" ");

          for (int j = 0; j < n; j++) {
            stickers[i][j] = Integer.parseInt(token[j]);
          }
        }

        int[][] dp = new int[2][n + 1];

        dp[0][1] = stickers[0][0];
        dp[1][1] = stickers[1][0];

        for (int i = 2; i <= n; i++) {
          dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + stickers[0][i - 1];
          dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + stickers[1][i - 1];
        }

        int max = Math.max(dp[0][n], dp[1][n]);

        bw.write(max + "\n");
      }

      bw.flush();
      br.close();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
