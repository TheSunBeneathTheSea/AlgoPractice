package Programmers.Level2;

public class 구명보트 {
  public int solution(int[] people, int limit) {
    int answer = 0;

    int[] status = new int[241];

    for (int n : people) {
      status[n]++;
    }

    int remainder = people.length;
    while (remainder != 0) {
      int curWeight = 0;
      int onBoat = 0;

      for (int weight = status.length - 1; weight >= 40; weight--) {
        if (curWeight == limit || onBoat == 2) break;
        if (status[weight] == 0) continue;
        if (curWeight + weight > limit) continue;

        curWeight += weight;
        onBoat++;
        status[weight]--;
        remainder--;
        weight++;
      }

      answer++;
    }

    return answer;
  }
}
