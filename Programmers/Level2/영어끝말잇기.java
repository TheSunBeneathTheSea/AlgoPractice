package Programmers.Level2;

import java.util.HashSet;

public class 영어끝말잇기 {
  public int[] solution(int n, String[] words) {
    int[] answer = {0, 0};

    HashSet<String> usedWords = new HashSet<>();

    String previousLastChar = "";
    for (int i = 0; i < words.length; i++) {
      if (words[i].startsWith(previousLastChar) && usedWords.add(words[i])) {
        previousLastChar = words[i].substring(words[i].length() - 1);
        continue;
      }

      answer = new int[] {(i % n) + 1, (i / n) + 1};
      break;
    }

    return answer;
  }
}
