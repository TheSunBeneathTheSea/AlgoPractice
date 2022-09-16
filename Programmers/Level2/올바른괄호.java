package Programmers.Level2;

import java.util.Stack;

public class 올바른괄호 {
  boolean solution(String s) {
    Stack<Boolean> stack = new Stack<>();

    for (char c : s.toCharArray()) {
      if (c == '(') {
        stack.push(true);
      } else {
        if (stack.isEmpty()) {
          return false;
        } else {
          stack.pop();

        }
      }
    }

    return stack.isEmpty();
  }
}
