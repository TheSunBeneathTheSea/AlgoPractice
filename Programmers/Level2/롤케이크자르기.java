package Programmers.Level2;

import java.util.HashMap;

public class 롤케이크자르기 {
  public int solution(int[] topping) {
    int answer = 0;
    HashMap<Integer, Integer> leftTopping = new HashMap<>();
    HashMap<Integer, Integer> rightTopping = new HashMap<>();

    leftTopping.put(topping[0], 1);
    for (int i = 1; i < topping.length; i++) {
      int toppingCount = rightTopping.getOrDefault(topping[i], 0);
      rightTopping.put(topping[i], toppingCount + 1);
    }


    for (int i = 1; i < topping.length; i++) {
      if (leftTopping.size() == rightTopping.size()) answer++;
      int toppingCount = leftTopping.getOrDefault(topping[i], 0);
      leftTopping.put(topping[i], toppingCount + 1);

      toppingCount = rightTopping.getOrDefault(topping[i], 0);
      if (toppingCount <= 1) rightTopping.remove(topping[i]);
      else rightTopping.put(topping[i], toppingCount - 1);
    }

    return answer;
  }
}
