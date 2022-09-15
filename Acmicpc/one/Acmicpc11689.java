package Acmicpc.one;

import java.io.*;

public class Acmicpc11689 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long N = Long.parseLong(br.readLine());

    long value = N;
    for (int i = 2; i <= Math.sqrt(N); i++) {
      if (N % i != 0) continue;
      value = value - (value / i);

      while (N % i == 0) {
        N /= i;
      }
    }
    if (N > 1) {
      value = value - (value / N);
    }

    System.out.println(value);
  }
}
