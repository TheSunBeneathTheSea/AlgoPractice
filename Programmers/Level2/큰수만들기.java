package Programmers.Level2;

import java.util.ArrayDeque;

public class 큰수만들기 {
  public String solution(String number, int k) {
    ArrayDeque<Character> deque = new ArrayDeque<>();
    int limit = number.length() - k;

    for (int i = 0; i < number.length(); i++) {
      char c = number.charAt(i);

      while (!deque.isEmpty() && deque.getLast() < c && k-- > 0) deque.pollLast();

      deque.offerLast(c);
    }

    while (deque.size() > limit) deque.pollLast();

    StringBuilder sb = new StringBuilder();
    for (char c : deque) {
      sb.append(c);
    }

    return sb.toString();
  }
}
