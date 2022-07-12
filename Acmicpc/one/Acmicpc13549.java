package Acmicpc.one;


import java.util.ArrayDeque;
import java.util.Scanner;

public class Acmicpc13549 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String[] input = sc.nextLine().split(" ");

    int seeker = Integer.parseInt(input[0]);
    int target = Integer.parseInt(input[1]);

    ArrayDeque<Integer[]> deque = new ArrayDeque<>();
    boolean[] isVisited = new boolean[200001];

    deque.add(new Integer[]{seeker, 0});

    while (!deque.isEmpty()) {
      Integer[] curStatus = deque.poll();
      int curPos = curStatus[0];
      int curWeight = curStatus[1];

      if (curPos == target) {
        System.out.println(curStatus[1]);
        break;
      }

      int[] nextPos = new int[]{curPos * 2, curPos + 1, curPos - 1};

      for (int i = 0; i < nextPos.length; i++) {
        if ((nextPos[i] < 0 || nextPos[i] > 200000) || isVisited[nextPos[i]]) {
          continue;
        }
        isVisited[nextPos[i]] = true;

        if (i < 1) {
          if (curPos < target) {
            deque.addFirst(new Integer[]{nextPos[i], curWeight});
          }
        } else {
          deque.addLast(new Integer[]{nextPos[i], curStatus[1] + 1});
        }
      }
    }
  }
}
