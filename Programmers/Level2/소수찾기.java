package Programmers.Level2;

public class 소수찾기 {
  static int answer;
  static int[] digits;
  public int solution(String numbers) {
    answer = 0;

    digits = new int[10];

    for (String s : numbers.split("")) {
      digits[Integer.parseInt(s)]++;
    }

    for (int i = 1; i <= numbers.length(); i++) {
      makeCombination("", i);
    }

    return answer;
  }

  public void makeCombination(String num, int sizeLimit) {
    if (num.startsWith("0")) return;
    if (num.length() == sizeLimit){

      if (isPrime(Integer.parseInt(num))) {
        answer++;
      }
      return;
    }


    for (int i = 0; i < 10; i++) {
      if (digits[i] == 0) continue;

      digits[i]--;
      makeCombination(num + i, sizeLimit);
      digits[i]++;
    }
  }

  public boolean isPrime(int num) {
    if (num < 2) return false;

    for (int i = 2; i <= Math.sqrt(num); i++) {
      if (num % i == 0) return false;
    }

    return true;
  }
}
