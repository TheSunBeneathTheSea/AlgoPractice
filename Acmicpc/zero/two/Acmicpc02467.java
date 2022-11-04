package Acmicpc.zero.two;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc02467 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    StringTokenizer tokenizer = new StringTokenizer(br.readLine());
    int[] array = new int[N];

    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(tokenizer.nextToken());
    }

    int sum = 0;
    int min = Integer.MAX_VALUE;
    int[] selected = {0, 0};
    int i = 0, j = N - 1;
    while (array[i] < 0 && array[j] > 0) {
      sum = array[i] + array[j];

      if (Math.abs(sum) < min) {
        min = Math.abs(sum);
        selected = new int[]{array[i], array[j]};
      }

      if (sum == 0) break;
      else if (sum < 0) i++;
      else j--;
    }

    while (i < N - 1 && array[i] < 0 && array[i + 1] < 0) {
      sum = array[i] + array[i + 1];
      if (Math.abs(sum) < min) {
        min = Math.abs(sum);
        selected = new int[]{array[i], array[i + 1]};
      }
      i++;
    }
    while (j >= 1 && array[j] > 0 && array[j - 1] > 0) {
      sum = array[j] + array[j - 1];
      if (Math.abs(sum) < min) {
        min = Math.abs(sum);
        selected = new int[]{array[j - 1], array[j]};
      }
      j--;
    }

    bw.write(selected[0] + " " + selected[1]);
    bw.flush();
    bw.close();
    br.close();
  }
}
