package Acmicpc.zero;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Acmicpc01759 {
  static int l, c;
  static StringBuilder sb;
  static char[] charPool;

  public static void main(String[] args) {
    try {
      //    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));

      StringTokenizer tokenizer = new StringTokenizer(br.readLine());

      l = Integer.parseInt(tokenizer.nextToken());
      c = Integer.parseInt(tokenizer.nextToken());

      tokenizer = new StringTokenizer(br.readLine());

      charPool = new char[c];
      for (int i = 0; i < c; i++) {
        charPool[i] = tokenizer.nextToken().charAt(0);
      }

      Arrays.sort(charPool);

      sb = new StringBuilder(l);

      findCombination(0, 0);

    } catch (IOException e) {
    }
  }

  public static void findCombination(int vowelCount, int idx) {
    if (sb.length() == l) {
      if (vowelCount > 0 && vowelCount < l - 1) {
        System.out.println(sb);
      }
      return;
    }

    for (int i = idx; i < c; i++) {
      char cur = charPool[i];

      sb.append(cur);
      if (isVowel(cur)) {
        findCombination(vowelCount + 1, i + 1);
      } else {
        findCombination(vowelCount, i + 1);
      }
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  public static boolean isVowel(char c) {
    return "aeiou".contains(String.valueOf(c));
  }
}
