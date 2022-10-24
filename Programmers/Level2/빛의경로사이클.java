package Programmers.Level2;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class 빛의경로사이클 {
  static final int UP = 0;
  static final int RIGHT = 1;
  static final int DOWN = 2;
  static final int LEFT = 3;
  static final int TURN_RIGHT = 1;
  static final int TURN_LEFT = 2;

  public int[] solution(String[] grid) {
    Light.edges = new int[grid.length][grid[0].length()][4];
    Light.nodes = new int[grid.length][grid[0].length()];

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length(); j++) {
        if (grid[i].charAt(j) == 'R') {
          Light.nodes[i][j] = TURN_RIGHT;
        } else if (grid[i].charAt(j) == 'L') {
          Light.nodes[i][j] = TURN_LEFT;
        }
      }
    }
    int remainEdgesLength = grid.length * grid[0].length() * 4;
    ArrayList<Integer> cycles = new ArrayList<>();
    ArrayDeque<Light> queue = new ArrayDeque<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length(); j++) {
        if (remainEdgesLength == 0) break;

        for (int k = 0; k < 4; k++) {
          queue.add(new Light(i, j, k, 0));

          while (!queue.isEmpty()) {
            Light cur = queue.poll();

            if (!cur.canMove()) {
              if (cur.length != 0 &&cur.isInitialState()) {
                cycles.add(cur.length);
                remainEdgesLength -= cur.length;
              }
              continue;
            }

            cur.move();
            queue.add(cur);
          }
        }
      }
    }

    cycles.sort(Integer::compare);
    int[] answer = new int[cycles.size()];
    for (int i = 0; i < cycles.size(); i++) {
      answer[i] = cycles.get(i);
    }

    return answer;
  }

  static class Light {
    static int[][][] edges;
    static int[][] nodes;
    int[] initialState;
    int curRow, curCol;
    int direction;
    int length;

    Light(int startRow, int startCol, int startDirection, int length) {
      this.initialState = new int[]{startRow, startCol, startDirection};
      this.curRow = startRow;
      this.curCol = startCol;
      this.direction = startDirection;
      this.length = length;
    }

    boolean canMove() {
      return edges[curRow][curCol][direction] == 0;
    }

    boolean isInitialState() {
      return curRow == initialState[0] && curCol == initialState[1] && direction == initialState[2];
    }

    void move() {
      edges[curRow][curCol][direction] = 1;

      switch (direction) {
        case UP: {
          if (curRow == 0) curRow = edges.length;
          curRow--;
          break;
        }
        case RIGHT: {
          if (curCol == edges[0].length - 1) curCol = -1;
          curCol++;
          break;
        }
        case DOWN: {
          if (curRow == edges.length - 1) curRow = -1;
          curRow++;
          break;
        }
        case LEFT: {
          if (curCol == 0) curCol = edges[0].length;
          curCol--;
          break;
        }
      }

      length++;
      if (nodes[curRow][curCol] == TURN_RIGHT) direction++;
      else if (nodes[curRow][curCol] == TURN_LEFT) direction--;

      direction += 4;
      direction %= 4;
    }
  }
}
