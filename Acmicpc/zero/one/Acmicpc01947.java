package Acmicpc.zero.one;

import java.io.*;

public class Acmicpc01947 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //BufferedReader br = new BufferedReader(new FileReader("input"));

    int N = Integer.parseInt(br.readLine());
    if (N < 3) {
      System.out.println(N - 1);
      return;
    }
    long[] present = new long[N + 1];

    present[2] = 1;

    for (int i = 3; i < present.length; i++) {
      present[i] = ((i - 1) * (present[i - 1] + present[i - 2])) % 1_000_000_000;
    }

    System.out.println(present[N]);
  }
}
