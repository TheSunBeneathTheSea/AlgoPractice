package Acmicpc.zero.one;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Acmicpc01043 {
  public static Set<Integer> truthGroup = new HashSet<>();

  public static int solve(int[] knowsTruth, int[][] parties) {
    if (knowsTruth[0] == 0) {
      return parties.length;
    }
    int answer = 0;

    for (int i = 1; i < knowsTruth.length; i++) {
      truthGroup.add(knowsTruth[i]);
    }

    boolean[] checkedParty = new boolean[parties.length];

    boolean checkOneMoreTime = true;
    while (checkOneMoreTime) {
      checkOneMoreTime = false;

      for (int i = 0; i < parties.length; i++) {
        if (checkedParty[i]) {
          continue;
        }

        int[] party = parties[i];
        for (int people : party) {
          if (truthGroup.contains(people)) {
            truthGroup.addAll(Arrays.stream(party).boxed().toList());
            checkOneMoreTime = true;
            checkedParty[i] = true;
            break;
          }
        }
      }
    }

    for (boolean party : checkedParty) {
      if (!party) {
        answer++;
      }
    }

    return answer;
  }
}