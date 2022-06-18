package Programmers.Level2;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 수식최대화 {
  static long max = 0;
  static String operator = "*+-";
  static boolean[] isVisited = new boolean[3];
  static ArrayList<Long> orgNums;
  static ArrayList<Character> orgOps;

  public static long solution(String expression) {
    Matcher m = Pattern.compile("\\d+").matcher(expression);

    orgNums = new ArrayList<>();
    while (m.find()) {
      orgNums.add(Long.parseLong(m.group()));
    }

    m = Pattern.compile("\\D").matcher(expression);

    orgOps = new ArrayList<>();
    while (m.find()) {
      orgOps.add(m.group().charAt(0));
    }

    solve("");

    return max;
  }

  static void solve(String priority) {
    if (priority.length() == 3) {
      calc(priority);
      return;
    }

    for (int i = 0; i < 3; i++) {
      if (!isVisited[i]) {
        isVisited[i] = true;
        solve(priority + operator.charAt(i));
        isVisited[i] = false;
      }
    }
  }

  static void calc(String priority) {
    ArrayList<Long> nums = new ArrayList<>(orgNums);
    ArrayList<Character> ops = new ArrayList<>(orgOps);

    for (int i = 0; i < priority.length(); i++) {
      char operator = priority.charAt(i);

      for (int j = 0; j < ops.size(); j++) {
        if (operator == ops.get(j)) {
          long a = nums.get(j);
          long b = nums.get(j + 1);

          long result = 0;
          switch (operator) {
            case '*':
              result = a * b;
              break;
            case '+':
              result = a + b;
              break;
            case '-':
              result = a - b;
              break;
          }

          nums.set(j, result);
          nums.remove(j + 1);
          ops.remove(j);
          j--;
        }
      }
    }

    max = Math.max(max, Math.abs(nums.get(0)));
  }
}
