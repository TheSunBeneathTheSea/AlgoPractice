package Acmicpc.zero.two;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc02343 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(tokenizer.nextToken());
    int M = Integer.parseInt(tokenizer.nextToken());

    int[] prefixSumArray = new int[N + 1];

    int bot = 0;
    tokenizer = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      int cur = Integer.parseInt(tokenizer.nextToken());
      bot = Math.max(bot, cur);
      prefixSumArray[i] = prefixSumArray[i - 1] + cur;
    }

    int top = prefixSumArray[N];
    int mid;
    while (bot <= top) {
      mid = (top + bot) / 2;

      int count = 1;
      int start = 0;
      for (int i = 1; i <= N; i++) {
        int cur = prefixSumArray[i] - prefixSumArray[start];

        if (cur > mid) {
          count++;
          start = i-- - 1;
        }
      }

      if (count <= M) top = mid - 1;
      else bot = mid + 1;
    }

    System.out.println(bot);
  }
}
