package Programmers;

public class 단체사진찍기 {
  static String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};
  static String[] conditions;
  static boolean[] isVisited;
  static int count;

  public static int solution(int n, String[] data) {
    isVisited = new boolean[friends.length];
    conditions = data;
    count = 0;

    solve("");

    return count;
  }

  public static void solve(String candidate) {
    if (candidate.length() == 8 && isValid(candidate)) {
      count++;
    }

    for (int i = 0; i < friends.length; i++) {
      if (isVisited[i]) {
        continue;
      }
      isVisited[i] = true;
      solve(candidate + friends[i]);
      isVisited[i] = false;
    }
  }

  public static boolean isValid(String candidate) {
    boolean result = true;

    for (String condition : conditions) {
      char sign = condition.charAt(3);
      int val = Character.digit(condition.charAt(4), 10) + 1;
      int gap = Math.abs(candidate.indexOf(condition.codePointAt(0)) - candidate.indexOf(condition.codePointAt(2)));

      switch (sign) {
        case '=':
          result &= gap == val ? true : false;
          break;

        case '>':
          result &= gap > val ? true : false;
          break;

        case '<':
          result &= gap < val ? true : false;
          break;
      }
      if (!result) {
        return false;
      }
    }

    return true;
  }
}
