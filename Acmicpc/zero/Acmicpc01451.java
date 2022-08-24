package Acmicpc.zero;

import java.io.*;

public class Acmicpc01451 {
  public static void main(String[] args) throws IOException {
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] line = br.readLine().split(" ");
    int height = Integer.parseInt(line[0]);
    int width = Integer.parseInt(line[1]);

    // 2차원 구간합 배열을 작성
    int[][] num = new int[height + 1][width + 1];
    long[][] sum = new long[height + 1][width + 1];

    for (int i = 1; i < height + 1; i++) {
      line = br.readLine().split("");

      for (int j = 1; j < width + 1; j++) {
        num[i][j] = Integer.parseInt(line[j - 1]);
      }
    }

    for (int i = 1; i < height + 1; i++) {
      for (int j = 1; j < width + 1; j++) {
        sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + num[i][j];
      }
    }

    long max = 0;

    // 가능한 모든 조합을 탐색
    // 가로 분할을 먼저하고
    for (int i = 1; i < height; i++) {
      long firstRect = sum[i][width];
      long secondRect, thirdRect;

      // 가로 분할을 추가로 하는 경우
      for (int j = i + 1; j < height; j++) {
        secondRect = sum[j][width] - firstRect;
        thirdRect = sum[height][width] - sum[j][width];

        if (max < firstRect * secondRect * thirdRect) max = firstRect * secondRect * thirdRect;
      }
      // 세로 분할을 추가로 하는 경우
      for (int j = 1; j < width; j++) {
        secondRect = sum[height][j] - sum[i][j];
        thirdRect = sum[height][width] - sum[i][width] - sum[height][j] + sum[i][j];

        if (max < firstRect * secondRect * thirdRect) max = firstRect * secondRect * thirdRect;
      }
      // 반대 세로 분할
      firstRect = sum[height][width] - sum[i][width];
      for (int j = 1; j < width; j++) {
        secondRect = sum[i][j];
        thirdRect = sum[i][width] - secondRect;

        if (max < firstRect * secondRect * thirdRect) max = firstRect * secondRect * thirdRect;
      }
    }
    //세로 분할을 먼저 하고
    for (int i = 1; i < width; i++) {
      long firstRect = sum[height][i];
      long secondRect, thirdRect;

      // 가로 분할을 추가로 하는 경우
      for (int j = 1; j < height; j++) {
        secondRect = sum[j][width] - sum[j][i];
        thirdRect = sum[height][width] - sum[j][width] - sum[height][i] + sum[j][i];

        if (max < firstRect * secondRect * thirdRect) max = firstRect * secondRect * thirdRect;
      }
      // 세로 분할을 추가로 하는 경우
      for (int j = i + 1; j < width; j++) {
        secondRect = sum[height][j] - firstRect;
        thirdRect = sum[height][width] - sum[height][j];

        if (max < firstRect * secondRect * thirdRect) max = firstRect * secondRect * thirdRect;
      }
      // 반대 가로 분할
      firstRect = sum[height][width] - sum[height][i];
      for (int j = 1; j < height; j++) {
        secondRect = sum[j][i];
        thirdRect = sum[height][i] - secondRect;

        if (max < firstRect * secondRect * thirdRect) max = firstRect * secondRect * thirdRect;
      }
    }


    bw.write(max + "");
    bw.flush();
    br.close();
    bw.close();
  }
}
