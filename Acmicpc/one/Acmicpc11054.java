package Acmicpc.one;

import java.io.*;
import java.util.Arrays;

public class Acmicpc11054 {
  public static void main(String[] args) throws IOException {
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());

    int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int[] leftMax = new int[n];
    int[] rightMax = new int[n];

    for (int i = 0; i < arr.length; i++) {
      int max = 1;
      for (int j = 0; j < i; j++) {
        if (arr[j] < arr[i] && max <= leftMax[j]) {
          max = leftMax[j] + 1;
        }
      }

      leftMax[i] = max;
    }
    for (int i = arr.length - 1; i >= 0; i--) {
      int max = 1;
      for (int j = arr.length - 1; j > i; j--) {
        if (arr[j] < arr[i] && max <= rightMax[j]) {
          max = rightMax[j] + 1;
        }
      }

      rightMax[i] = max;
    }

    int max = -1;
    for (int i = 0; i < n; i++) {
      if (max < leftMax[i] + rightMax[i]) {
        max = leftMax[i] + rightMax[i];
      }
    }

    bw.write(--max + "");
    bw.flush();
    br.close();
    bw.close();
  }
}
