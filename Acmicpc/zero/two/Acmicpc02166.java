package Acmicpc.zero.two;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc02166 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    double[][] points = new double[N][2];

    StringTokenizer tokenizer;
    for (int i = 0; i < N; i++) {
      tokenizer = new StringTokenizer(br.readLine());

      points[i] = new double[]{Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
    }

    double sum = 0;
    for (int i = 0; i < N; i++) {
      int one = i;
      int another = i + 1 == N ? 0 : i + 1;

      sum = sum + points[one][0] * points[another][1] - points[one][1] * points[another][0];
    }

    sum = Math.abs(sum) / 2;

    System.out.printf("%.1f", sum);
  }
}
