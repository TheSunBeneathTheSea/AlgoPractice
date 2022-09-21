package Acmicpc.one.five;

import java.util.*;
import java.io.*;

public class Acmicpc15654 {
  public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void solve(int m, int len, int[] s, boolean[] isSelected, ArrayList<Integer> numbers) throws IOException {
    if (m == len) {
      String result = "";
      for (int n : s) {
        result += n + " ";
      }
      bw.write(result + "\n");
      bw.flush();
      return;
    }
    for (int i = 0; i < numbers.size(); i++) {
      if (isSelected[i]) {
        continue;
      }
      s[len] = numbers.get(i);
      isSelected[i] = true;
      solve(m, len + 1, s, isSelected, numbers);
      isSelected[i] = false;
      s[len] = 0;
    }
  }
}
