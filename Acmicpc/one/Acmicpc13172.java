package Acmicpc.one;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc13172 {
  static final long MODULAR = 1000000007;

  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int m = Integer.parseInt(br.readLine());

      long sum = 0;

      while (m-- > 0) {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        long n = Long.parseLong(tokenizer.nextToken());
        long s = Long.parseLong(tokenizer.nextToken());

        long moduloInverse = findExponential(n, MODULAR - 2);

        sum += (moduloInverse * s) % MODULAR;
        sum = sum % MODULAR;
      }

      bw.write(sum + "");
      bw.flush();
      bw.close();
      br.close();
    } catch (IOException e) {
    }
  }

  public static long findExponential(long num, long exp) {
    if (exp == 1) {
      return num;
    }
    long divided = findExponential(num, exp / 2);

    if (exp % 2 == 0) {
      return ((divided * divided) % MODULAR);
    } else {
      return (((divided * divided) % MODULAR) * num) % MODULAR;
    }
  }
}
