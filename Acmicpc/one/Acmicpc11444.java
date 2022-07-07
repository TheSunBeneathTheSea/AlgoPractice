package Acmicpc.one;

import java.io.*;

public class Acmicpc11444 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      long n = Long.parseLong(br.readLine());
      long[][] result;
      if (n == 1) {
        result = new long[][]{{1}};
      } else {
        result = matSquare(new long[][]{{1, 1}, {1, 0}}, n - 1);
      }
      
      bw.write(result[0][0] + "");
      bw.flush();
      bw.close();
      br.close();
    } catch (IOException e) {

    }
  }

  public static long[][] matSquare(long[][] matrix, long times) {
    if (times == 1) {
      return matrix;
    }
    if (times % 2 == 1) {
      return matTimes(matSquare(matrix, times - 1), matrix);
    } else {
      long[][] half = matSquare(matrix, times / 2);
      return matTimes(half, half);
    }
  }

  public static long[][] matTimes(long[][] matA, long[][] matB) {
    long[][] result = new long[matA.length][matA[0].length];

    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result.length; j++) {
        int sum = 0;
        for (int k = 0; k < result.length; k++) {
          sum += ((matA[i][k] % 1000000007) * (matB[k][j] % 1000000007)) % 1000000007;
        }
        result[i][j] = sum % 1000000007;
      }
    }

    return result;
  }
}
