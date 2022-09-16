package Programmers.Level2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.Collectors;

public class 두큐합같게만들기 {
  public int solution(int[] queue1, int[] queue2) {
    ArrayDeque<Integer> q1 = Arrays.stream(queue1).boxed().collect(Collectors.toCollection(ArrayDeque::new));
    ArrayDeque<Integer> q2 = Arrays.stream(queue2).boxed().collect(Collectors.toCollection(ArrayDeque::new));

    long sum1 = sumOfQueue(q1);
    long sum2 = sumOfQueue(q2);

    if ((sum1 + sum2) % 2 == 1) return -1;
    int count = 0;
    while (count < queue1.length * 3) {
      if (sum1 == sum2) return count;

      if (sum1 < sum2) {
        int move = q2.pollFirst();
        q1.addLast(move);
        sum1 += move;
        sum2 -= move;
      } else {
        int move = q1.pollFirst();
        q2.addLast(move);
        sum1 -= move;
        sum2 += move;
      }
      count++;
    }

    return -1;
  }

  public long sumOfQueue(ArrayDeque<Integer> queue) {
    long sum = 0;

    for (int i : queue) {
      sum += i;
    }

    return sum;
  }
}
