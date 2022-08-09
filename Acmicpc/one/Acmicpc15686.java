package Acmicpc.one;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Acmicpc15686 {
  static ArrayList<Location> houses;
  static ArrayList<Location> stores;
  static int minChickenDistance = 100000;

  public static void main(String[] args) {
    try {
      //    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      StringTokenizer tokenizer = new StringTokenizer(br.readLine());

      int n = Integer.parseInt(tokenizer.nextToken());
      int m = Integer.parseInt(tokenizer.nextToken());

      int[][] map = new int[n][n];
      houses = new ArrayList<>();
      stores = new ArrayList<>();

      for (int i = 0; i < n; i++) {
        tokenizer = new StringTokenizer(br.readLine());

        for (int j = 0; j < n; j++) {
          map[i][j] = Integer.parseInt(tokenizer.nextToken());
          if (map[i][j] == 1) houses.add(new Location(i, j));
          else if (map[i][j] == 2) stores.add(new Location(i, j));
        }
      }

      selectStores(m);

      bw.write(minChickenDistance + "");
      bw.flush();
      bw.close();
      br.close();

    } catch (IOException e) {
    }
  }

  public static void selectStores(int givenSize) {
    int[] combination = new int[givenSize];

    for (int i = 0; i < givenSize; i++) {
      combination[i] = i;
    }

    while (combination[givenSize - 1] < stores.size()) {
      getChickenDistance(combination);

      int t = givenSize - 1;
      while (t != 0 && combination[t] == stores.size() - givenSize + t) {
        t--;
      }
      combination[t]++;
      for (int i = t + 1; i < givenSize; i++) {
        combination[i] = combination[i - 1] + 1;
      }
    }
  }

  public static void getChickenDistance(int[] selectedStores) {
    int sumOfDistances = 0;

    for (Location h : houses) {
      int closestDistance = 100000;

      for (int selectedStoreIdx : selectedStores) {
        int distance = Math.abs(stores.get(selectedStoreIdx).row - h.row)
                + Math.abs(stores.get(selectedStoreIdx).col - h.col);
        closestDistance = Math.min(closestDistance, distance);
      }
      if (closestDistance > minChickenDistance || sumOfDistances > minChickenDistance) return;

      sumOfDistances += closestDistance;
    }

    minChickenDistance = Math.min(minChickenDistance, sumOfDistances);
  }

  static class Location {
    int row;
    int col;

    public Location(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
}
