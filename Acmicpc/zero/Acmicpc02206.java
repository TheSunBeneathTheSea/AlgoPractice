package Acmicpc.zero;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Acmicpc02206 {
  static char[][] map;
  static boolean[][][] isVisited;
  static int[][] moves = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  static int distance = 1000001;

  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      StringTokenizer token = new StringTokenizer(br.readLine());

      int n = Integer.parseInt(token.nextToken());
      int m = Integer.parseInt(token.nextToken());

      map = new char[n][m];
      isVisited = new boolean[n][m][2];

      for (int i = 0; i < n; i++) {
        String row = br.readLine();

        for (int j = 0; j < m; j++) {
          map[i][j] = row.charAt(j);
        }
      }

      br.close();

      isVisited[0][0] = new boolean[]{true, true};
      ArrayDeque<Integer[]> queue = new ArrayDeque<>();

      // x 좌표, y 좌표, 지나온 거리, 지나간 벽 갯수
      queue.add(new Integer[]{0, 0, 1, 0});

      while (!queue.isEmpty()) {
        Integer[] cur = queue.poll();

        if (cur[3] > 1) {
          continue;
        }

        if (cur[0] == map.length - 1 && cur[1] == map[0].length - 1 && cur[3] <= 1) {
          distance = Math.min(distance, cur[2]);
          break;
        }

        int[] next;
        for (int i = 0; i < 4; i++) {
          next = new int[]{cur[0] + moves[i][0], cur[1] + moves[i][1]};
          if (!isValidMove(next)) {
            continue;
          }

          if (isWall(next)) {
            if (!isVisited[next[0]][next[1]][cur[3]]) {
              queue.add(new Integer[]{next[0], next[1], cur[2] + 1, cur[3] + 1});
              isVisited[next[0]][next[1]][cur[3]] = true;
            }
          } else {
            if (!isVisited[next[0]][next[1]][cur[3]]) {
              queue.add(new Integer[]{next[0], next[1], cur[2] + 1, cur[3]});
              isVisited[next[0]][next[1]][cur[3]] = true;
            }
          }
        }
      }

      int result = distance == 1000001 ? -1 : distance;

      bw.write(result + "");
      bw.flush();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static boolean isValidMove(int[] next) {
    return next[0] >= 0 && next[1] >= 0
            && next[0] < map.length && next[1] < map[0].length;
  }

  static boolean isWall(int[] next) {
    return map[next[0]][next[1]] == '1';
  }
}
