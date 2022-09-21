package Acmicpc.one.seven;

import java.io.*;
import java.util.*;

public class Acmicpc17298 {
  public static int[] solve() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("input"));

    int n = Integer.parseInt(br.readLine());
    int[] nge = new int[n];

    Stack<Integer[]> stack = new Stack<>();
    StringTokenizer token = new StringTokenizer(br.readLine());

    stack.push(new Integer[]{Integer.parseInt(token.nextToken()), 0});
    for (int i = 1; i < n; i++) {
      Integer cur = Integer.parseInt(token.nextToken());

      Integer[] head = stack.peek();

      while (head[0] < cur) {
        head = stack.pop();

        nge[head[1]] = cur;

        if (stack.isEmpty()) {
          break;
        } else {
          head = stack.peek();
        }
      }

      stack.add(new Integer[]{cur, i});
    }

    while (!stack.isEmpty()) {
      Integer[] remains = stack.pop();

      nge[remains[1]] = -1;
    }
    br.close();

    return nge;
  }
}
