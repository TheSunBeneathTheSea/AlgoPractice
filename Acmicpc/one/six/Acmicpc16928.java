package Acmicpc.one.six;

import java.util.*;

public class Acmicpc16928 {
  public static int solve(HashMap<Integer, Integer> ladders, HashMap<Integer, Integer> snakes) {
    int answer = 100;

    ArrayDeque<Integer[]> queue = new ArrayDeque<>();
    boolean[] isVisited = new boolean[101];

    queue.add(new Integer[]{1, 0});

    while (!queue.isEmpty()) {
      Integer[] cur = queue.poll();
      if (cur[1] >= answer) {
        continue;
      }

      for (int i = 1; i <= 6; i++) {
        int next = cur[0] + i;

        if (ladders.containsKey(next)) {
          queue.add(new Integer[]{ladders.get(next), cur[1] + 1});
          isVisited[ladders.get(next)] = true;
          continue;
        }
        if (snakes.containsKey(next)) {
          queue.add(new Integer[]{snakes.get(next), cur[1] + 1});
          isVisited[snakes.get(next)] = true;
          continue;
        }
        if (next >= 100) {
          if (answer > cur[1]) {
            answer = cur[1] + 1;
          }
        }
        if (next < 100 && !isVisited[next]) {
          queue.add(new Integer[]{next, cur[1] + 1});
          isVisited[next] = true;
        }
      }
    }

    return answer;
  }
}
