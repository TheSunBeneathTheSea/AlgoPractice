package Acmicpc.one.two;

import java.io.*;
import java.util.ArrayDeque;

public class Acmicpc12851 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      String[] input = br.readLine().split(" ");
      int seeker = Integer.parseInt(input[0]);
      int target = Integer.parseInt(input[1]);

      bfs(seeker, target);

    } catch (IOException e) {
    }
  }

  public static void bfs(int seeker, int target) {
    ArrayDeque<Seeker> queue = new ArrayDeque<>();

    Seeker start = new Seeker(seeker, 0);
    queue.add(start);

    int shortestPath = 100001;
    int pathCount = 1;
    while (!queue.isEmpty()) {
      Seeker currentStatus = queue.poll();

      if (currentStatus.location == target) {
        shortestPath = Math.min(shortestPath, currentStatus.step);
        break;
      }

      if (currentStatus.isVisitedLater()) continue;
      currentStatus.hasVisit();

      if (currentStatus.location < target) {
        Seeker warp = currentStatus.getSeekerAfterWarp();
        if (!warp.isVisitedLater()) queue.add(warp);
      }
      if (currentStatus.location != 0) {
        Seeker backward = currentStatus.getSeekerAfterWalkBackward();
        if (!backward.isVisitedLater()) queue.add(backward);
      }
      Seeker forward = currentStatus.getSeekerAfterWalkForward();
      if (!forward.isVisitedLater()) queue.add(forward);
    }

    while (!queue.isEmpty()) {
      Seeker currentStatus = queue.poll();

      if (currentStatus.step != shortestPath || currentStatus.location != target) continue;

      pathCount++;
    }

    System.out.println(shortestPath);
    System.out.println(pathCount);
  }

  static class Seeker {
    static int[] isVisited = new int[200001];
    int location;
    int step;

    public Seeker(int location, int step) {
      this.location = location;
      this.step = step;
    }

    public void hasVisit() {
      if (isVisited[this.location] == 0) isVisited[this.location] = this.step;
    }

    public boolean isVisitedLater() {
      return isVisited[this.location] != 0 && isVisited[this.location] < this.step;
    }

    public Seeker getSeekerAfterWarp() {
      return new Seeker(this.location * 2, this.step + 1);
    }

    public Seeker getSeekerAfterWalkForward() {
      return new Seeker(this.location + 1, this.step + 1);
    }

    public Seeker getSeekerAfterWalkBackward() {
      return new Seeker(this.location - 1, this.step + 1);
    }
  }
}

