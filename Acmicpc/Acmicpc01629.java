package Acmicpc;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc01629 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      StringTokenizer token = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(token.nextToken());
      int b = Integer.parseInt(token.nextToken());
      int c = Integer.parseInt(token.nextToken());

      long answer = solve(a, b, c);

      bw.write(answer + "");
      bw.flush();
      br.close();

      bw.close();
    } catch (IOException e) {

    }
  }

  public static long solve(int a, int b, int c) {
    if (b == 1) {
      return a % c;
    }
    long answer = solve(a, b / 2, c) % c;

    if (b % 2 == 1) {
      return answer * answer * a % c;
    } else {
      return answer * answer % c;
    }
  }
}
