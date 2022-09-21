package Acmicpc.zero.one;

import java.io.*;

public class Acmicpc01016 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] input = br.readLine().split(" ");

    long min = Long.parseLong(input[0]);
    long max = Long.parseLong(input[1]);

    boolean[] sieve = new boolean[(int)(max - min) + 1];

    long cur = 2;
    long square = 0;
    while (max >= square) {
      square = cur * cur;
      cur++;

      long start = min % square == 0 ? min / square : min / square + 1;
      long limit = (max / square);
      for (long i = start; i <= limit; i++) {
        sieve[(int)(square * i - min)] = true;
      }

    }

    int count = 0;
    for (boolean b : sieve) {
      if (b) continue;

      count++;
    }

    System.out.println(count);
  }
}
