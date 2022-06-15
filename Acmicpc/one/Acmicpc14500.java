package Acmicpc.one;

public class Acmicpc14500 {
  static int[][] moves = {
          {0, 1}, {1, 0}, {-1, 0}, {0, -1}
  };
  static int answer = 0;
  static boolean[][] isVisited;
  static int[][] map;

  public static int solve(int[][] input) {
    map = input;
    isVisited = new boolean[map.length][map[0].length];

    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        isVisited[i][j] = true;
        dfs(i, j, map[i][j], 1);
        isVisited[i][j] = false;
        exception(i, j);
      }
    }

    return answer;
  }

  public static void dfs(int n, int m, int sum, int count) {
    if (count == 4) {
      if (answer < sum) {
        answer = sum;
      }
      return;
    }

    for (int k = 0; k < moves.length; k++) {
      int[] next = {n + moves[k][0], m + moves[k][1]};

      if (next[0] >= map.length || next[1] >= map[0].length
              || next[0] < 0 || next[1] < 0 || isVisited[next[0]][next[1]]) {
        continue;
      }

      isVisited[next[0]][next[1]] = true;
      dfs(next[0], next[1], sum + map[next[0]][next[1]], count + 1);
      isVisited[next[0]][next[1]] = false;
    }
  }

  public static void exception(int n, int m) {
    int branch = 4;
    int min = 1001;
    int sum = map[n][m];
    for (int i = 0; i < 4; i++) {
      int[] next = {n + moves[i][0], m + moves[i][1]};

      if (branch <= 2)
        return;

      if (next[0] >= map.length || next[1] >= map[0].length
              || next[0] < 0 || next[1] < 0) {
        branch--;
        continue;
      }
      min = Math.min(min, map[next[0]][next[1]]);
      sum = sum + map[next[0]][next[1]];
    }
    if (branch == 4) {
      sum = sum - min;
    }
    answer = Math.max(answer, sum);
  }
}
