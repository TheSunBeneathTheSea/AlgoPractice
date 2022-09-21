package Acmicpc.zero.one;

import java.io.*;

public class Acmicpc01300 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());

    int result = 0;
    int start = 1;
    int end = K;
    while (start <= end) {
      int mid = (start + end) / 2;
      int count = 0;

      for (int i = 1; i <= N; i++) {
        count += Math.min(mid / i, N);
      }

      if (count < K) {
        start = mid + 1;
      } else {
        end = mid - 1;
        result = mid;
      }
    }

    System.out.println(result);
  }
}
