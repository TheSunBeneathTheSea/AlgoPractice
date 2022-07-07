package Acmicpc.one;

import java.io.*;
import java.util.Arrays;

public class Acmicpc11404 {
  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int cities = Integer.parseInt(br.readLine());
      int buses = Integer.parseInt(br.readLine());

      int[][] adjArray = new int[cities + 1][cities + 1];

      for (int i = 1; i <= cities; i++) {
        for (int j = 1; j <= cities; j++) {
          if (i == j) {
            adjArray[i][j] = 0;
            continue;
          }
          adjArray[i][j] = 10000001;
        }
      }

      while (buses-- > 0) {
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int oldValue = adjArray[input[0]][input[1]];

        if (oldValue > input[2]) {
          adjArray[input[0]][input[1]] = input[2];
        }
      }
      for (int i = 1; i <= cities; i++) {
        for (int j = 1; j <= cities; j++) {
          for (int k = 1; k <= cities; k++) {
            if (adjArray[j][k] > adjArray[j][i] + adjArray[i][k]) {
              adjArray[j][k] = adjArray[j][i] + adjArray[i][k];
            }
          }
        }
      }

      for (int i = 1; i <= cities; i++) {
        for (int j = 1; j <= cities; j++) {
          int value = adjArray[i][j];
          if (value == 10000001) {
            value = 0;
          }
          bw.write(value + " ");
        }
        bw.write("\n");
      }

      bw.flush();
      bw.close();
      br.close();

    } catch (IOException e) {
    }
  }
}
