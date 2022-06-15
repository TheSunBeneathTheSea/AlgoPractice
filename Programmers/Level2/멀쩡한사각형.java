package Programmers.Level2;

public class 멀쩡한사각형 {
  public static long solution(int w, int h) {
    long answer = 0;

    for (long i = 1; i < w; i++) {
      answer += i * h / w;
    }

    return answer * 2;
  }
}
