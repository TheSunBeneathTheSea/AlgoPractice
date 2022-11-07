package Acmicpc.one.three;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc13398 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));

    int N = Integer.parseInt(br.readLine());

    int[] numbers = new int[N + 1];

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      numbers[i] = Integer.parseInt(tokenizer.nextToken());
    }

    int[][] dp = new int[N + 1][2];
    dp[0] = new int[] {-1001, -1001};
    for (int i = 1; i <= N; i++) {
      dp[i][0] = Math.max(0, dp[i - 1][0]) + numbers[i];
      dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + numbers[i]);
    }

    int max = -1001;
    for (int[] sums : dp) {
      for (int sum : sums) {
        if (max < sum) max = sum;
      }
    }

    System.out.println(max);
  }
}
