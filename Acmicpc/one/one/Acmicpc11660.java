package Acmicpc.one.one;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Acmicpc11660 {
  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      StringTokenizer token = new StringTokenizer(br.readLine());

      int n = Integer.parseInt(token.nextToken());
      int m = Integer.parseInt(token.nextToken());

      int[][] sums = new int[n + 1][n + 1];

      for (int i = 1; i <= n; i++) {
        token = new StringTokenizer(br.readLine());
        int sum = 0;

        for (int j = 1; j <= n; j++) {
          sum += Integer.parseInt(token.nextToken());
          sums[i][j] = sum;
        }
      }

      while (m-- > 0) {
        int[] instruction = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int value = 0;

        for (int i = instruction[0]; i <= instruction[2]; i++) {
          value += sums[i][instruction[3]] - sums[i][instruction[1] - 1];
        }

        bw.write(value + "\n");
      }

      br.close();
      bw.flush();
      bw.close();

    } catch (IOException e) {
    }
  }
}
