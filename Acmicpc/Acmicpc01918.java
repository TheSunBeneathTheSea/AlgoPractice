package Acmicpc;

import java.util.*;
import java.io.*;

public class Acmicpc01918 {
  static HashMap<Character, Integer> operatorMap = new HashMap<>();

  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      operatorMap.put('(', 0);
      operatorMap.put('+', 1);
      operatorMap.put('-', 1);
      operatorMap.put('*', 2);
      operatorMap.put('/', 2);

      String expression = br.readLine();

      bw.write(solve(expression));
      bw.flush();
      br.close();

      bw.close();
    } catch (IOException e) {

    }
  }

  static String solve(String expression) {
    StringBuilder sb = new StringBuilder();
    ArrayDeque<Character> stack = new ArrayDeque<>();

    for (Character c : expression.toCharArray()) {
      switch (c) {
        case '(':
          stack.push(c);
          break;
        case '+', '-', '*', '/':
          while (!stack.isEmpty() && operatorMap.get(stack.peek()) >= operatorMap.get(c)) {
            sb.append(stack.pop());
          }
          stack.push(c);
          break;
        case ')':
          Character pop = stack.pop();
          while (pop != '(') {
            sb.append(pop);
            pop = stack.pop();
          }
          break;
        default:
          sb.append(c);
          break;
      }
    }

    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }

    return sb.toString();
  }
}
