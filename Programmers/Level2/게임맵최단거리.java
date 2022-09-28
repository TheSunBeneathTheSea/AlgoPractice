package Programmers.Level2;

import java.util.ArrayDeque;

public class 게임맵최단거리 {
  static int[][] map;
  static int[] rowMoves = {1, 0, -1, 0};
  static int[] colMoves = {0, 1, 0, -1};
  static int width, height;
  static boolean[][] isVisited;

  public static int solution(int[][] maps) {
    map = maps;
    height = maps.length;
    width = maps[0].length;
    isVisited = new boolean[height][width];
    int answer = -1;

    ArrayDeque<Location> deque = new ArrayDeque<>();

    isVisited[0][0] = true;
    deque.add(new Location(0, 0, 1));
    while(!deque.isEmpty()) {
      Location cur = deque.poll();

      if (cur.row == height - 1 && cur.col == width - 1) {
        answer = cur.time;
        break;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = cur.row + rowMoves[i];
        int nextCol = cur.col + colMoves[i];

        if (!isValidMove(nextRow, nextCol)) continue;

        isVisited[nextRow][nextCol] = true;
        deque.add(new Location(nextRow, nextCol, cur.time + 1));
      }
    }

    return answer;
  }

  public static boolean isValidMove(int nextRow, int nextCol) {
    return nextRow >= 0 && nextRow < height && nextCol >= 0 && nextCol < width
            && !isVisited[nextRow][nextCol] && map[nextRow][nextCol] != 0;
  }

  public static void main(String[] args) {
    int[][] maps = {{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,0}, {0,0,0,0,1}};

    int ans = solution(maps);
  }

  static class Location {
    int row, col, time;

    Location(int row, int col, int time) {
      this.row = row;
      this.col = col;
      this.time = time;
    }
  }
}
