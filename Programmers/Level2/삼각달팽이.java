package Programmers.Level2;

import java.util.ArrayDeque;

public class 삼각달팽이 {
  public int[] solution(int n) {
    if (n == 1) return new int[] {1};

    int arraySize = (n * (n + 1)) / 2;
    int[] result = new int[arraySize + 1];
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    int flag = 0;

    result[1] = 1;

    int idx = 2;
    int triangleCount = 1;
    int startGap = 2;
    int gap = 0;
    for (int i = 2; i <= arraySize; i++) {
      result[idx] = i;
      if (i == arraySize) break;
      if (flag == 0) {
        int nextIdx = idx + gap++ + startGap;
        if (nextIdx <= arraySize && result[nextIdx] == 0) {
          stack.push(nextIdx);
          idx = nextIdx;
        } else {
          flag = 1;
          gap = 0;
          if (triangleCount != 1) startGap += 2;
        }
      }
      if (flag == 1) {
        int nextIdx = idx + 1;
        if (nextIdx > arraySize || result[nextIdx] != 0) {
          flag = 2;
        } else {
          idx = nextIdx;
        }
      }
      if (flag == 2) {
        if (stack.isEmpty()) {
          flag = 0;
          i--;
          triangleCount++;
        }
        else {
          int nextIdx = stack.pop() - (triangleCount * 2 - 1);
          if (result[nextIdx] != 0) {
            flag = 0;
            i--;
            triangleCount++;
          }else {
            idx = nextIdx;
          }
        }
      }
    }

    int[] answer = new int[arraySize];

    for (int i = 1; i <= arraySize; i++) {
      answer[i - 1] = result[i];
    }

    return answer;
  }
}
