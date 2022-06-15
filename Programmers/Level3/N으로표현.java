package Programmers.Level3;

import java.util.HashSet;
import java.util.Set;

public class N으로표현 {
  static int answer = -1;

  public static int solution(int N, int number) {
    Set<Integer>[] set = new HashSet[9];
    int n = 0;
    for (int i = 1; i < 9; i++) {
      n = (n * 10) + N;
      set[i] = new HashSet<>();
      set[i].add(n);
    }
    for (int i = 1; i <= 8; i++) {
      for (int j = 1; j < i; j++) {
        for (Integer a : set[j]) {
          for (Integer b : set[i - j]) {
            set[i].add(a + b);
            set[i].add(a - b);
            set[i].add(a * b);

            if (b != 0) {
              set[i].add(a / b);
            }
            if (a != 0) {
              set[i].add(b / a);
            }
          }
        }
      }
      if (set[i].contains(number)) {
        answer = i;
        return answer;
      }
    }
    return -1;
  }
}
