package Acmicpc.zero;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Acmicpc01744 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));

    int N = Integer.parseInt(br.readLine());
    PriorityQueue<Integer> positivePQ = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> negativePQ = new PriorityQueue<>(Comparator.naturalOrder());
    boolean hasZero = false;

    while (N-- > 0) {
      int cur = Integer.parseInt(br.readLine());

      if (cur == 0) hasZero = true;
      else if (cur > 0) positivePQ.add(cur);
      else negativePQ.add(cur);
    }

    int maxValue = 0;

    while (positivePQ.size() > 1) {
      int first = positivePQ.poll();
      int second = positivePQ.poll();

      if (first == 1 || second == 1) {
        maxValue += first + second;
        continue;
      }
      int bind = first * second;

      maxValue += bind;
    }

    while (negativePQ.size() > 1) {
      int bind = negativePQ.poll() * negativePQ.poll();

      maxValue += bind;
    }

    while (!positivePQ.isEmpty()) {
      maxValue += positivePQ.poll();
    }

    while (!negativePQ.isEmpty()) {
      if (hasZero) {
        negativePQ.poll();
        continue;
      }

      maxValue += negativePQ.poll();
    }

    System.out.println(maxValue);
  }
}
