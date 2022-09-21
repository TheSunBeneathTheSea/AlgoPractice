package Acmicpc.zero.one;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc01517 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(tokenizer.nextToken());
    int S = Integer.parseInt(tokenizer.nextToken());
    int answer = 0;

    tokenizer = new StringTokenizer(br.readLine());
    int[] numbers = new int[N];

    for (int i = 0; i < N; i++) {
      numbers[i] = Integer.parseInt(tokenizer.nextToken());
    }

    int[] prefixSums = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      prefixSums[i] = prefixSums[i - 1] + numbers[i - 1];
    }

    int top = N;
    int bot = 0;
    int count = 0;
    while (top >= bot) {
      int mid = (top + bot) / 2;

      for (int i = 0; i <= N - mid; i++) {
        int cur = prefixSums[i + mid] - prefixSums[i];
        if (S <= cur) count++;
      }

      if (count > 0) {
        answer = mid;
        top = mid - 1;
      } else {
        bot = mid + 1;
      }
      count = 0;
    }

    System.out.print(answer);
  }
}
