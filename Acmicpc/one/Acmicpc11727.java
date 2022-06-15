package Acmicpc.one;

/*

slowlight50@gmail.com

https://github.com/TheSunBeneathTheSea/AlgoPractice


***문제

2×n 직사각형을 1×2, 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.

아래 그림은 2×17 직사각형을 채운 한가지 예이다.

***입력

첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)

***출력

첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.



*/

//DP
//n칸을 채우는 방법의 수는 n-2칸을 채우는 방법의 수 * 2 + n-1칸을 채우는 방법의 수
public class Acmicpc11727 {
  static int[] dp = new int[1001];

  public static int solve(int n) {
    dp[1] = 1;
    dp[2] = 3;
    if (n <= 2) {
      return dp[n];
    }

    for (int i = 3; i <= n; i++) {
      dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007;
    }

    return dp[n];
  }
}
