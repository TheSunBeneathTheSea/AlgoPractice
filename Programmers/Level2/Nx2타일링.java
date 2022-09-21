package Programmers.Level2;

public class Nx2타일링 {
  public int solution(int n) {
    int[] memo = new int[n + 1];

    memo[0] = 1;
    memo[1] = 1;

    for (int i = 2; i <= n; i++) {
      memo[i] = (memo[i - 1] + memo[i - 2]) % 1000000007;
    }

    return memo[n];
  }
}
