package Acmicpc.zero;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Acmicpc02110 {
  static int[] houses;

  public static void main(String[] args) {
    try {
      //    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      StringTokenizer tokenizer = new StringTokenizer(br.readLine());

      int n = Integer.parseInt(tokenizer.nextToken());
      int c = Integer.parseInt(tokenizer.nextToken());

      houses = new int[n];
      for (int i = 0; i < n; i++) {
        houses[i] = Integer.parseInt(br.readLine());
      }

      Arrays.sort(houses);

      int low = 1;
      int high = 1000000000;

      while (low < high) {
        int mid = (low + high) / 2;

        if (getMaxInstall(mid) < c) high = mid;
        else low = mid + 1;
      }
      bw.write((low - 1) + "");
      bw.flush();
      br.close();
      bw.close();
    } catch (IOException e) {
    }
  }

  public static int getMaxInstall(int distance) {
    int count = 0;
    int before = -1000000001;
    for (int pos : houses) {
      if (distance > pos - before) continue;

      before = pos;
      count++;
    }

    return count;
  }
}
