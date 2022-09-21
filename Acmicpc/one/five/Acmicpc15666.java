package Acmicpc.one.five;

import java.util.ArrayList;
import java.util.HashSet;

public class Acmicpc15666 {
  public static HashSet<String> answers = new HashSet<>();
  public static StringBuilder sb = new StringBuilder();

  public static void solve(String values, int selected, int m, int cur, ArrayList<Integer> nums) {
    if (selected == m) {
      String answer = values.trim();

      if (!answers.contains(answer)) {
        sb.append(answer + "\n");
        answers.add(answer);
      }
      return;
    }

    for (int i = cur; i < nums.size(); i++) {
      solve(values + " " + nums.get(i), selected + 1, m, i, nums);
    }
  }
}
