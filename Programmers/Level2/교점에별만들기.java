package Programmers.Level2;

import java.util.ArrayList;

public class 교점에별만들기 {
  static ArrayList<Long[]> pointList;

  public String[] solution(int[][] line) {
    pointList = new ArrayList<>();
    for (int i = 0; i < line.length - 1; i++) {
      int[] curExp = line[i];

      for (int j = i + 1; j < line.length; j++) {
        int[] targetExp = line[j];

        if (isParallel(curExp, targetExp)) continue;
        Long[] point = getIntersectionPoint(curExp, targetExp);

        if (isValidPoint(curExp, point) && isValidPoint(targetExp, point)) {
          pointList.add(point);
        }
      }
    }

    long[] minMaxOfX = {pointList.get(0)[0], pointList.get(0)[0]};
    long[] minMaxOfY = {pointList.get(0)[1], pointList.get(0)[1]};
    for (Long[] point : pointList) {
      if (minMaxOfX[0] > point[0]) minMaxOfX[0] = point[0];
      else if (minMaxOfX[1] < point[0]) minMaxOfX[1] = point[0];

      if (minMaxOfY[0] > point[1]) minMaxOfY[0] = point[1];
      else if (minMaxOfY[1] < point[1]) minMaxOfY[1] = point[1];
    }

    boolean[][] isStar = new boolean[(int)(minMaxOfY[1] - minMaxOfY[0] + 1)][(int)(minMaxOfX[1] - minMaxOfX[0] + 1)];
    String[] answer = new String[(int)(minMaxOfY[1] - minMaxOfY[0] + 1)];
    for (Long[] point : pointList) {
      isStar[(int)(point[1] - minMaxOfY[0])][(int)(point[0] - minMaxOfX[0])] = true;
    }

    for (int i = 0; i < isStar.length; i++) {
      StringBuilder sb = new StringBuilder();

      for (int j = 0; j < isStar[0].length; j++) {
        if (isStar[i][j]) sb.append("*");
        else sb.append(".");
      }
      answer[isStar.length - i - 1] = sb.toString();
    }

    return answer;
  }

  public boolean isParallel(int[] expA, int[] expB) {
    return (expA[0] * expB[1] - expA[1] * expB[0]) == 0;
  }

  public Long[] getIntersectionPoint(int[] expA, int[] expB) {
    return new Long[] {
            ((long)expA[1] * expB[2] - (long)expB[1] * expA[2])
                    / ((long)expA[0] * expB[1] - (long)expA[1] * expB[0]),
            (((long)expA[2] * expB[0] - (long)expA[0] * expB[2])
                    / ((long)expA[0] * expB[1] - (long)expA[1] * expB[0]))
    };
  }

  public boolean isValidPoint(int[] exp, Long[] point) {
    return exp[0] * point[0] + exp[1] * point[1] + exp[2] == 0;
  }
}