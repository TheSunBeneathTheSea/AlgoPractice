package Acmicpc.zero;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc02096 {
  public static void main(String[] args) {
    try {
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int n = Integer.parseInt(br.readLine());

      int[][] dp = new int[3][2];
      StringTokenizer token = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(token.nextToken());
      int b = Integer.parseInt(token.nextToken());
      int c = Integer.parseInt(token.nextToken());

      dp[0][0] = a;
      dp[0][1] = a;
      dp[1][0] = b;
      dp[1][1] = b;
      dp[2][0] = c;
      dp[2][1] = c;

      while (n-- > 1) {
        int aMin = dp[0][0];
        int aMax = dp[0][1];
        int bMin = dp[1][0];
        int bMax = dp[1][1];
        int cMin = dp[2][0];
        int cMax = dp[2][1];

        token = new StringTokenizer(br.readLine());

        a = Integer.parseInt(token.nextToken());
        b = Integer.parseInt(token.nextToken());
        c = Integer.parseInt(token.nextToken());

        dp[0][0] = Math.min(aMin, bMin) + a;
        dp[0][1] = Math.max(aMax, bMax) + a;
        dp[1][0] = Math.min(Math.min(aMin, bMin), cMin) + b;
        dp[1][1] = Math.max(Math.max(aMax, bMax), cMax) + b;
        dp[2][0] = Math.min(bMin, cMin) + c;
        dp[2][1] = Math.max(bMax, cMax) + c;
      }

      int min = Math.min(Math.min(dp[0][0], dp[1][0]), dp[2][0]);
      int max = Math.max(Math.max(dp[0][1], dp[1][1]), dp[2][1]);

      bw.write(max + " " + min);
      bw.flush();
      br.close();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
