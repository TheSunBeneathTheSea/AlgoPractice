package Acmicpc.zero;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Acmicpc02638 {
  static int[][] map;
  static boolean[][] isVisited;
  static int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  static int count = 0;
  static boolean cheesesLeft = true;

  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      String[] nm = br.readLine().split(" ");

      int n = Integer.parseInt(nm[0]);
      int m = Integer.parseInt(nm[1]);

      map = new int[n][m];
      isVisited = new boolean[n][m];

      for (int i = 0; i < n; i++) {
        StringTokenizer token = new StringTokenizer(br.readLine());

        for (int j = 0; j < m; j++) {
          map[i][j] = Integer.parseInt(token.nextToken());
        }
      }

      while (cheesesLeft) {
        findOuterSpace();
        checkCheese();
        meltCheese();
        isVisited = new boolean[n][m];
      }

      bw.write((count - 1) + "");
      bw.flush();
      br.close();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void findOuterSpace() {
    ArrayDeque<Integer[]> queue = new ArrayDeque<>();

    isVisited[0][0] = true;
    map[0][0] = 9;
    queue.add(new Integer[]{0, 0});

    while (!queue.isEmpty()) {
      Integer[] cur = queue.poll();

      for (int i = 0; i < 4; i++) {
        Integer[] next = new Integer[]{cur[0] + moves[i][0], cur[1] + moves[i][1]};

        if (isValidMove(next) && !isVisited[next[0]][next[1]] && map[next[0]][next[1]] != 1) {
          isVisited[next[0]][next[1]] = true;
          map[next[0]][next[1]] = 9;
          queue.add(next);
        }
      }
    }
  }

  static void checkCheese() {
    cheesesLeft = false;
    for (int i = 1; i < map.length; i++) {
      for (int j = 1; j < map[0].length; j++) {
        if (map[i][j] == 1 && willMelt(i, j)) {
          map[i][j] = 2;
          cheesesLeft = true;
        }
      }
    }
  }

  static void meltCheese() {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == 2) {
          map[i][j] = 9;
        }
      }
    }
    count++;
  }

  static boolean isValidMove(Integer[] next) {
    return next[0] >= 0 && next[0] <= map.length - 1 && next[1] >= 0 && next[1] <= map[0].length - 1;
  }

  static boolean willMelt(int row, int col) {
    int facingOuterSpaces = 0;

    for (int i = 0; i < 4; i++) {
      Integer[] next = new Integer[]{row + moves[i][0], col + moves[i][1]};

      if (isValidMove(next) && map[next[0]][next[1]] == 9) {
        facingOuterSpaces++;
      }
    }

    return facingOuterSpaces >= 2;
  }
}
