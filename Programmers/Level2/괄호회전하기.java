package Programmers.Level2;

import java.util.ArrayDeque;

public class 괄호회전하기 {
  public int solution(String s) {
    int answer = 0;
    ArrayDeque<Character> stack = new ArrayDeque<>();

    for (int i = 0; i < s.length(); i++) {
      boolean isValid = true;
      int curIdx = i;
      char first = s.charAt(curIdx);
      if (first == ')' || first == '}' || first == ']') continue;

      stack.push(s.charAt(curIdx++));
      if (curIdx >= s.length()) curIdx = 0;
      while (curIdx != i) {
        char cur = s.charAt(curIdx);
        if (stack.isEmpty()) stack.push(cur);
        else if (isValidPair(stack.peek(), cur)) {
          stack.pop();
        } else if (cur == ')' || cur == '}' || cur == ']') {
          isValid = false;
          break;
        } else stack.push(cur);

        if (++curIdx >= s.length()) curIdx = 0;
      }

      if (!stack.isEmpty()) isValid = false;

      if (isValid) answer++;

      stack = new ArrayDeque<>();
    }

    return answer;
  }

  public boolean isValidPair(char left, char right) {
    if (left == '(' && right == ')') return true;
    else if (left == '{' && right == '}') return true;
    else if (left == '[' && right == ']') return true;

    return false;
  }
}
