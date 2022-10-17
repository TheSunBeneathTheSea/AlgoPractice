package Programmers.Level2;

import java.util.ArrayDeque;

public class 피로도 {
  public int solution(int k, int[][] dungeons) {
    int answer = -1;

    Fatigue start = new Fatigue(k, 0, dungeons.length);
    Fatigue.dungeons = dungeons;

    ArrayDeque<Fatigue> q = new ArrayDeque<>();
    for (int i = 0; i < dungeons.length; i++) {
      if (start.canVisit(i)) {
        q.add(start.visit(i));
      }
    }

    while (!q.isEmpty()) {
      Fatigue cur = q.poll();

      if (cur.visitCount > answer) answer = cur.visitCount;

      for (int i = 0; i < dungeons.length; i++) {
        if (!cur.isVisited(i) && cur.canVisit(i)) {
          q.add(cur.visit(i));
        }
      }
    }

    return answer;
  }
  static class Fatigue {
    static int[][] dungeons;
    int currentFatigue;
    int visitCount;
    boolean[] isVisited;

    Fatigue(int cur, int visitCount, int numberOfDungeons) {
      this.currentFatigue = cur;
      this.visitCount = visitCount;
      this.isVisited = new boolean[numberOfDungeons];
    }

    boolean isVisited(int dungeonNumber) {
      if (dungeonNumber >= this.isVisited.length) return false;

      return this.isVisited[dungeonNumber];
    }

    boolean canVisit(int dungeonNumber) {
      if (dungeonNumber >= this.isVisited.length) return false;

      return this.currentFatigue >= dungeons[dungeonNumber][0];
    }

    Fatigue visit(int dungeonNumber) {
      Fatigue afterVisit = new Fatigue(this.currentFatigue, this.visitCount, dungeons.length);

      afterVisit.isVisited = this.isVisited.clone();
      afterVisit.currentFatigue -= dungeons[dungeonNumber][1];
      afterVisit.visitCount++;
      afterVisit.isVisited[dungeonNumber] = true;

      return afterVisit;
    }
  }
}
