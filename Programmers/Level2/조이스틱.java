package Programmers.Level2;

public class 조이스틱 {
  static int target = 0;
  static int minMove = 20;
  static int length = 0;

  public int solution(String name) {
    target = 0;
    minMove = 20;
    length = name.length();
    int answer = 0;
    int idx = 0;
    boolean[] isVisited = new boolean[length];

    for (char c : name.toCharArray()) {
      if (c != 'A') {
        answer += getCostOfChangingAlphabet(c);
        target++;
      } else {
        isVisited[idx] = true;
      }
      idx++;
    }

    if (name.charAt(0) != 'A') {
      target--;
      isVisited[0] = true;
    }

    dfs(name, isVisited, 0, 0, 0);

    return answer + minMove;
  }

  public void dfs(String name, boolean[] isVisited, int curIdx, int distance, int depth) {
    if (depth == target) {
      System.out.println(distance + " " + minMove);
      minMove = Math.min(minMove, distance);
      return;
    }

    int dist = 0;
    int nextIdx = curIdx;
    while (isVisited[nextIdx]) {
      nextIdx++;
      dist++;

      if (nextIdx >= length) nextIdx %= length;
    }
    isVisited[nextIdx] = true;

    dfs(name, isVisited, nextIdx, distance + dist, depth + 1);

    isVisited[nextIdx] = false;

    nextIdx = curIdx;
    dist = 0;
    while (isVisited[nextIdx]) {
      nextIdx--;
      dist++;

      if (nextIdx < 0) nextIdx += length;
    }

    isVisited[nextIdx] = true;

    dfs(name, isVisited, nextIdx, distance + dist, depth + 1);

    isVisited[nextIdx] = false;
  }

  public int getCostOfChangingAlphabet(char target) {
    return target - 'A' < 13 ? target - 'A' : 26 + 'A' - target;
  }
}
