package Acmicpc.zero;

import java.io.*;

public class Acmicpc02023 {
  static int N;
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    dfs(2);
    dfs(3);
    dfs(5);
    dfs(7);
  }
  public static void dfs(int n) {
    if(Integer.toString(n).length() == N) {
      System.out.println(n);
      return;
    }

    for (int i = 1; i < 10; i += 2) {
      int next = n * 10 + i;
      if (!isPrime(next)) continue;

      dfs(next);
    }
  }

  public static boolean isPrime(int n) {
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if(n % i == 0) return false;
    }

    return true;
  }
}
