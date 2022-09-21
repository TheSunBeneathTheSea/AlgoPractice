package Acmicpc.one.five;

import java.util.ArrayList;
import java.util.HashSet;

public class Acmicpc15663 {
  public static HashSet<String> answers = new HashSet<>();

  public static void solve(String values, int selected, int m, ArrayList<Integer> nums, boolean[] isSelected) {
    if (selected == m) {
      String answer = values.trim();

      if (!answers.contains(answer)) {
        System.out.println(answer);
        answers.add(answer);
      }
      return;
    }

    for (int i = 0; i < nums.size(); i++) {
      if (!isSelected[i]) {
        isSelected[i] = true;
        solve(values + " " + nums.get(i), selected + 1, m, nums, isSelected);
        isSelected[i] = false;
      }
    }
  }
}
