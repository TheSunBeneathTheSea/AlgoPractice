package Programmers.Level2;

public class 짝지어제거하기 {
  public static int solution(String s) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      sb.append(s.charAt(i));

      if (sb.length() >= 2 && sb.charAt(sb.length() - 1) == sb.charAt(sb.length() - 2)) {
        sb.delete(sb.length() - 2, sb.length());
      }
    }

    return sb.isEmpty() ? 1 : 0;
  }
}
