package Acmicpc.zero.nine;

import java.io.*;

public class Acmicpc09252 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String one = br.readLine();
    String another = br.readLine();

    char[] oneArray = one.toCharArray();
    char[] anotherArray = another.toCharArray();
    int[][] dp = new int[one.length() + 1][another.length() + 1];

    for (int i = 1; i <= one.length(); i++) {
      for (int j = 1; j <= another.length(); j++) {
        boolean same = oneArray[i - 1] == anotherArray[j - 1];

        if (same) dp[i][j] = dp[i - 1][j - 1] + 1;
        else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
      }
    }

    StringBuilder sb = new StringBuilder();

    int[] next = {one.length(), another.length()};
    while (next[0] > 0 && next[1] > 0) {
      if (oneArray[next[0] - 1] == anotherArray[next[1] - 1]) {
        sb.insert(0, oneArray[next[0] - 1]);
        next[0]--;
        next[1]--;
      }
      else {
        if (dp[next[0] - 1][next[1]] > dp[next[0]][next[1] - 1]) next[0]--;
        else next[1]--;
      }
    }

    bw.write(sb.length() + "\n");
    if (sb.length() != 0) bw.write(sb.toString());
    bw.flush();
    bw.close();
    br.close();
  }
}
