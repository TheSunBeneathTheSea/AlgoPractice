package Acmicpc.one.four;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Acmicpc14502 {
  static int[][] map;
  static ArrayList<Integer[]> virus = new ArrayList<>();
  static int maxSafeSpace = 0;
  static int width, height;
  static int[] xMove = {1, -1, 0, 0};
  static int[] yMove = {0, 0, 1, -1};

  public static void main(String[] args) {
    try {
      //    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      StringTokenizer tokenizer = new StringTokenizer(br.readLine());

      height = Integer.parseInt(tokenizer.nextToken());
      width = Integer.parseInt(tokenizer.nextToken());

      map = new int[height][width];
      for (int i = 0; i < height; i++) {
        tokenizer = new StringTokenizer(br.readLine());
        for (int j = 0; j < width; j++) {
          map[i][j] = Integer.parseInt(tokenizer.nextToken());
          if (map[i][j] == 2) {
            virus.add(new Integer[]{i, j});
          }
        }
      }

      findCombination(0);

      bw.write(maxSafeSpace + "");
      bw.flush();
      bw.close();
      br.close();

    } catch (IOException e) {
    }
  }

  public static void findCombination(int wallCount) {
    if (wallCount == 3) {
      maxSafeSpace = Math.max(maxSafeSpace, getCurrentIsolatedSpace());
      return;
    }

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (map[i][j] == 0) {
          map[i][j] = 1;
          findCombination(wallCount + 1);
          map[i][j] = 0;
        }
      }
    }
  }

  public static int getCurrentIsolatedSpace() {
    //spread virus

    ArrayDeque<Integer[]> queue = new ArrayDeque<>(virus);

    while (!queue.isEmpty()) {
      Integer[] cur = queue.poll();

      for (int i = 0; i < 4; i++) {
        Integer[] next = new Integer[]{cur[0] + yMove[i], cur[1] + xMove[i]};

        if (next[0] < 0 || next[0] >= height || next[1] < 0 || next[1] >= width || map[next[0]][next[1]] != 0) continue;
        map[next[0]][next[1]] = 3;
        queue.add(next);
      }
    }

    //count safe space && restore map
    int safeSpace = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (map[i][j] == 0) {
          safeSpace++;
        } else if (map[i][j] == 3) {
          map[i][j] = 0;
        }
      }
    }

    return safeSpace;
  }
}
