package Acmicpc.one;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Acmicpc17144 {
  static int[][] map;
  static int airPurifierRow;
  static int remainDust;
  static HashMap<String, Integer> dusts = new HashMap<>();

  public static void main(String[] args) {
    try {
      //    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      String[] input = br.readLine().split(" ");
      int r = Integer.parseInt(input[0]);
      int c = Integer.parseInt(input[1]);
      int t = Integer.parseInt(input[2]);

      map = new int[r][c];

      for (int i = 0; i < r; i++) {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int j = 0; j < c; j++) {
          map[i][j] = Integer.parseInt(tokenizer.nextToken());
          if (map[i][j] == -1) {
            airPurifierRow = i;
          } else if (map[i][j] != 0) {
            dusts.put(i + " " + j, map[i][j]);
          }
        }
      }

      for (int i = 0; i < t; i++) {
        spread();
        circulation();
        checkDusts();
      }

      bw.write(String.valueOf(remainDust));
      bw.flush();
      bw.close();
      br.close();

    } catch (IOException e) {
    }

  }

  public static void spread() {
    int[] xMove = new int[]{1, -1, 0, 0};
    int[] yMove = new int[]{0, 0, 1, -1};

    for (String key : dusts.keySet()) {
      int dustAmount = dusts.get(key);

      if (dustAmount < 5) {
        continue;
      }

      String[] position = key.split(" ");
      int row = Integer.parseInt(position[0]);
      int col = Integer.parseInt(position[1]);

      int spreadAmount = dustAmount / 5;
      int totalSpreadAmount = 0;
      for (int i = 0; i < 4; i++) {
        int[] next = new int[]{row + xMove[i], col + yMove[i]};

        if (next[0] < 0 || next[0] >= map.length || next[1] < 0 || next[1] >= map[0].length) {
          continue;
        }

        if (map[next[0]][next[1]] == -1) {
          continue;
        }

        map[next[0]][next[1]] += spreadAmount;
        totalSpreadAmount += spreadAmount;
      }

      map[row][col] -= totalSpreadAmount;
    }
  }

  public static void circulation() {
    int width = map[0].length;
    int height = map.length;
    int top = airPurifierRow - 1;
    int bot = airPurifierRow;
    map[top - 1][0] = 0;
    map[bot + 1][0] = 0;


    for (int i = top - 1; i > 0; i--) {
      map[i][0] = map[i - 1][0];
    }
    for (int i = 0; i < width - 1; i++) {
      map[0][i] = map[0][i + 1];
    }
    for (int i = 0; i < top; i++) {
      map[i][width - 1] = map[i + 1][width - 1];
    }
    for (int i = width - 1; i > 1; i--) {
      map[top][i] = map[top][i - 1];
    }

    for (int i = bot + 1; i < height - 1; i++) {
      map[i][0] = map[i + 1][0];
    }
    for (int i = 0; i < width - 1; i++) {
      map[height - 1][i] = map[height - 1][i + 1];
    }
    for (int i = height - 1; i > bot; i--) {
      map[i][width - 1] = map[i - 1][width - 1];
    }
    for (int i = width - 1; i > 1; i--) {
      map[bot][i] = map[bot][i - 1];
    }

    map[top][1] = 0;
    map[bot][1] = 0;
  }

  public static void checkDusts() {
    dusts.clear();
    remainDust = 0;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] > 0) {
          dusts.put(i + " " + j, map[i][j]);
          remainDust += map[i][j];
        }
      }
    }
  }
}
