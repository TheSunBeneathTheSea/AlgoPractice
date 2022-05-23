package Acmicpc;

import java.util.ArrayList;

public class Acmicpc15657 {
  public static void solve(String result, int selected, int m, ArrayList<Integer> nums, int cur) {
    if (selected == m) {
      System.out.println(result.trim());
      return;
    }

    for (int i = cur; i < nums.size(); i++) {
      solve(result + " " + nums.get(i), selected + 1, m, nums, i);
    }
  }
}
