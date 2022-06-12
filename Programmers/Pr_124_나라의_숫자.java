package Programmers;

public class Pr_124_나라의_숫자 {
  public static String solution(int n) {
    String translated = solve(n);

    return translated;
  }

  public static String solve(int n) {
    StringBuilder sb = new StringBuilder();

    while (n > 0) {
      if (n % 3 == 0) {
        sb.append(4);
        n /= 3;
        n--;
      } else {

        sb.append(n % 3);
        n /= 3;
      }
    }

    return sb.reverse().toString();
  }
}
